package br.com.pandox.nursery.domain.metric.loader.impl;

import br.com.pandox.nursery.domain.metric.entity.MetricEntity;
import br.com.pandox.nursery.domain.metric.entity.repository.MetricRepository;
import br.com.pandox.nursery.domain.metric.loader.MetricLoader;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetricLoaderImpl implements MetricLoader {

    @Autowired
    private MetricRepository repository;

    @Override
    public Metric loadByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Metric loadByID(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Metric> loadByMonitor(Monitor monitor) {
        Iterable<MetricEntity> all = repository.findByMonitor(monitor);

        ImmutableList.Builder<Metric> builder = ImmutableList.builder();
        ImmutableList<Metric> models = builder.addAll(all).build();
        return models;
    }
}
