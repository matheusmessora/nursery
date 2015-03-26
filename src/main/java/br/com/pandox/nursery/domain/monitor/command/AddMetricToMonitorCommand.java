package br.com.pandox.nursery.domain.monitor.command;

import br.com.pandox.nursery.domain.monitor.command.handler.AddMetricToMonitorCommandHandler;
import br.com.pandox.nursery.infrastructure.command.Command;
import br.com.pandox.nursery.infrastructure.command.handler.CommandHandler;
import br.com.pandox.nursery.view.metric.MetricDTO;

public class AddMetricToMonitorCommand implements Command {

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
	public Class<? extends CommandHandler> getExecutorType() {
		return AddMetricToMonitorCommandHandler.class;
	}
}
