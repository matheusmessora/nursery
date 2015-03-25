package br.com.pandox.nursery.infrastructure.command.executor;

import br.com.pandox.nursery.infrastructure.command.Command;
import br.com.pandox.nursery.infrastructure.command.handler.CommandHandler;

public interface CommandExecutor {

    Object execute(Command command);

    void addHandler(CommandHandler commandHandler);
}
