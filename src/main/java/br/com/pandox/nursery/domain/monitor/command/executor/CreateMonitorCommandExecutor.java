package br.com.pandox.nursery.domain.monitor.command.executor;

import br.com.pandox.nursery.domain.monitor.command.CreateMonitorCommand;
import br.com.pandox.nursery.domain.monitor.factory.MonitorFactory;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.repository.MonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateMonitorCommandExecutor implements MonitorCommandExecutor<Monitor, CreateMonitorCommand> {

    @Autowired
    private MonitorRepository repository;

    @Autowired
    private MonitorFactory factory;

    @Override
    public Monitor execute(CreateMonitorCommand command) {
        Monitor monitor = factory.fabric(command.getMonitorDTO());
        monitor.save(repository);
        return monitor;
    }
}
