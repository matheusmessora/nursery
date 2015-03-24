package br.com.pandox.nursery.view.metric;

import br.com.pandox.nursery.DataTransferObject;
import br.com.pandox.nursery.view.monitor.MonitorDTO;

public class MetricDTO implements DataTransferObject {
	private Long id;

	private String name;

	private String type;

	private Integer timeInterval;

	private MonitorDTO monitor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(Integer timeInterval) {
		this.timeInterval = timeInterval;
	}

	public MonitorDTO getMonitor() {
		return monitor;
	}

	public void setMonitor(MonitorDTO monitor) {
		this.monitor = monitor;
	}
}
