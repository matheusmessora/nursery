package br.com.pandox.nursery.domain.metric.entity;

import br.com.pandox.nursery.domain.metric.entity.repository.MetricRepository;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.metric.model.MetricData;
import br.com.pandox.nursery.domain.monitor.entity.MonitorEntity;
import br.com.pandox.nursery.domain.monitor.model.Monitor;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
public class MetricEntity implements Metric {

    public MetricEntity() {
    }

    public MetricEntity(Long id, String name, String type, Integer timeInterval, List<MetricData> datas) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.timeInterval = timeInterval;
        this.datas = datas;
        if(datas == null) {
            this.datas = Collections.EMPTY_LIST;
        }
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

    @ManyToOne(targetEntity = MonitorEntity.class)
    @JoinColumn
    private Monitor monitor;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = DataMetricEntity.class)
    private List<MetricData> datas;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Integer getTimeInterval() {
        return timeInterval;
    }

    @Override
    public Monitor getMonitor() {
        return monitor;
    }

    @Override
    public void addData(Integer value, MetricRepository repository) {
        MetricData e = new DataMetricEntity(this, value);
        datas.add(e);

//        if(status.equals(Monitor.Status.UNREGISTERED) || status.equals(Monitor.Status.STOPPED)){
//            throw new CommandException("Can not add metric to Monitor in %s status", status.name());
//        }

        repository.save(this);
    }

    @Override
    public List<MetricData> getDatas() {
        return this.datas;
//        Iterable<MetricData> all = this.datas;
//
//        ImmutableList.Builder<MetricData> builder = ImmutableList.builder();
//        return builder.addAll(all).build();
    }

    @Override
    public String toString() {
        return "MetricEntity{" +
                "id=" + id +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }
}
