package br.com.pandox.nursery.infrastructure.command.executor;

public interface CommandExecutor<R, C> {

    R execute(C command);
}
