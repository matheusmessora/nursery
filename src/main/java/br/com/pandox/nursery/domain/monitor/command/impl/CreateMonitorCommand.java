package br.com.pandox.nursery.domain.monitor.command.impl;

import br.com.pandox.nursery.domain.monitor.command.handler.impl.CreateMonitorCommandHandler;
import br.com.pandox.nursery.infrastructure.command.Command;
import br.com.pandox.nursery.infrastructure.command.handler.CommandHandler;
import br.com.pandox.nursery.view.monitor.MonitorDTO;

public class CreateMonitorCommand implements Command {

    private MonitorDTO monitorDTO;

    public CreateMonitorCommand(MonitorDTO monitorDTO) {
        this.monitorDTO = monitorDTO;
    }

    public MonitorDTO getMonitorDTO() {
        return monitorDTO;
    }

    @Override
    public Class<? extends CommandHandler> getExecutorType() {
        return CreateMonitorCommandHandler.class;
    }
}
