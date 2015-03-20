package br.com.pandox.nursery.domain.monitor.command;


import br.com.pandox.nursery.domain.monitor.command.executor.CommandExecutor;

public interface Command {

    public Class<? extends CommandExecutor> getExecutorType();

}
