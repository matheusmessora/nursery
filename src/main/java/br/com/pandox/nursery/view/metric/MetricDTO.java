package br.com.pandox.nursery.view.metric;

import br.com.pandox.nursery.DataTransferObject;
import br.com.pandox.nursery.view.data.DataDTO;
import br.com.pandox.nursery.view.monitor.MonitorDTO;

import java.util.List;

public class MetricDTO implements DataTransferObject {
	private Long id;

	private String name;

	private String type;

	private Integer time_interval;

	private MonitorDTO monitor;

	private List<DataDTO> datas;

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

	public Integer getTime_interval() {
		return time_interval;
	}

	public void setTime_interval(Integer time_interval) {
		this.time_interval = time_interval;
	}

	public MonitorDTO getMonitor() {
		return monitor;
	}

	public void setMonitor(MonitorDTO monitor) {
		this.monitor = monitor;
	}

	public List<DataDTO> getDatas() {
		return datas;
	}

	public void setDatas(List<DataDTO> datas) {
		this.datas = datas;
	}
}
