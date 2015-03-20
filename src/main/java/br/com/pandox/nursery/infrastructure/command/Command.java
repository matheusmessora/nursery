package br.com.pandox.nursery.infrastructure.command;


import br.com.pandox.nursery.infrastructure.command.handler.CommandHandler;

public interface Command {

    public Class<? extends CommandHandler> getExecutorType();

}
