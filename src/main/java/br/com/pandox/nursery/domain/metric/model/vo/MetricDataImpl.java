package br.com.pandox.nursery.domain.metric.model.vo;

import java.util.Date;

public class MetricDataImpl implements MetricData {

    private Integer value;
    private Date dateCreation;

    protected MetricDataImpl(Integer value, Date dateCreation) {
        this.value = value;
        this.dateCreation = dateCreation;
    }

    protected MetricDataImpl(Integer value) {
        this(value, new Date());
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public Date getDateCreation() {
        return dateCreation;
    }
}
