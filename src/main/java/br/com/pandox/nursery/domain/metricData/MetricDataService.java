package br.com.pandox.nursery.domain.metricData;

import br.com.pandox.nursery.domain.metric.model.vo.MetricData;

public interface MetricDataService {

    MetricData create(Integer value, Long metricId);
}
