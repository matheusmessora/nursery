package br.com.pandox.nursery.domain.metric.model.vo;

import br.com.pandox.nursery.domain.metric.entity.MetricDataEntity;
import org.springframework.stereotype.Service;

@Service
public class MetricDataFactoryImpl implements MetricDataFactory {


    @Override
    public MetricData createFrom(MetricDataEntity entity) {
        return new MetricDataBuilder()
                .setValue(entity.getValue())
                .setDateCreation(entity.getDateCreation())
                .build();
    }

    @Override
    public MetricData createWith(Integer value) {
        return new MetricDataBuilder()
                .setValue(value)
                .build();
    }
}
