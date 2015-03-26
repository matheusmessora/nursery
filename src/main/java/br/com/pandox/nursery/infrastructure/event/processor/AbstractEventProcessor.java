package br.com.pandox.nursery.infrastructure.event.processor;

import br.com.pandox.nursery.infrastructure.event.Event;
import br.com.pandox.nursery.infrastructure.event.listener.EventListener;
import br.com.pandox.nursery.infrastructure.event.processor.EventProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public abstract class AbstractEventProcessor<R extends Event> implements EventProcessor<R> {

    @Autowired
    private EventListener eventListener;

    @PostConstruct
    public void init() {
        eventListener.addProcessor(this);
    }
}
