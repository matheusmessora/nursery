package br.com.pandox.nursery.domain.metric.entity;

import br.com.pandox.nursery.view.exception.DomainIllegalAttributeException;
import br.com.pandox.nursery.infrastructure.util.DomainAssert;

public class MetricBuilder {
    private String name;
    private String type;
    private Integer timeInterval;

    public MetricBuilder setName(String name) {
        DomainAssert.hasText(name,"name");
        this.name = name;
        return this;
    }

    public MetricBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public MetricBuilder setTimeInterval(Integer timeInterval) {
        DomainAssert.notNull(timeInterval, "time_interval");
        if(timeInterval < 1 || timeInterval > 1440) {
            throw new DomainIllegalAttributeException("metric", "time_interval");
        }
        this.timeInterval = timeInterval;
        return this;
    }

    public MetricEntity build() {
        return new MetricEntity(name, type, timeInterval);
    }
}
