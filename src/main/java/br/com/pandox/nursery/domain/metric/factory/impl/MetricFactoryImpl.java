package br.com.pandox.nursery.domain.metric.factory.impl;

import br.com.pandox.nursery.domain.metric.factory.MetricFactory;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.metric.model.MetricBuilder;
import br.com.pandox.nursery.domain.metric.model.MetricEntity;
import br.com.pandox.nursery.domain.metric.model.vo.MetricData;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.view.rest.Link;
import br.com.pandox.nursery.view.rest.data.DataDTO;
import br.com.pandox.nursery.view.rest.metric.MetricDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;

@Service
public class MetricFactoryImpl implements MetricFactory {

	@Override
	public Metric createFrom(MetricEntity entity, boolean loadData) {
		Assert.notNull(entity, "MetricEntity must not be null");

		Monitor monitor = entity.getMonitor();

		MetricBuilder builder = new MetricBuilder()
				.setId(entity.getId())
				.setType(entity.getType())
				.setTimeInterval(entity.getTimeInterval())
				.setName(entity.getName())
				.setMonitor(monitor);

		if(loadData){
			ArrayList<MetricData> datas = new ArrayList<MetricData>();
			for (MetricData dataEntity : entity.getDatas()) {
				datas.add(dataEntity);
			}
			builder.setDatas(datas);
		}

		return builder.build();
	}

	@Override
	public Metric createFrom(MetricDTO metricDTO) {
		return new MetricBuilder()
				.setName(metricDTO.getName())
				.setType(metricDTO.getType())
				.setTimeInterval(metricDTO.getTime_interval())
				.build();
	}

	public MetricDTO fabric(Metric metric){
		MetricDTO dto = new MetricDTO();
		dto.setId(metric.getId());
		dto.setName(metric.getName());
		dto.setTime_interval(metric.getTimeInterval());
		dto.setType(metric.getType());

		ArrayList<DataDTO> datas = new ArrayList<>();
		if(metric.isDatasLoaded()){
			for (MetricData data : metric.getDatas()) {
				datas.add(new DataDTO(data.getValue(), data.getDateCreation()));
			}
		}

		dto.setDatas(datas);

		dto.addLink(new Link("/api/vSNAPSHOT/data", "create-data"));
		dto.addLink(new Link("/api/vSNAPSHOT/metric/" + dto.getId() + "?load=true", "fetch-data"));

		return dto;
	}


}
