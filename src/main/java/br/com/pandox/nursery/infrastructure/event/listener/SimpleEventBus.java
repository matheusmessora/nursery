package br.com.pandox.nursery.infrastructure.event.listener;

import br.com.pandox.nursery.infrastructure.event.Event;
import br.com.pandox.nursery.infrastructure.event.processor.EventProcessor;
import com.google.common.eventbus.EventBus;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SimpleEventBus implements EventListener {

    private EventBus eventBus;

    @PostConstruct
    public void init(){
        eventBus = new EventBus();
    }

    @Override
    public void addProcessor(EventProcessor processor) {
        eventBus.register(processor);
    }

    @Override
    public void post(Event event) {
        eventBus.post(event);
    }

}
