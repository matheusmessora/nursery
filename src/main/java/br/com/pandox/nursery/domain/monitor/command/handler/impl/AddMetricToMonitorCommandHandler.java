package br.com.pandox.nursery.domain.monitor.command.handler.impl;

import br.com.pandox.nursery.domain.metric.factory.MetricFactory;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.monitor.command.handler.MetricCommandHandler;
import br.com.pandox.nursery.domain.monitor.command.impl.AddMetricToMonitorCommand;
import br.com.pandox.nursery.domain.monitor.entity.repository.MonitorRepository;
import br.com.pandox.nursery.domain.monitor.loader.MonitorLoader;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddMetricToMonitorCommandHandler implements MetricCommandHandler<Metric, AddMetricToMonitorCommand> {

    @Autowired
    private MonitorRepository repository;


    @Autowired
    private MetricFactory metricFactory;

    @Autowired
    private MonitorLoader loader;


    @Override
    public Metric process(AddMetricToMonitorCommand command) {
        Monitor monitor = loader.loadByID(command.getMonitorId());
        Metric metric = metricFactory.fabric(command.getMetricDTO());
        Long metricId = monitor.addMetric(metric, repository);

        monitor = loader.loadByID(command.getMonitorId());
        return monitor.getMetric(metricId);
    }
}
