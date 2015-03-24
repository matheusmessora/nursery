package br.com.pandox.nursery.domain.monitor.command.executor;

import br.com.pandox.nursery.domain.monitor.command.MonitorCommand;
import br.com.pandox.nursery.domain.monitor.command.handler.MonitorCommandHandler;
import br.com.pandox.nursery.domain.monitor.command.handler.impl.CreateMonitorCommandHandler;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.infrastructure.command.executor.AbstractCommandExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SimpleMonitorCommandExecutor extends AbstractCommandExecutor implements MonitorCommandExecutor<Monitor, MonitorCommand> {

    @Autowired
    private CreateMonitorCommandHandler createMonitorCommandHandler;

    @PostConstruct
    public void init() {
        addHandler(createMonitorCommandHandler);
    }

    @Deprecated
    public SimpleMonitorCommandExecutor(){}

    @Deprecated
    protected SimpleMonitorCommandExecutor(MonitorCommandHandler... handlers) {
        for (MonitorCommandHandler handler : handlers) {
            super.addHandler(handler);
        }
    }

    @Override
    public Monitor execute(MonitorCommand command) {
        return (Monitor) getHandler(command).process(command);
    }
}
