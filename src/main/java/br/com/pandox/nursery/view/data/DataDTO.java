package br.com.pandox.nursery.view.data;

import br.com.pandox.nursery.DataTransferObject;
import br.com.pandox.nursery.view.metric.MetricDTO;

import java.util.Date;

public class DataDTO implements DataTransferObject {

    private Long id;

    private Integer value;

    private Date date_creation;

    private MetricDTO metric;

    public DataDTO() {
    }

    public DataDTO(Integer value, Date dateCreation) {
        this.value = value;
        this.date_creation = dateCreation;
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

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public MetricDTO getMetric() {
        return metric;
    }

    public void setMetric(MetricDTO metric) {
        this.metric = metric;
    }
}
