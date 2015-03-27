package br.com.pandox.nursery.domain.metric.model.vo;

import br.com.pandox.nursery.domain.metric.model.MetricDataEntity;

public interface MetricDataFactory {

    MetricData createFrom(MetricDataEntity entity);

    MetricData createWith(Integer value);
}

