package br.com.pandox.nursery.domain.metric.factory.impl;

import br.com.pandox.nursery.domain.metric.entity.MetricBuilder;
import br.com.pandox.nursery.domain.metric.factory.MetricFactory;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.view.metric.MetricDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MetricFactoryImpl implements MetricFactory {


	@Override
	public Metric fabric(MetricDTO metricDTO) {
		return new MetricBuilder()
				.setName(metricDTO.getName())
				.setType(metricDTO.getType())
				.setTimeInterval(metricDTO.getTimeInterval())
				.build();
	}

	public MetricDTO fabric(Metric metric){
		MetricDTO dto = new MetricDTO();
		BeanUtils.copyProperties(metric, dto);
		return dto;
	}
}
