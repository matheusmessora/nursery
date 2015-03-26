package br.com.pandox.nursery.domain.monitor.command.handler;

import br.com.pandox.nursery.domain.monitor.command.DeleteMonitorCommand;
import br.com.pandox.nursery.infrastructure.command.executor.CommandExecutor;
import br.com.pandox.nursery.infrastructure.command.handler.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DeleteMonitorCommandHandler implements CommandHandler<DeleteMonitorCommand> {

    @Autowired
    private CommandExecutor executor;

    @PostConstruct
    public void init(){
        executor.addHandler(this);
    }


    public Void process(DeleteMonitorCommand command) {
        return null;
    }
}
