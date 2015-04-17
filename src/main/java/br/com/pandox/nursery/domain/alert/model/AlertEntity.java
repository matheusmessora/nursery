package br.com.pandox.nursery.domain.alert.model;

import br.com.pandox.nursery.domain.alert.Alert;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.metric.model.MetricEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class AlertEntity implements Alert {

    public AlertEntity() {
    }

    public AlertEntity(Metric metric) {
        this.metric = metric;
        this.date = new Date();
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = MetricEntity.class)
    @JoinColumn(updatable = false, insertable = true, nullable = false)
    private Metric metric;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Metric alertedBy() {
        return metric;
    }

    @Override
    public Date getDate() {
        return date;
    }
}
