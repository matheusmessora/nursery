package br.com.pandox.nursery.domain.metric.entity;

import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.metric.model.MetricData;

import javax.persistence.*;
import java.util.Date;

@Entity
public class DataMetricEntity implements MetricData {

    @Deprecated
    public DataMetricEntity() {
    }

    public DataMetricEntity(Metric metric, Integer value) {
        this.metric = metric;
        this.value = value;
        this.dateCreation = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer value;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    @ManyToOne(targetEntity = MetricEntity.class)
    @JoinColumn
    private Metric metric;


    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public Date getDateCreation() {
        return dateCreation;
    }
}
