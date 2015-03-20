package br.com.pandox.nursery.domain.monitor.command.executor;

public interface CommandExecutor<R, C> {

    R execute(C command);
}
