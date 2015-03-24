package br.com.pandox.nursery.domain.monitor.command.impl;

import br.com.pandox.nursery.domain.monitor.command.MetricCommand;
import br.com.pandox.nursery.domain.monitor.command.handler.MetricCommandHandler;
import br.com.pandox.nursery.domain.monitor.command.handler.impl.AddMetricToMonitorCommandHandler;
import br.com.pandox.nursery.view.metric.MetricDTO;

public class AddMetricToMonitorCommand implements MetricCommand {

	private Long monitorId;
	private MetricDTO metricDTO;

	public AddMetricToMonitorCommand(Long monitorId, MetricDTO metricDTO) {
		this.monitorId = monitorId;
		this.metricDTO = metricDTO;
	}

	public Long getMonitorId() {
		return monitorId;
	}

	public MetricDTO getMetricDTO() {
		return metricDTO;
	}

	@Override
	public Class<? extends MetricCommandHandler> getExecutorType() {
		return AddMetricToMonitorCommandHandler.class;
	}
}
