package br.com.pandox.nursery.domain.monitor.command.handler;

import br.com.pandox.nursery.domain.monitor.command.CreateMonitorCommand;
import br.com.pandox.nursery.domain.monitor.factory.MonitorFactory;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.sevice.MonitorService;
import br.com.pandox.nursery.infrastructure.command.executor.CommandExecutor;
import br.com.pandox.nursery.infrastructure.command.handler.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CreateMonitorCommandHandler implements CommandHandler<CreateMonitorCommand> {

    @Autowired
    private MonitorService service;

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
        monitor.save(service);
        return null;
    }

}
