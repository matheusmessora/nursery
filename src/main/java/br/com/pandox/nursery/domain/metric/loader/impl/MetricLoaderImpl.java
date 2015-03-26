package br.com.pandox.nursery.domain.metric.loader.impl;

import br.com.pandox.nursery.domain.DomainNotFoundException;
import br.com.pandox.nursery.domain.metric.entity.MetricEntity;
import br.com.pandox.nursery.domain.metric.entity.repository.MetricRepository;
import br.com.pandox.nursery.domain.metric.factory.MetricFactory;
import br.com.pandox.nursery.domain.metric.loader.MetricLoader;
import br.com.pandox.nursery.domain.metric.model.Metric;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MetricLoaderImpl implements MetricLoader {

    @Autowired
    private MetricRepository repository;

    @Autowired
    private MetricFactory factory;

    @Override
    public Metric loadByName(String name) {
        MetricEntity entity = repository.findByName(name);
        return factory.createFrom(entity, false);
    }

    @Override
    public Metric loadByID(Long id, boolean loadData) {
        MetricEntity entity;
        if(loadData) {
            entity = repository.findOneLoadDatas(id);
        }else {
            entity = repository.findOne(id);
        }

        if(entity == null) {
            throw new DomainNotFoundException();
        }
        return factory.createFrom(entity, loadData);
    }

    @Override
    public List<Metric> loadByMonitorID(Long monitorId) {
        Iterable<MetricEntity> all = repository.findByMonitor_Id(monitorId);

        List<Metric> metrics = new ArrayList<>();
        for (MetricEntity metricEntity : all) {
            Metric metric = factory.createFrom(metricEntity, false);
            metrics.add(metric);
        }

        ImmutableList.Builder<Metric> builder = ImmutableList.builder();
        return builder.addAll(metrics).build();
    }

}
