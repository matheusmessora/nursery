package br.com.pandox.nursery.domain.metric.event;

import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.infrastructure.event.Event;

public class CreateDataEvent implements Event {

    private Metric metric;

    public CreateDataEvent(Metric metric) {
        this.metric = metric;
    }

    public Metric getMetric() {
        return metric;
    }
}
