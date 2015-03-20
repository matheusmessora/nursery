package br.com.pandox.nursery.domain.monitor.command;

import br.com.pandox.nursery.domain.monitor.command.executor.CreateMonitorCommandExecutor;
import br.com.pandox.nursery.domain.monitor.command.executor.MonitorCommandExecutor;
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
    public Class<? extends MonitorCommandExecutor> getExecutorType() {
        return CreateMonitorCommandExecutor.class;
    }
}
