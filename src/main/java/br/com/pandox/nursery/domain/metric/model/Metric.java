package br.com.pandox.nursery.domain.metric.model;

import br.com.pandox.nursery.Model;

public interface Metric extends Model {

	Long getId();

//	Monitor getMonitor();

	String getName();

	String getType();

	Integer getTimeInterval();

}
