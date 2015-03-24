package br.com.pandox.nursery.domain.metric.loader;

import br.com.pandox.nursery.domain.metric.model.Metric;

public interface MetricLoader {

	Metric loadByName(String name);

	Metric loadByID(Long id);
}
