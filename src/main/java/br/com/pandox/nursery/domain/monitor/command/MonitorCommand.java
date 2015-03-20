package br.com.pandox.nursery.domain.monitor.command;


import br.com.pandox.nursery.domain.monitor.command.handler.MonitorCommandHandler;
import br.com.pandox.nursery.infrastructure.command.Command;

public interface MonitorCommand extends Command {

    @Override
    Class<? extends MonitorCommandHandler> getExecutorType();
}
