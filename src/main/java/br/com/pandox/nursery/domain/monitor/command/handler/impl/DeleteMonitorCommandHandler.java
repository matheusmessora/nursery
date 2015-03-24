package br.com.pandox.nursery.domain.monitor.command.handler.impl;

import br.com.pandox.nursery.domain.monitor.command.impl.DeleteMonitorCommand;
import br.com.pandox.nursery.domain.monitor.command.handler.MonitorCommandHandler;
import br.com.pandox.nursery.domain.monitor.model.Monitor;

public class DeleteMonitorCommandHandler implements MonitorCommandHandler<Monitor, DeleteMonitorCommand> {

    public Monitor process(DeleteMonitorCommand command) {
        return null;
    }
}
