package br.com.pandox.nursery.domain.monitor.command.impl;

import br.com.pandox.nursery.domain.monitor.command.MonitorCommand;
import br.com.pandox.nursery.domain.monitor.command.handler.MonitorCommandHandler;
import br.com.pandox.nursery.domain.monitor.command.handler.impl.AddMetricToMonitorCommandHandler;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.view.metric.MetricDTO;

public class AddMetricToMonitorCommand implements MonitorCommand {

	private Monitor monitor;
	private MetricDTO metricDTO;

	public AddMetricToMonitorCommand(Monitor syncedMonitor, MetricDTO metricDTO) {
		this.monitor = syncedMonitor;
		this.metricDTO = metricDTO;
	}

	public Monitor getMonitor() {
		return monitor;
	}

	public MetricDTO getMetricDTO() {
		return metricDTO;
	}

	@Override
	public Class<? extends MonitorCommandHandler> getExecutorType() {
		return AddMetricToMonitorCommandHandler.class;
	}
}
