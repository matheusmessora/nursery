package br.com.pandox.nursery.domain.metric.model.vo;

import java.util.Date;

public class MetricDataBuilder {

    private Integer value;
    private Date dateCreation;

    protected MetricDataBuilder(){}

    public MetricDataBuilder setValue(Integer value){
        this.value = value;
        return this;
    }

    public MetricDataBuilder setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    public MetricData build() {
//        Assert.hasText(name, "name must not be null");
//        Assert.notNull(timeInterval, "time_interval must not be null");

        return new MetricDataImpl(value, dateCreation);
    }
}
