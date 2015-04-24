package br.com.pandox.nursery.domain.alert.event;

import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.metric.model.vo.MetricData;
import org.springframework.util.Assert;

public class DataViolatedThresdhold {

    private MetricData data;
    private Metric metric;


    public DataViolatedThresdhold(Metric metric, MetricData data) {
        Assert.notNull(metric, "metric should not be null");
        Assert.notNull(data, "metricData should not be null");

        this.metric = metric;
        this.data = data;
    }

    public Metric getMetric() {
        return metric;
    }

    public MetricData getData() {
        return data;
    }
}
