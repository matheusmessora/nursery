package br.com.pandox.nursery.domain.metric.service;

import br.com.pandox.nursery.domain.metric.entity.MetricDataEntity;
import br.com.pandox.nursery.domain.metric.entity.MetricEntity;
import br.com.pandox.nursery.domain.metric.entity.repository.MetricRepository;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.metric.model.vo.MetricData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MetricService {


    @Autowired
    private MetricRepository repository;

    public void save(Metric metric) {
        List<MetricDataEntity> dataEntities = new ArrayList<>();
        MetricEntity entity = new MetricEntity(metric.getId(), metric.getName(), metric.getType(), metric.getTimeInterval());

        for (MetricData data : metric.getDatas()) {
            MetricDataEntity dataEntity = new MetricDataEntity(data.getValue(), data.getDateCreation());
            dataEntity.setMetric(entity);
            dataEntities.add(dataEntity);
        }

        entity.setDatas(dataEntities);

        repository.save(entity);
    }
}
