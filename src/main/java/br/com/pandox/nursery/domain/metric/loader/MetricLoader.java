package br.com.pandox.nursery.domain.metric.loader;

import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.monitor.model.Monitor;

import java.util.List;

public interface MetricLoader {

	Metric loadByName(String name);

	Metric loadByID(Long id, boolean loadData);

	List<Metric> loadByMonitor(Monitor monitor);
}
