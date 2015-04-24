package br.com.pandox.nursery.domain.metric.model;

import br.com.pandox.nursery.domain.CommandException;
import br.com.pandox.nursery.domain.alert.Alert;
import br.com.pandox.nursery.domain.alert.event.DataViolatedThresdhold;
import br.com.pandox.nursery.domain.alert.model.AlertEntity;
import br.com.pandox.nursery.domain.metric.model.vo.Edge;
import br.com.pandox.nursery.domain.metric.model.vo.EdgeImpl;
import br.com.pandox.nursery.domain.metric.model.vo.MetricData;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.model.MonitorEntity;
import com.google.common.base.Optional;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity
public class MetricEntity implements Metric {

    public MetricEntity() {
    }

    public MetricEntity(Long id, String name, String type, Integer timeInterval) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.timeInterval = timeInterval;
    }


    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private Integer timeInterval;

    @Column
    private Integer maxValue;

    @Column
    private Integer thresholdLowValue;

    @Column
    private Integer thresholdHighValue;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = MonitorEntity.class)
    @JoinColumn(updatable = false, insertable = true, nullable = false)
    private Monitor monitor;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = MetricDataEntity.class, mappedBy = "metric")
    private List<MetricData> datas;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = AlertEntity.class, mappedBy = "metric")
    private List<Alert> alerts;

    @Transient
    private boolean dataLoaded;

    public MetricEntity(Long id, String name, String type, Integer timeInterval, Set<MetricData> datas, Monitor monitor, Integer maxValue) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.timeInterval = timeInterval;
        ArrayList<MetricData> metricDatas = Lists.newArrayList(datas);
        this.datas = metricDatas;
        this.monitor = monitor;
        this.maxValue = maxValue;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Integer getTimeInterval() {
        return timeInterval;
    }

    @Override
    public Integer getMaxValue() {
        return maxValue;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void addData(MetricData data, EventBus eventBus) {
        Assert.notNull(eventBus, "eventBus should not be null");
        Assert.notNull(data, "data should not be null");

        validate(data);

        publishAlertEvent(data, eventBus);

        this.datas.add(data);
    }

    protected void publishAlertEvent(MetricData data, EventBus eventBus) {
        if (!getEdge().isPresent()) {
            return;
        }

        if (data.getValue() > getEdge().get().getHighest()) {
            DataViolatedThresdhold event = new DataViolatedThresdhold(this, data);
            eventBus.post(event);
        }
    }

    protected void validate(MetricData data) {
        Optional<MetricData> lastData = getFirstData();
        if (lastData.isPresent()) {
            DateTime lastCreationDate = new DateTime(lastData.get().getDateCreation());
            DateTime now = new DateTime();
            int minutes = Minutes.minutesBetween(lastCreationDate, now).getMinutes();
            if (minutes < getTimeInterval()) {
                //                throw new CommandException("You must wait %s minutes for sending another data", getTimeInterval());
            }
        }
        if (getMonitor().getStatus().equals(Monitor.Status.UNREGISTERED) || getMonitor().getStatus().equals(Monitor.Status.STOPPED)) {
            throw new CommandException("Can not add data. Monitor is in %s status", getMonitor().getStatus().name());
        }
    }

    protected Optional<MetricData> getFirstData() {
        MetricData last = Iterables.getFirst(datas, null);
        return Optional.fromNullable(last);
    }

    public List<MetricData> getDatas() {
        return datas;
    }

    public boolean isDatasLoaded() {
        return dataLoaded;
    }

    @Override
    public void addEdge(Edge edge) {
        Assert.notNull(edge);
        thresholdLowValue = edge.getLowest();
        thresholdHighValue = edge.getHighest();
    }

    @Override
    public Optional<Edge> getEdge() {
        Optional<Edge> edge = Optional.absent();
        if (hasEdge()) {
            Edge edge1 = new EdgeImpl(thresholdLowValue, thresholdHighValue);
            edge = Optional.of(edge1);
        }

        return edge;
    }

    @Override
    public List<Alert> getAlerts() {
        if (alerts != null) {
            return alerts;
        }

        return Collections.emptyList();
    }

    private boolean hasEdge() {
        return thresholdLowValue != null && thresholdHighValue != null;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public void setDataLoaded(boolean dataLoaded) {
        this.dataLoaded = dataLoaded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        MetricEntity that = (MetricEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
