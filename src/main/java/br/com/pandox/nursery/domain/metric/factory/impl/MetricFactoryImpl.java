package br.com.pandox.nursery.domain.metric.factory.impl;

import br.com.pandox.nursery.domain.metric.factory.MetricFactory;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.metric.model.MetricBuilder;
import br.com.pandox.nursery.domain.metric.model.MetricEntity;
import br.com.pandox.nursery.domain.metric.model.vo.MetricData;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.view.rest.metric.MetricDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;

@Service
public class MetricFactoryImpl implements MetricFactory {

	@Override
	public Metric from(MetricEntity entity, boolean loadData) {
		Assert.notNull(entity, "MetricEntity must not be null");

		Monitor monitor = entity.getMonitor();

		MetricBuilder builder = new MetricBuilder()
				.setId(entity.getId())
				.setType(entity.getType())
				.setTimeInterval(entity.getTimeInterval())
				.setName(entity.getName())
				.setMonitor(monitor)
				.setMaxValue(entity.getMaxValue());

		if(loadData){
			Set<MetricData> datas = new HashSet<>();
			for (MetricData dataEntity : entity.getDatas()) {
				datas.add(dataEntity);
			}
			builder.setDatas(datas);
		}

		return builder.build();
	}


	@Override
	public Metric from(MetricDTO metricDTO) {
		Assert.notNull(metricDTO, "MetricDTO must not be null");

		MetricBuilder builder = new MetricBuilder()
				.setName(metricDTO.getName())
				.setType(metricDTO.getType())
				.setTimeInterval(metricDTO.getTime_interval())
				.setMaxValue(metricDTO.getMax_value());

		return builder.build();
	}



}
