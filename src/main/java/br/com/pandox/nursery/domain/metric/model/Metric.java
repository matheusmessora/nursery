package br.com.pandox.nursery.domain.metric.model;

import br.com.pandox.nursery.Model;
import br.com.pandox.nursery.domain.alert.Alert;
import br.com.pandox.nursery.domain.metric.model.vo.MetricData;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.threshold.model.Threshold;
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

	List<Threshold> getThresholds();

	void addThreshold(Threshold threshold);

	List<Alert> getAlerts();


}
