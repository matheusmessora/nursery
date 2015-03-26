package br.com.pandox.nursery.domain.metric.factory;

import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.view.metric.MetricDTO;

public interface MetricFactory {

	Metric createFrom(MetricDTO metricDTO);

	MetricDTO fabric(Metric metric);
}
