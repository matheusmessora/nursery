package br.com.pandox.nursery.domain.metric.entity;

import br.com.pandox.nursery.domain.monitor.entity.MonitorEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class MetricEntity {

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(updatable = false, insertable = true)
    private MonitorEntity monitor;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "metric")
    private List<MetricDataEntity> datas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(Integer timeInterval) {
        this.timeInterval = timeInterval;
    }

    public MonitorEntity getMonitor() {
        return monitor;
    }

    public void setMonitor(MonitorEntity monitor) {
        this.monitor = monitor;
    }

    public List<MetricDataEntity> getDatas() {
        return datas;
    }

    public void setDatas(List<MetricDataEntity> datas) {
        this.datas = datas;
    }
}
