package br.com.pandox.nursery.domain.monitor.command.executor;

import br.com.pandox.nursery.domain.monitor.command.MonitorCommand;
import br.com.pandox.nursery.domain.monitor.model.Monitor;

public interface MonitorCommandExecutor<R extends Monitor, T extends MonitorCommand> extends CommandExecutor<R, T> {

    R execute(T command);
}
