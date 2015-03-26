package br.com.pandox.nursery.infrastructure.event.processor;

import br.com.pandox.nursery.infrastructure.event.Event;

public interface EventProcessor<E extends Event> {

    void process(E event);
}
