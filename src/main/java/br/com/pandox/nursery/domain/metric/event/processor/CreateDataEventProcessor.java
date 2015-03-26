package br.com.pandox.nursery.domain.metric.event.processor;

import br.com.pandox.nursery.domain.metric.event.CreateDataEvent;
import br.com.pandox.nursery.domain.metric.service.MetricService;
import br.com.pandox.nursery.infrastructure.event.processor.AbstractEventProcessor;
import br.com.pandox.nursery.infrastructure.event.processor.EventProcessor;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateDataEventProcessor extends AbstractEventProcessor<CreateDataEvent> implements EventProcessor<CreateDataEvent> {

    @Autowired
    private MetricService metricService;

    @Override
    @Subscribe
    public void process(CreateDataEvent event) {
        metricService.save(event.getMetric());
    }
}
