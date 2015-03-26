package br.com.pandox.nursery.domain.metric.model;

import br.com.pandox.nursery.Model;
import br.com.pandox.nursery.domain.metric.model.vo.MetricData;
import br.com.pandox.nursery.domain.metric.service.MetricService;
import br.com.pandox.nursery.domain.monitor.model.Monitor;

import java.util.List;

public interface Metric extends Model {

	Long getId();

	String getName();

	String getType();

	Integer getTimeInterval();

	Monitor getMonitor();

	void addData(MetricData data, MetricService service);

	List<MetricData> getDatas();

	boolean isDatasLoaded();

}
