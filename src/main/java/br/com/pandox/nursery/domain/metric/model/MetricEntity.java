package br.com.pandox.nursery.domain.metric.model;

import br.com.pandox.nursery.domain.CommandException;
import br.com.pandox.nursery.domain.metric.model.vo.MetricData;
import br.com.pandox.nursery.domain.monitor.model.MonitorEntity;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.infrastructure.event.listener.EventListener;
import com.google.common.base.Optional;
import com.google.common.collect.Iterables;
import org.joda.time.DateTime;
import org.joda.time.Minutes;

import javax.persistence.*;
import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private Integer timeInterval;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = MonitorEntity.class)
    @JoinColumn(updatable = false, insertable = true)
    private Monitor monitor;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = MetricDataEntity.class, mappedBy = "metric")
    private List<MetricData> datas;

    @Transient
    private boolean dataLoaded;

    public MetricEntity(Long id, String name, String type, Integer timeInterval, List<MetricData> datas, Monitor monitor) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.timeInterval = timeInterval;
        this.datas = datas;
        this.monitor = monitor;
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

    public Monitor getMonitor() {
        return monitor;
    }

    public void addData(MetricData data, EventListener eventListener) {
        Optional<MetricData> lastData = getFirstData();
        if(lastData.isPresent()){
            DateTime lastCreationDate = new DateTime(lastData.get().getDateCreation());
            DateTime now = new DateTime();
            int minutes = Minutes.minutesBetween(lastCreationDate, now).getMinutes();
            if(minutes < getTimeInterval()) {
                throw new CommandException("You must wait %s minutes for sending another data", getTimeInterval());
            }
        }
        if(getMonitor().getStatus().equals(Monitor.Status.UNREGISTERED) || getMonitor().getStatus().equals(Monitor.Status.STOPPED)){
            throw new CommandException("Can not add data. Monitor in %s status", getMonitor().getStatus().name());
        }

        this.datas.add(data);
    }

    private Optional<MetricData> getFirstData(){
        MetricData last = Iterables.getFirst(datas, null);
        return Optional.fromNullable(last);
    }

    public List<MetricData> getDatas() {
        return datas;
    }

    public boolean isDatasLoaded() {
        return dataLoaded;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public void setDataLoaded(boolean dataLoaded) {
        this.dataLoaded = dataLoaded;
    }
}
