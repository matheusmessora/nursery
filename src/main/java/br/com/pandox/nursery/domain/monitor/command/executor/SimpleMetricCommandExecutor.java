package br.com.pandox.nursery.domain.monitor.command.executor;

import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.monitor.command.MetricCommand;
import br.com.pandox.nursery.domain.monitor.command.handler.MetricCommandHandler;
import br.com.pandox.nursery.domain.monitor.command.handler.impl.AddMetricToMonitorCommandHandler;
import br.com.pandox.nursery.infrastructure.command.executor.AbstractCommandExecutor;
import br.com.pandox.nursery.infrastructure.command.executor.CommandExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SimpleMetricCommandExecutor extends AbstractCommandExecutor implements CommandExecutor<Metric, MetricCommand> {

    @Autowired
    private AddMetricToMonitorCommandHandler addMetricToMonitorCommand;

    @PostConstruct
    public void init() {
        addHandler(addMetricToMonitorCommand);
    }

    @Deprecated
    public SimpleMetricCommandExecutor(){}

    @Deprecated
    protected SimpleMetricCommandExecutor(MetricCommandHandler... handlers) {
        for (MetricCommandHandler handler : handlers) {
            super.addHandler(handler);
        }
    }

    @Override
    public Metric execute(MetricCommand command) {
        return (Metric) getHandler(command).process(command);
    }
}
