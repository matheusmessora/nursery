package br.com.pandox.nursery.domain.metric.loader.impl;

import br.com.pandox.nursery.domain.metric.entity.MetricBuilder;
import br.com.pandox.nursery.domain.metric.entity.MetricEntity;
import br.com.pandox.nursery.domain.metric.entity.repository.MetricRepository;
import br.com.pandox.nursery.domain.metric.factory.MetricFactory;
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

    @Autowired
    private MetricFactory factory;

    @Override
    public Metric loadByName(String name) {
        MetricEntity domain = repository.findByName(name);
        return create(domain, false);
    }

    @Override
    public Metric loadByID(Long id, boolean loadData) {
        MetricEntity domain;
        if(loadData) {
            domain = repository.findOneLoadDatas(id);
        }else {
            domain = repository.findOne(id);
        }

        return create(domain, loadData);
    }

    @Override
    public List<Metric> loadByMonitor(Monitor monitor) {
        Iterable<MetricEntity> all = repository.findByMonitor(monitor);

        ImmutableList.Builder<Metric> builder = ImmutableList.builder();
        return builder.addAll(all).build();
    }

    private Metric create(MetricEntity entity, boolean loadData){
        MetricBuilder metric = new MetricBuilder()
                .setId(entity.getId())
                .setName(entity.getName())
                .setType(entity.getType())
                .setTimeInterval(entity.getTimeInterval());

        if(loadData) {
            metric.setDatas(entity.getDatas());
        }

        return metric.build();
    }
}
