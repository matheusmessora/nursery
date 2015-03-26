package br.com.pandox.nursery.domain.monitor.event.processor;

import br.com.pandox.nursery.domain.monitor.event.CreateMonitorEvent;
import br.com.pandox.nursery.domain.monitor.sevice.MonitorService;
import br.com.pandox.nursery.infrastructure.event.processor.AbstractEventProcessor;
import br.com.pandox.nursery.infrastructure.event.processor.EventProcessor;
import com.google.common.eventbus.Subscribe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateMonitorEventProcessor extends AbstractEventProcessor<CreateMonitorEvent> implements EventProcessor<CreateMonitorEvent> {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private MonitorService monitorService;

    @Override
    @Subscribe
    public void process(CreateMonitorEvent event) {
        monitorService.save(event.getMonitor());
        LOGGER.info("CreateMonitorEvent processed");
    }
}
