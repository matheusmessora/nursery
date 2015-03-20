package br.com.pandox.nursery.domain.monitor.command.impl;

import br.com.pandox.nursery.domain.monitor.command.MonitorCommand;
import br.com.pandox.nursery.domain.monitor.command.handler.MonitorCommandHandler;


public class DeleteMonitorCommand implements MonitorCommand {

    @Override public Class<? extends MonitorCommandHandler> getExecutorType() {
        return null;
    }
}
