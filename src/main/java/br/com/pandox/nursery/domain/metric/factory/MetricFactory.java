package br.com.pandox.nursery.domain.metric.factory;

import br.com.pandox.nursery.domain.metric.model.MetricEntity;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.view.rest.metric.MetricDTO;

public interface MetricFactory {

	Metric createFrom(MetricEntity entity, boolean loadData);

	Metric createFrom(MetricDTO metricDTO);

	MetricDTO fabric(Metric metric);
}
