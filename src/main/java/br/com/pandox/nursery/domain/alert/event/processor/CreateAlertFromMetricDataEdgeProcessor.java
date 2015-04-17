package br.com.pandox.nursery.domain.alert.event.processor;

import br.com.pandox.nursery.domain.alert.Alert;
import br.com.pandox.nursery.domain.alert.event.CreateAlertFromMetricDataEdge;
import br.com.pandox.nursery.domain.alert.factory.AlertFactory;
import br.com.pandox.nursery.domain.alert.service.AlertService;
import br.com.pandox.nursery.infrastructure.event.processor.AbstractEventProcessor;
import br.com.pandox.nursery.infrastructure.event.processor.EventProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateAlertFromMetricDataEdgeProcessor
        extends AbstractEventProcessor<CreateAlertFromMetricDataEdge>
        implements EventProcessor<CreateAlertFromMetricDataEdge> {

    @Autowired
    private AlertService alertService;

    @Autowired
    private AlertFactory alertFactory;

    @Override
    public void process(CreateAlertFromMetricDataEdge event) {
        Alert alert = alertFactory.from(event.getMetric());


    }
}
