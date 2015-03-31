package br.com.pandox.nursery.domain.metric.loader.impl;

import br.com.pandox.nursery.domain.DomainNotFoundException;
import br.com.pandox.nursery.domain.metric.loader.MetricLoader;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.metric.model.MetricEntity;
import br.com.pandox.nursery.domain.metric.model.repository.MetricDataRepository;
import br.com.pandox.nursery.domain.metric.model.repository.MetricRepository;
import com.google.common.collect.ImmutableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetricLoaderImpl implements MetricLoader {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private MetricRepository repository;

    @Autowired
    private MetricDataRepository metricDataRepository;

    @Override
    public Metric loadByName(String name) {
        MetricEntity entity = repository.findByName(name);
        if(entity == null) {
            throw new DomainNotFoundException();
        }

        return entity;
    }

    @Override
    public Metric loadByID(Long id, boolean loadData) {
        MetricEntity entity;
        if(loadData) {
            entity = repository.findOneLoadDatas(id);
            entity.setDataLoaded(true);
        }else {
            entity = repository.findOne(id);
        }

        if(entity == null) {
            throw new DomainNotFoundException();
        }
        return entity;
    }

    @Override
    public List<Metric> loadByMonitorID(Long monitorId) {
        Iterable<MetricEntity> all = repository.findByMonitor_Id(monitorId);

        ImmutableList.Builder<Metric> builder = ImmutableList.builder();
        return builder.addAll(all).build();
    }

}
