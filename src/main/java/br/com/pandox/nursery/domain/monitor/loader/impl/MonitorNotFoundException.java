package br.com.pandox.nursery.domain.monitor.loader.impl;

import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.infrastructure.exception.DomainNotFoundException;

public class MonitorNotFoundException extends DomainNotFoundException {

    public MonitorNotFoundException() {
        super(Monitor.class);
    }
}
