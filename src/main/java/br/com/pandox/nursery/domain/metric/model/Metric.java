package br.com.pandox.nursery.domain.metric.model;

import br.com.pandox.nursery.Model;
import br.com.pandox.nursery.domain.alert.Alert;
import br.com.pandox.nursery.domain.metric.model.vo.Edge;
import br.com.pandox.nursery.domain.metric.model.vo.MetricData;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import com.google.common.base.Optional;
import com.google.common.eventbus.EventBus;

import java.util.List;

public interface Metric extends Model {

	Long getId();

	String getName();

	String getType();

	Integer getTimeInterval();

	Integer getMaxValue();

	Monitor getMonitor();

	void addData(MetricData data, EventBus eventBus);

	List<MetricData> getDatas();

	boolean isDatasLoaded();

	void addEdge(Edge edge);

	Optional<Edge> getEdge();

	List<Alert> getAlerts();


}
