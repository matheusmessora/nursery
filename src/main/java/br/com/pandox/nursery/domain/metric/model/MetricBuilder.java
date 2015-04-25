package br.com.pandox.nursery.domain.metric.model;

import br.com.pandox.nursery.domain.metric.model.vo.MetricData;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.threshold.model.Threshold;
import com.google.common.base.Optional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MetricBuilder {

    private String name;
    private String type;
    private Integer timeInterval;
    private Optional<Set<MetricData>> datas;
    private Long id;
    private Monitor monitor;
    private Integer maxValue;
    private Optional<Threshold> edge;
    private List<Threshold> thresholds;

    public MetricBuilder() {
        edge = Optional.absent();
        datas = Optional.absent();
        thresholds = new ArrayList<>();
    }

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

    public MetricBuilder setDatas(Set<MetricData> datas) {
        this.datas = Optional.fromNullable(datas);
        return this;
    }

    public MetricBuilder setMonitor(Monitor monitor) {
        this.monitor = monitor;
        return this;
    }

    public MetricBuilder threshold(Threshold threshold){
        this.thresholds.add(threshold);
        return this;
    }

    public Metric build() {
        Assert.hasText(name, "name must not be null");
        Assert.notNull(timeInterval, "time_interval must not be null");
        if(timeInterval < 1 || timeInterval > 1440) {
            throw new IllegalArgumentException("Malformed attribute: time_interval. It should be between 1 and 1440");
        }

        Set<MetricData> datas = new HashSet<>();
        if(this.datas.isPresent()) {
            datas = this.datas.get();
        }

        Metric metricEntity = new MetricEntity(id, name, type, timeInterval, datas, monitor, maxValue);
        for (Threshold threshold : thresholds) {
            metricEntity.addThreshold(threshold);
        }

        return metricEntity;
    }

    public MetricBuilder setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
        return this;
    }
}
