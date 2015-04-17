package br.com.pandox.nursery.domain.alert.factory;

import br.com.pandox.nursery.domain.alert.Alert;
import br.com.pandox.nursery.domain.metric.model.Metric;

public interface AlertFactory {

    Alert from(Metric metric);


}
