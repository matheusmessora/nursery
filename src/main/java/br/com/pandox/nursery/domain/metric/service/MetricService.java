package br.com.pandox.nursery.domain.metric.service;

import br.com.pandox.nursery.domain.metric.model.Metric;

public interface MetricService {

    Metric create(Metric metric, Long monitorId);

    Metric update(Metric metric);
}
