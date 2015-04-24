package br.com.pandox.nursery.domain.alert.factory;

import br.com.pandox.nursery.domain.alert.Alert;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.metric.model.vo.MetricData;

public interface AlertFactory {

    Alert from(Metric metric, MetricData data);
}
