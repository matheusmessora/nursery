package br.com.pandox.nursery.domain.monitor.command;


import br.com.pandox.nursery.domain.monitor.command.executor.MonitorCommandExecutor;

public interface MonitorCommand extends Command {

    @Override
    Class<? extends MonitorCommandExecutor> getExecutorType();
}
