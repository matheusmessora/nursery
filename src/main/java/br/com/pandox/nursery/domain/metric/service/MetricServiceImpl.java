package br.com.pandox.nursery.domain.metric.service;

import br.com.pandox.nursery.domain.CommandException;
import br.com.pandox.nursery.domain.DomainNotFoundException;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.metric.model.MetricEntity;
import br.com.pandox.nursery.domain.metric.model.repository.MetricRepository;
import br.com.pandox.nursery.domain.monitor.loader.MonitorLoader;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MetricServiceImpl implements MetricService {

    @Autowired
    private MetricRepository repository;

    @Autowired
    private MonitorLoader monitorLoader;

    @Override
    public Metric create(Metric metric, Long monitorId) {
        Monitor monitor = load(monitorId);

        monitor.addMetric(metric);

        MetricEntity metricEntity = (MetricEntity) metric;
        metricEntity.setMonitor(monitor);
        metricEntity = repository.save(metricEntity);
        return metricEntity;
    }

    @Override
    @Transactional
    public Metric update(Metric metric) {
        return repository.save((MetricEntity) metric);
    }

    private Monitor load(Long monitorId){
        try {
            return monitorLoader.loadByID(monitorId, true);
        }catch(DomainNotFoundException ex){
            throw new CommandException(String.format("Given monitor with id [%s] not found", monitorId));
        }
    }
}
