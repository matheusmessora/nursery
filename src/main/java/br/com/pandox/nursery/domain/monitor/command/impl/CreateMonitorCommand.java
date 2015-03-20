package br.com.pandox.nursery.domain.monitor.command.impl;

import br.com.pandox.nursery.domain.monitor.command.MonitorCommand;
import br.com.pandox.nursery.domain.monitor.command.handler.impl.CreateMonitorCommandHandler;
import br.com.pandox.nursery.domain.monitor.command.handler.MonitorCommandHandler;
import br.com.pandox.nursery.view.monitor.MonitorDTO;

public class CreateMonitorCommand implements MonitorCommand {

    private MonitorDTO monitorDTO;

    public CreateMonitorCommand(MonitorDTO monitorDTO) {
        this.monitorDTO = monitorDTO;
    }

    public MonitorDTO getMonitorDTO() {
        return monitorDTO;
    }

    @Override
    public Class<? extends MonitorCommandHandler> getExecutorType() {
        return CreateMonitorCommandHandler.class;
    }
}
