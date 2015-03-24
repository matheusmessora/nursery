package br.com.pandox.nursery.domain.metric.entity;

import br.com.pandox.nursery.domain.metric.model.Metric;

import javax.persistence.*;

@Entity
public class MetricEntity implements Metric {

    @Deprecated
    public MetricEntity() {
    }

    protected MetricEntity(String name, String type, Integer timeInterval) {
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
    public String toString() {
        return "MetricEntity{" +
                "id=" + id +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }
}
