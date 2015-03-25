package br.com.pandox.nursery.domain.monitor.command.handler;

import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.monitor.command.MetricCommand;
import br.com.pandox.nursery.infrastructure.command.handler.CommandHandler;

public interface MetricCommandHandler<R extends Metric, T extends MetricCommand> extends CommandHandler {

    R process(T command);
}
