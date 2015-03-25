package br.com.pandox.nursery.domain.metric.entity;

import org.springframework.util.Assert;

public class MetricBuilder {
    private String name;
    private String type;
    private Integer timeInterval;

    public MetricBuilder setName(String name) {
        Assert.hasText(name, "name must not be null");
        this.name = name;
        return this;
    }

    public MetricBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public MetricBuilder setTimeInterval(Integer timeInterval) {
        Assert.notNull(timeInterval, "time_interval must not be null");
        if(timeInterval < 1 || timeInterval > 1440) {
            throw new IllegalArgumentException("Malformed attribute: time_interval. It should be between 1 and 1440");
        }
        this.timeInterval = timeInterval;
        return this;
    }

    public MetricEntity build() {
        return new MetricEntity(name, type, timeInterval);
    }
}
