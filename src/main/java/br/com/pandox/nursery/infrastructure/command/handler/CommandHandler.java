package br.com.pandox.nursery.infrastructure.command.handler;

import br.com.pandox.nursery.infrastructure.command.Command;

public interface CommandHandler<C extends Command> {

    Void process(C command);
}
