package br.com.pandox.nursery.domain.metric.model;

import br.com.pandox.nursery.Model;
import br.com.pandox.nursery.domain.metric.entity.repository.MetricRepository;
import br.com.pandox.nursery.domain.monitor.model.Monitor;

import java.util.List;

public interface Metric extends Model {

	Long getId();

	String getName();

	String getType();

	Integer getTimeInterval();

	Monitor getMonitor();

	void addData(Integer value, MetricRepository repository);

	List<MetricData> getDatas();

}
