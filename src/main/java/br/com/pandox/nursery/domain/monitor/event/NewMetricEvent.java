package br.com.pandox.nursery.domain.monitor.event;

import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.infrastructure.event.Event;

public class NewMetricEvent implements Event {

    private Monitor monitor;

    public NewMetricEvent(Monitor monitor) {
        this.monitor = monitor;
    }

    public Monitor getMonitor() {
        return monitor;
    }
}
