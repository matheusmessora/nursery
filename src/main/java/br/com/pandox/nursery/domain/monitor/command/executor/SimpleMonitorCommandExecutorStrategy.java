package br.com.pandox.nursery.domain.monitor.command.executor;

import br.com.pandox.nursery.domain.monitor.command.MonitorCommand;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class SimpleMonitorCommandExecutorStrategy implements MonitorCommandExecutorStrategy {

    @Autowired
    private CreateMonitorCommandExecutor createMonitorCommandExecutor;

    private Map<Class<? extends MonitorCommandExecutor>,MonitorCommandExecutor> executors;

    @PostConstruct
    public void init() {
        executors = new HashMap<>();
        executors.put(createMonitorCommandExecutor.getClass(), createMonitorCommandExecutor);

    }

    @Override
    public Monitor execute(MonitorCommand command) {
        return executors.get(command.getExecutorType()).execute(command);
    }
}
