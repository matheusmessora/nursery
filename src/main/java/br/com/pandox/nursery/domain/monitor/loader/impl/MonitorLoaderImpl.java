package br.com.pandox.nursery.domain.monitor.loader.impl;

import br.com.pandox.nursery.domain.DomainNotFoundException;
import br.com.pandox.nursery.domain.monitor.loader.MonitorLoader;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class MonitorLoaderImpl implements MonitorLoader {

    @Autowired
    private MonitorService service;

    public Monitor loadByID(Long id) {
        Assert.notNull(id);
        Monitor monitor = service.findByID(id);
        if(monitor == null) {
            throw new DomainNotFoundException();
        }
        return monitor;
    }

    public List<Monitor> loadAll() {
        return service.loadAll();
    }
}
