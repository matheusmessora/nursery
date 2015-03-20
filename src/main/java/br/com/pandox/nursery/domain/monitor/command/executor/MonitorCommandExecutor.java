package br.com.pandox.nursery.domain.monitor.command.executor;


import br.com.pandox.nursery.domain.monitor.command.MonitorCommand;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.infrastructure.command.executor.CommandExecutor;

public interface MonitorCommandExecutor<R extends Monitor, T extends MonitorCommand> extends CommandExecutor<R, T> {


    Monitor execute(MonitorCommand command);
}
