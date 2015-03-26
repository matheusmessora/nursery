package br.com.pandox.nursery.domain.metric.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class MetricDataEntity {

    @Deprecated
    public MetricDataEntity() {
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

    @ManyToOne
    @JoinColumn
    private MetricEntity metric;

    public MetricDataEntity(Integer value) {
        this(value, new Date());
    }

    public MetricDataEntity(Integer value, Date dateCreation) {
        this.dateCreation = dateCreation;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public MetricEntity getMetric() {
        return metric;
    }

    public void setMetric(MetricEntity metric) {
        this.metric = metric;
    }
}
