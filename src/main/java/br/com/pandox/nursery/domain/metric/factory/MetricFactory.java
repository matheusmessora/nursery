package br.com.pandox.nursery.domain.metric.factory;

import br.com.pandox.nursery.domain.metric.model.MetricEntity;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.view.rest.metric.MetricDTO;

public interface MetricFactory {

	Metric from(MetricEntity entity, boolean loadData);

	Metric from(MetricDTO metricDTO);

}
