package br.com.pandox.nursery.domain.monitor.command.handler.impl;

import br.com.pandox.nursery.domain.monitor.command.impl.CreateMonitorCommand;
import br.com.pandox.nursery.domain.monitor.entity.repository.MonitorRepository;
import br.com.pandox.nursery.domain.monitor.factory.MonitorFactory;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.infrastructure.command.executor.CommandExecutor;
import br.com.pandox.nursery.infrastructure.command.handler.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CreateMonitorCommandHandler implements CommandHandler<CreateMonitorCommand> {

    @Autowired
    private MonitorRepository repository;

    @Autowired
    private MonitorFactory factory;

    @Autowired
    private CommandExecutor executor;

    @PostConstruct
    public void init(){
        executor.addHandler(this);
    }

    @Override
    public Void process(CreateMonitorCommand command) {
        Monitor monitor = factory.fabric(command.getMonitorDTO());
        monitor.save(repository);
        return null;
    }

}
