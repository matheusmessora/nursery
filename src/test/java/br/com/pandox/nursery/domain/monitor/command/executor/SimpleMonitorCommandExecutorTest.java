package br.com.pandox.nursery.domain.monitor.command.executor;

import br.com.pandox.nursery.infrastructure.command.Command;
import br.com.pandox.nursery.infrastructure.command.executor.SimpleCommandExecutor;
import br.com.pandox.nursery.infrastructure.command.handler.CommandHandler;
import org.testng.annotations.Test;

public class SimpleMonitorCommandExecutorTest {

    class NullMonitorCommand implements Command {

        @Override
        public Class<? extends CommandHandler> getExecutorType() {
            return null;
        }
    }

    class EmptyMonitorCommand implements Command {

        @Override
        public Class<? extends CommandHandler> getExecutorType() {
            return EmptyCommandHandler.class;
        }
    }

    class EmptyCommandHandler implements CommandHandler<Command> {

        @Override
        public Void process(Command command) {
            return null;
        }
    }

    class NullCommandHandler implements CommandHandler<Command> {

        @Override
        public Void  process(Command command) {
            return null;
        }
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void should_return_IllegalArgumentException_when_handler_null() {
        SimpleCommandExecutor executor = new SimpleCommandExecutor();

        executor.execute(new NullMonitorCommand());
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void should_return_UnsupportedOperationException_when_no_handler_found() {
        CommandHandler handler = new NullCommandHandler();
        SimpleCommandExecutor executor = new SimpleCommandExecutor();
        executor.addHandler(handler);

        executor.execute(new EmptyMonitorCommand());
    }

    @Test
    public void should_execute_properly() {
        EmptyCommandHandler handler = new EmptyCommandHandler();
        SimpleCommandExecutor executor = new SimpleCommandExecutor();
        executor.addHandler(handler);

        executor.execute(new EmptyMonitorCommand());
    }

}
