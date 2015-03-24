package br.com.pandox.nursery.domain.monitor.command;


import br.com.pandox.nursery.domain.monitor.command.handler.MetricCommandHandler;
import br.com.pandox.nursery.infrastructure.command.Command;

public interface MetricCommand extends Command {

    @Override
    Class<? extends MetricCommandHandler> getExecutorType();
}
