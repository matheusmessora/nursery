package br.com.pandox.nursery.infrastructure.event.listener;

import br.com.pandox.nursery.infrastructure.event.Event;
import br.com.pandox.nursery.infrastructure.event.processor.EventProcessor;

public interface EventListener {

    void addProcessor(EventProcessor processor);

    void post(Event event);
}
