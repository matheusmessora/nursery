package br.com.pandox.nursery.domain.alert.event;

import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.infrastructure.event.Event;
import org.springframework.util.Assert;

public class CreateAlertFromMetricDataEdge implements Event {

    private Metric metric;


    public CreateAlertFromMetricDataEdge(Metric metric) {
        Assert.notNull(metric, "metric should not be null");
        this.metric = metric;
    }

    public Metric getMetric() {
        return metric;
    }
}
