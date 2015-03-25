package br.com.pandox.nursery.domain.monitor.loader.impl;

import br.com.pandox.nursery.domain.DomainNotFoundException;
import br.com.pandox.nursery.domain.monitor.entity.MonitorEntity;
import br.com.pandox.nursery.domain.monitor.entity.repository.MonitorRepository;
import br.com.pandox.nursery.domain.monitor.loader.MonitorLoader;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
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

    public Monitor loadByID(Long id, boolean loadMetrics) {
        Assert.notNull(id, "MonitorID must not be null");

        Monitor monitor;

        if(loadMetrics) {
            monitor = repository.findOneLoadMetrics(id);
        }else {
            monitor = repository.findOne(id);
        }


        if(monitor == null) {
            throw new DomainNotFoundException();
        }
        return monitor;
    }

    @Override
    public Optional<Monitor> loadByName(String name) {
        Monitor entity = repository.findByName(name);
        return Optional.of(entity);
    }

    public List<Monitor> loadAll() {
        Iterable<MonitorEntity> all = repository.findAll();

        ImmutableList.Builder<Monitor> builder = ImmutableList.builder();
        ImmutableList<Monitor> monitors = builder.addAll(all).build();
        return monitors;
    }
}
