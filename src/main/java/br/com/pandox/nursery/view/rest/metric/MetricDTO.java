package br.com.pandox.nursery.view.rest.metric;

import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.metric.model.vo.Edge;
import br.com.pandox.nursery.domain.metric.model.vo.MetricData;
import br.com.pandox.nursery.view.rest.AbstractDTO;
import br.com.pandox.nursery.view.rest.Link;
import br.com.pandox.nursery.view.rest.data.DataDTO;
import br.com.pandox.nursery.view.rest.monitor.MonitorDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.Optional;

import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MetricDTO extends AbstractDTO {

	public MetricDTO() {
	}
	
	public MetricDTO(Metric metric){
		this.setId(metric.getId());
		this.setName(metric.getName());
		this.setTime_interval(metric.getTimeInterval());
		this.setType(metric.getType());
		this.setMax_value(metric.getMaxValue());

		Optional<Edge> edge = metric.getEdge();
		if(edge.isPresent()){
			this.setEdgeLowValue(edge.get().getLowest());
			this.setEdgeHighValue(edge.get().getHighest());
		}

		ArrayList<DataDTO> datas = new ArrayList<>();
		if(metric.isDatasLoaded()){
			for (MetricData data : metric.getDatas()) {
				datas.add(new DataDTO(data.getValue(), data.getDateCreation()));
			}
		}
		this.setDatas(datas);

		this.addLink(new Link("/api/data", "create-data"));
		this.addLink(new Link("/api/metric/" + this.getId() + "?load=true", "fetch-data"));
	}

	private Long id;

	private String name;

	private String type;

	private Integer time_interval;

	private MonitorDTO monitor;

	private List<DataDTO> datas;

	private Integer max_value;

	private Integer edgeLowValue;
	private Integer edgeHighValue;

	public Integer getMax_value() {
		return max_value;
	}

	public void setMax_value(Integer max_value) {
		this.max_value = max_value;
	}

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

	public Integer getEdgeHighValue() {
		return edgeHighValue;
	}

	public void setEdgeHighValue(Integer edgeHighValue) {
		this.edgeHighValue = edgeHighValue;
	}

	public Integer getEdgeLowValue() {
		return edgeLowValue;
	}

	public void setEdgeLowValue(Integer edgeLowValue) {
		this.edgeLowValue = edgeLowValue;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("MetricDTO{");
		sb.append("id=").append(id);
		sb.append(", name='").append(name).append('\'');
		sb.append(", type='").append(type).append('\'');
		sb.append(", time_interval=").append(time_interval);
		sb.append(", monitor=").append(monitor);
		sb.append(", datas=").append(datas);
		sb.append(", max_value=").append(max_value);
		sb.append(", edgeLowValue=").append(edgeLowValue);
		sb.append(", edgeHighValue=").append(edgeHighValue);
		sb.append('}');
		return sb.toString();
	}
}
