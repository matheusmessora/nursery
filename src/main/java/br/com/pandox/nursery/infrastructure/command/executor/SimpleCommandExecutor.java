package br.com.pandox.nursery.infrastructure.command.executor;

import br.com.pandox.nursery.infrastructure.command.Command;
import br.com.pandox.nursery.infrastructure.command.handler.CommandHandler;
import org.springframework.stereotype.Component;

@Component
public class SimpleCommandExecutor extends AbstractCommandExecutor implements CommandExecutor {

    @Override
    public Void execute(Command command) {
        CommandHandler handler = super.getHandler(command);
        handler.process(command);
        return null;
    }
}
