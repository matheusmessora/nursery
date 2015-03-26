package br.com.pandox.nursery.domain.metric.factory.impl;

import br.com.pandox.nursery.domain.metric.entity.MetricBuilder;
import br.com.pandox.nursery.domain.metric.factory.MetricFactory;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.metric.model.MetricData;
import br.com.pandox.nursery.view.data.DataDTO;
import br.com.pandox.nursery.view.metric.MetricDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MetricFactoryImpl implements MetricFactory {


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
		for (MetricData data : metric.getDatas()) {
			datas.add(new DataDTO(data.getValue(), data.getDateCreation()));
		}

		dto.setDatas(datas);

		return dto;
	}
}
