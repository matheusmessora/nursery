package br.com.pandox.nursery.domain.alert.event;

import br.com.pandox.nursery.domain.metric.model.vo.MetricData;
import org.springframework.util.Assert;

public class DataViolatedThresdhold {

    private MetricData data;
    private Long metricId;


    public DataViolatedThresdhold(Long metricId, MetricData data) {
        Assert.notNull(metricId, "metricId should not be null");
        Assert.notNull(data, "metricData should not be null");

        this.metricId = metricId;
        this.data = data;
    }

    public Long getMetricId() {
        return metricId;
    }
    public MetricData getData() {
        return data;
    }
}
