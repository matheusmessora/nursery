package br.com.pandox.nursery.domain.monitor.entity;

import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.infrastructure.exception.DomainNotFoundException;


public class MetricNotFoundException extends DomainNotFoundException {

    public MetricNotFoundException() {
        super(Metric.class);
    }
}
