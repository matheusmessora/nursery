package br.com.pandox.nursery.domain.monitor.command.handler;

import br.com.pandox.nursery.domain.CommandException;
import br.com.pandox.nursery.domain.DomainNotFoundException;
import br.com.pandox.nursery.domain.metric.factory.MetricFactory;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.monitor.command.AddMetricToMonitorCommand;
import br.com.pandox.nursery.domain.monitor.entity.repository.MonitorRepository;
import br.com.pandox.nursery.domain.monitor.loader.MonitorLoader;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.infrastructure.command.executor.CommandExecutor;
import br.com.pandox.nursery.infrastructure.command.handler.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class AddMetricToMonitorCommandHandler implements CommandHandler<AddMetricToMonitorCommand> {

    @Autowired
    private CommandExecutor executor;

    @Autowired
    private MonitorRepository repository;

    @Autowired
    private MetricFactory metricFactory;

    @Autowired
    private MonitorLoader loader;

    @PostConstruct
    public void init(){
        executor.addHandler(this);
    }

    @Override
    public Void process(AddMetricToMonitorCommand command) {
        try {
            Monitor monitor = loader.loadByID(command.getMonitorId(), true);
            Metric metric = metricFactory.createFrom(command.getMetricDTO());
            monitor.addMetric(metric, repository);
        } catch(DomainNotFoundException ex){
            throw new CommandException(String.format("Given monitor with id [%s] not found", command.getMonitorId()));
        }
        return null;
    }
}
