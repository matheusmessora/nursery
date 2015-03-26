package br.com.pandox.nursery.domain.monitor.event.processor;

import br.com.pandox.nursery.domain.monitor.event.NewMetricEvent;
import br.com.pandox.nursery.domain.monitor.sevice.MonitorService;
import br.com.pandox.nursery.infrastructure.event.processor.AbstractEventProcessor;
import br.com.pandox.nursery.infrastructure.event.processor.EventProcessor;
import com.google.common.eventbus.Subscribe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewMetricEventProcessor extends AbstractEventProcessor<NewMetricEvent> implements EventProcessor<NewMetricEvent> {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private MonitorService monitorService;

    @Subscribe
    public void process(NewMetricEvent event) {
        monitorService.save(event.getMonitor());
        LOGGER.info("NewMetricEvent processed");
    }
}
