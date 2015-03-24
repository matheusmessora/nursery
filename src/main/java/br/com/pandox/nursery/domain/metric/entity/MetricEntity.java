package br.com.pandox.nursery.domain.metric.entity;

import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.entity.MonitorEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MetricEntity implements Metric {

	@Deprecated
	public MetricEntity() {
	}

	protected MetricEntity(String name, String type, Integer timeInterval, Monitor monitor) {
		this.name = name;
		this.type = type;
		this.timeInterval = timeInterval;
		this.monitor = (MonitorEntity) monitor;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String name;

	@Column
	private String type;

	@Column
	private Integer timeInterval;

	@JoinColumn
	@ManyToOne(fetch = FetchType.EAGER)
	private MonitorEntity monitor;

	@Override public Long getId() {
		return id;
	}

	@Override public Monitor getMonitor() {
		return monitor;
	}

	@Override public String getName() {
		return name;
	}

	@Override public String getType() {
		return type;
	}

	@Override public Integer getTimeInterval() {
		return timeInterval;
	}
}
