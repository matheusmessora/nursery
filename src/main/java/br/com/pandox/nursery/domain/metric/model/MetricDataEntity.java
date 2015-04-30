package br.com.pandox.nursery.domain.metric.model;

import br.com.pandox.nursery.domain.metric.model.vo.MetricData;

import javax.persistence.*;
import java.util.Date;

@Entity
public class MetricDataEntity implements MetricData {

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

    @ManyToOne(targetEntity = MetricEntity.class)
    @JoinColumn(insertable = true)
    private Metric metric;

    public MetricDataEntity(Integer value, Date dateCreation) {
        if(dateCreation == null) {
            dateCreation = new Date();
        }
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

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }


}
