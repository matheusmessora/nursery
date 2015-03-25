package br.com.pandox.nursery.infrastructure.command.executor;


import br.com.pandox.nursery.infrastructure.command.Command;
import br.com.pandox.nursery.infrastructure.command.handler.CommandHandler;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCommandExecutor {

    private Map<Class<? extends CommandHandler>,CommandHandler> executors;

    protected AbstractCommandExecutor() {
        executors = new HashMap<>();
    }

    public void addHandler(CommandHandler commandHandler) {
        executors.put(commandHandler.getClass(), commandHandler);
    }

    protected CommandHandler getHandler(Command command) {
        Assert.notNull(command, "Command must not be null");
        Assert.notNull(command.getExecutorType(), "ExecutorType must not be null");
        CommandHandler commandHandler = executors.get(command.getExecutorType());
        if(commandHandler == null){
            throw new UnsupportedOperationException(String.format("The %s has no Handlers. Please provide a correct Handler", command.getClass()));
        }
        return commandHandler;
    }

}
