package br.com.pandox.nursery.domain.metric.loader.impl;

import br.com.pandox.nursery.domain.metric.entity.repository.MetricRepository;
import br.com.pandox.nursery.domain.metric.loader.MetricLoader;
import br.com.pandox.nursery.domain.metric.model.Metric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
