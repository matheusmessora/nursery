package br.com.pandox.nursery.domain.metric.model;

import br.com.pandox.nursery.domain.metric.model.vo.MetricData;
import org.springframework.util.Assert;

import java.util.List;

public class MetricBuilder {

    private String name;
    private String type;
    private Integer timeInterval;
    private List<MetricData> datas;
    private Long id;

    public MetricBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public MetricBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public MetricBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public MetricBuilder setTimeInterval(Integer timeInterval) {
        this.timeInterval = timeInterval;
        return this;
    }

    public MetricBuilder setDatas(List<MetricData> datas) {
        this.datas = datas;
        return this;
    }

    public Metric build() {
        Assert.hasText(name, "name must not be null");
        Assert.notNull(timeInterval, "time_interval must not be null");
        if(timeInterval < 1 || timeInterval > 1440) {
            throw new IllegalArgumentException("Malformed attribute: time_interval. It should be between 1 and 1440");
        }

        return new MetricImpl(id, name, type, timeInterval, datas);
    }
}
