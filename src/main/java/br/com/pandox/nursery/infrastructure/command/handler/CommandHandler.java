package br.com.pandox.nursery.infrastructure.command.handler;

public interface CommandHandler<R, C> {

    R process(C command);
}
