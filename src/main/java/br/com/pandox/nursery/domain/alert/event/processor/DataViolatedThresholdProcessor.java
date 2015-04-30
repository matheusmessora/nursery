package br.com.pandox.nursery.domain.alert.event.processor;

import br.com.pandox.nursery.domain.alert.Alert;
import br.com.pandox.nursery.domain.alert.event.DataViolatedThresdhold;
import br.com.pandox.nursery.domain.alert.factory.AlertFactory;
import br.com.pandox.nursery.domain.alert.service.AlertService;
import br.com.pandox.nursery.domain.metric.loader.MetricLoader;
import br.com.pandox.nursery.domain.metric.model.Metric;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataViolatedThresholdProcessor {

    @Autowired
    private EventBus eventBus;

    @Autowired
    private AlertService alertService;

    @Autowired
    private AlertFactory alertFactory;

    @Autowired
    private MetricLoader metricLoader;

    public DataViolatedThresholdProcessor() {
    }

    @Deprecated
    public void injectAlertservice(AlertService alertService) {
        this.alertService = alertService;
    }

    @PostConstruct
    public void init() {
        eventBus.register(this);
    }

    @Subscribe
    public void process(DataViolatedThresdhold event) {
        Metric metric = metricLoader.loadByID(event.getMetricId(), false, true);
        if(!metric.getAlerts().isEmpty()){
            return;
        }

        Alert alert = alertFactory.from(metric, event.getData());
        alertService.create(alert);
    }
}
