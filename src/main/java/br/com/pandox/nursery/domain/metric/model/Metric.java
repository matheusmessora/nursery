package br.com.pandox.nursery.domain.metric.model;

import br.com.pandox.nursery.Model;
import br.com.pandox.nursery.domain.monitor.model.Monitor;

public interface Metric extends Model {

	Long getId();

//	Monitor getMonitor();

	String getName();

	String getType();

	Integer getTimeInterval();

	Monitor getMonitor();

}
