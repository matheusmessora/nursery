package br.com.pandox.nursery.domain.alert.factory.impl;

import br.com.pandox.nursery.domain.alert.Alert;
import br.com.pandox.nursery.domain.alert.factory.AlertFactory;
import br.com.pandox.nursery.domain.alert.model.AlertBuilder;
import br.com.pandox.nursery.domain.metric.model.Metric;
import org.springframework.stereotype.Component;

@Component
public class AlertFactoryImpl implements AlertFactory {

    @Override
    public Alert from(Metric metric) {
        return new AlertBuilder()
                .setMetric(metric)
                .build();
    }
}
