package br.com.pandox.nursery.domain.monitor.command.executor;


import br.com.pandox.nursery.domain.monitor.command.MonitorCommand;
import br.com.pandox.nursery.domain.monitor.model.Monitor;

public interface MonitorCommandExecutorStrategy {

    Monitor execute(MonitorCommand command);
}
