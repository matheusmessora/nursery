package br.com.pandox.nursery.domain.monitor.command.handler;

import br.com.pandox.nursery.domain.monitor.command.MonitorCommand;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.infrastructure.command.handler.CommandHandler;

public interface MonitorCommandHandler<R extends Monitor, T extends MonitorCommand> extends CommandHandler<R, T> {

    R process(T command);
}
