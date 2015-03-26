package br.com.pandox.nursery.domain.monitor.command;

import br.com.pandox.nursery.infrastructure.command.Command;
import br.com.pandox.nursery.infrastructure.command.handler.CommandHandler;


public class DeleteMonitorCommand implements Command {

    @Override public Class<? extends CommandHandler> getExecutorType() {
        return null;
    }
}
