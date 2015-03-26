package br.com.pandox.nursery.domain.monitor.loader.impl;

import br.com.pandox.nursery.domain.DomainNotFoundException;
import br.com.pandox.nursery.domain.monitor.entity.MonitorEntity;
import br.com.pandox.nursery.domain.monitor.entity.repository.MonitorRepository;
import br.com.pandox.nursery.domain.monitor.factory.MonitorFactory;
import br.com.pandox.nursery.domain.monitor.loader.MonitorLoader;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class MonitorLoaderImpl implements MonitorLoader {

    @Autowired
    private MonitorRepository repository;

    @Autowired
    private MonitorFactory factory;

    public Monitor loadByID(Long id, boolean loadMetrics) {
        Assert.notNull(id, "MonitorID must not be null");

        MonitorEntity entity;

        if(loadMetrics) {
            entity = repository.findOneLoadMetrics(id);
        }else {
            entity = repository.findOne(id);
        }

        if(entity == null) {
            throw new DomainNotFoundException();
        }
        return factory.createFrom(entity, loadMetrics);
    }

    @Override
    public Optional<Monitor> loadByName(String name) {
        MonitorEntity entity = repository.findByName(name);
        Monitor domain = null;
        if(entity != null) {
            domain = factory.createFrom(entity, false);
        }

        return Optional.of(domain);
    }

    public List<Monitor> loadAll() {
        Iterable<MonitorEntity> all = repository.findAll();

        List<Monitor> monitors = new ArrayList<>();
        for (MonitorEntity monitorEntity : all) {
            Monitor monitor = factory.createFrom(monitorEntity, false);
            monitors.add(monitor);
        }

        ImmutableList.Builder<Monitor> builder = ImmutableList.builder();
        return builder.addAll(monitors).build();
    }

}
