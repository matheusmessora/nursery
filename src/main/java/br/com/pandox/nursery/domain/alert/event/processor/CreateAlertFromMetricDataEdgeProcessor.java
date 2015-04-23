package br.com.pandox.nursery.domain.alert.event.processor;

import br.com.pandox.nursery.domain.alert.Alert;
import br.com.pandox.nursery.domain.alert.event.CreateAlertFromMetricDataEdge;
import br.com.pandox.nursery.domain.alert.factory.AlertFactory;
import br.com.pandox.nursery.domain.alert.service.AlertService;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CreateAlertFromMetricDataEdgeProcessor {

    @Autowired
    private EventBus eventBus;

    @Autowired
    private AlertService alertService;

    @Autowired
    private AlertFactory alertFactory;

    public CreateAlertFromMetricDataEdgeProcessor() {
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
    public void process(CreateAlertFromMetricDataEdge event) {
        Alert alert = alertFactory.from(event.getMetric());
        alertService.create(alert);
    }
}
