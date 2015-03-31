package br.com.pandox.nursery.domain.metric.model;

import br.com.pandox.nursery.Model;
import br.com.pandox.nursery.domain.metric.model.vo.MetricData;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.infrastructure.event.listener.EventListener;

import java.util.List;

public interface Metric extends Model {

	Long getId();

	String getName();

	String getType();

	Integer getTimeInterval();

	Monitor getMonitor();

	void addData(MetricData data, EventListener eventListener);

	List<MetricData> getDatas();

	boolean isDatasLoaded();

}
