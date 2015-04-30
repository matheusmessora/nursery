package br.com.pandox.nursery.domain.metric.loader;

import br.com.pandox.nursery.domain.metric.model.Metric;

import java.util.List;

public interface MetricLoader {

	Metric loadByName(String name);

	Metric loadByID(Long id, boolean loadData, boolean loadAlerts);

	List<Metric> loadByMonitorID(Long monitorId);
}
