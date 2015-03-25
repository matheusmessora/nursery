package br.com.pandox.nursery.domain.monitor.loader.impl;

import br.com.pandox.nursery.domain.DomainNotFoundException;
import br.com.pandox.nursery.domain.monitor.entity.MonitorEntity;
import br.com.pandox.nursery.domain.monitor.entity.repository.MonitorRepository;
import br.com.pandox.nursery.domain.monitor.loader.MonitorLoader;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.service.MonitorService;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class MonitorLoaderImpl implements MonitorLoader {

    @Autowired
    private MonitorService service;

    @Autowired
    private MonitorRepository repository;

    public Monitor loadByID(Long id) {
        Assert.notNull(id, "MonitorID must not be null");

        Monitor monitor = service.findByID(id);
        if(monitor == null) {
            throw new DomainNotFoundException();
        }
        return monitor;
    }

    @Override
    public Optional<MonitorEntity> loadByName(String name) {
        MonitorEntity entity = repository.findByName(name);
        return Optional.of(entity);
    }

    public List<Monitor> loadAll() {
        return service.loadAll();
    }
}
