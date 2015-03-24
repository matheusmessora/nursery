package br.com.pandox.nursery.domain.monitor.command.handler.impl;

import br.com.pandox.nursery.domain.metric.factory.MetricFactory;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.monitor.command.handler.MonitorCommandHandler;
import br.com.pandox.nursery.domain.monitor.command.impl.AddMetricToMonitorCommand;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.entity.repository.MonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddMetricToMonitorCommandHandler implements MonitorCommandHandler<Monitor, AddMetricToMonitorCommand> {

	@Autowired private MonitorRepository repository;

	@Autowired private MetricFactory metricFactory;


	@Override
	public Monitor process(AddMetricToMonitorCommand command) {
		Monitor monitor = command.getMonitor();
		Metric metric = metricFactory.fabric(command.getMetricDTO());
		monitor.addMetric(metric, repository);
		return monitor;
	}
}
