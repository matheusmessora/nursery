package br.com.pandox.nursery.domain.monitor.loader.impl;

import br.com.pandox.nursery.domain.DomainNotFoundException;
import br.com.pandox.nursery.domain.monitor.loader.MonitorLoader;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.model.MonitorEntity;
import br.com.pandox.nursery.domain.monitor.model.repository.MonitorRepository;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Set;

@Service
public class MonitorLoaderImpl implements MonitorLoader {

    @Autowired
    private MonitorRepository repository;

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

    @Override
    @Transactional(readOnly = true)
    public List<Monitor> loadByMachine(String machine) {
        Iterable<MonitorEntity> all = repository.findByMachineLoadMetrics(machine);

        ImmutableList.Builder<Monitor> builder = ImmutableList.builder();
        return builder.addAll(all).build();
    }

    public List<Monitor> loadAll(boolean loadMetrics) {
        Iterable<MonitorEntity> all;
        if(loadMetrics) {
            all = repository.findAllLoadMetrics();
        }else {
            all = repository.findAll();
        }


        ImmutableList.Builder<Monitor> builder = ImmutableList.builder();
        return builder.addAll(all).build();
    }

}
