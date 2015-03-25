package br.com.pandox.nursery.domain.monitor.command.handler.impl;

import br.com.pandox.nursery.domain.monitor.command.impl.CreateMonitorCommand;
import br.com.pandox.nursery.domain.monitor.command.handler.MonitorCommandHandler;
import br.com.pandox.nursery.domain.monitor.factory.MonitorFactory;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.entity.repository.MonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateMonitorCommandHandler implements MonitorCommandHandler<Monitor, CreateMonitorCommand> {

    @Autowired
    private MonitorRepository repository;

    @Autowired
    private MonitorFactory factory;

    @Override
    public Monitor process(CreateMonitorCommand command) {
        Monitor monitor = factory.fabric(command.getMonitorDTO());
        monitor.save(repository);
        return null;
    }
}
