package br.com.pandox.nursery.domain.monitor.loader.impl;

import br.com.pandox.nursery.domain.DomainNotFoundException;
import br.com.pandox.nursery.domain.monitor.factory.MonitorFactory;
import br.com.pandox.nursery.domain.monitor.loader.MonitorLoader;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.model.MonitorEntity;
import br.com.pandox.nursery.domain.monitor.model.repository.MonitorRepository;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
        return entity;
    }

    @Override
    public Optional<Monitor> loadByName(String name) {
        Monitor entity = repository.findByName(name);

        return Optional.fromNullable(entity);
    }

    @Override
    public Optional<Monitor> loadByMachineAndName(String name, String machine) {
        Monitor entity = repository.findByMachineAndName(machine, name);

        return Optional.fromNullable(entity);
    }

    public List<Monitor> loadAll() {
        Iterable<MonitorEntity> all = repository.findAll();

        ImmutableList.Builder<Monitor> builder = ImmutableList.builder();
        return builder.addAll(all).build();
    }

}
