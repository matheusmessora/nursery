package br.com.pandox.nursery.domain.alert.model;

import br.com.pandox.nursery.domain.alert.Alert;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.metric.model.vo.MetricData;
import org.springframework.util.Assert;

public class AlertBuilder {

    private Metric metric;
    private MetricData data;

    public AlertBuilder setMetric(Metric metric){
        this.metric = metric;
        return this;
    }

    public AlertBuilder setData(MetricData data){
        this.data = data;
        return this;
    }

    public Alert build() {
        Assert.notNull(metric, "metric must not be null");
        Assert.notNull(data, "metricData must not be null");

        Alert alert = new AlertEntity(metric, data);

        return alert;
    }


}
