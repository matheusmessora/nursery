package br.com.pandox.nursery.domain.metric.command.handler;

import br.com.pandox.nursery.domain.CommandException;
import br.com.pandox.nursery.domain.DomainNotFoundException;
import br.com.pandox.nursery.domain.metric.command.AddDataToMetricCommand;
import br.com.pandox.nursery.domain.metric.loader.MetricLoader;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.metric.model.vo.MetricData;
import br.com.pandox.nursery.domain.metric.model.vo.MetricDataFactory;
import br.com.pandox.nursery.infrastructure.command.executor.CommandExecutor;
import br.com.pandox.nursery.infrastructure.command.handler.CommandHandler;
import br.com.pandox.nursery.infrastructure.event.listener.EventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
public class AddDataToMetricCommandHandler implements CommandHandler<AddDataToMetricCommand> {

    @Autowired
    private CommandExecutor executor;

    @Autowired
    private MetricLoader loader;

    @Autowired
    private EventListener eventListener;

    @Autowired
    private MetricDataFactory dataFactory;

    @PostConstruct
    public void init(){
        executor.addHandler(this);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Void process(AddDataToMetricCommand command) {
        try {
            Metric metric = loader.loadByID(command.getMetricId(), true);

            Integer value = command.getDataDTO().getValue();
            MetricData metricData = dataFactory.createWith(value);

            metric.addData(metricData, eventListener);
        } catch(DomainNotFoundException ex){
            throw new CommandException(String.format("Given metric with id [%s] not found", command.getMetricId()));
        }
        return null;
    }
}
