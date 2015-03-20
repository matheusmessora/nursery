package br.com.pandox.nursery.domain.monitor.command.executor;

import br.com.pandox.nursery.domain.monitor.command.MonitorCommand;
import br.com.pandox.nursery.domain.monitor.command.handler.MonitorCommandHandler;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import org.testng.annotations.Test;

public class SimpleMonitorCommandExecutorTest {

    class NullMonitorCommand implements MonitorCommand {

        @Override public Class<? extends MonitorCommandHandler> getExecutorType() {
            return null;
        }
    }

    class EmptyMonitorCommand implements MonitorCommand {

        @Override public Class<? extends MonitorCommandHandler> getExecutorType() {
            return EmptyCommandHandler.class;
        }
    }

    class EmptyCommandHandler implements MonitorCommandHandler<Monitor, MonitorCommand> {

        @Override public Monitor process(MonitorCommand command) {
            return null;
        }
    }

    class NullCommandHandler implements MonitorCommandHandler<Monitor, MonitorCommand> {

        @Override public Monitor process(MonitorCommand command) {
            return null;
        }
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void should_return_IllegalArgumentException_when_handler_null() {
        MonitorCommandExecutor executor = new SimpleMonitorCommandExecutor();

        executor.execute(new NullMonitorCommand());
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void should_return_UnsupportedOperationException_when_no_handler_found() {
        MonitorCommandHandler handler = new NullCommandHandler();
        MonitorCommandExecutor executor = new SimpleMonitorCommandExecutor(handler);

        executor.execute(new EmptyMonitorCommand());
    }

    @Test
    public void should_execute_properly() {
        EmptyCommandHandler handler = new EmptyCommandHandler();
        MonitorCommandExecutor executor = new SimpleMonitorCommandExecutor(handler);

        executor.execute(new EmptyMonitorCommand());
    }

}
