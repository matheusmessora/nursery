package br.com.pandox.nursery.domain.monitor.command.handler;

import br.com.pandox.nursery.domain.CommandException;
import br.com.pandox.nursery.domain.monitor.command.CreateMonitorCommand;
import br.com.pandox.nursery.domain.monitor.factory.MonitorFactory;
import br.com.pandox.nursery.domain.monitor.loader.MonitorLoader;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.sevice.MonitorService;
import br.com.pandox.nursery.infrastructure.command.executor.CommandExecutor;
import br.com.pandox.nursery.infrastructure.command.handler.CommandHandler;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CreateMonitorCommandHandler implements CommandHandler<CreateMonitorCommand> {

    @Autowired
    private MonitorService service;

    @Autowired
    private MonitorLoader loader;

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
        Optional<Monitor> domain = loader.loadByName(monitor.getName());
        if(domain.isPresent()) {
            throw new CommandException(String.format("Given monitor already exists with name %s", monitor.getName()));
        }

        monitor.save(service);
        return null;
    }

}
