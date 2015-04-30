package br.com.pandox.nursery.domain.metricData;

import br.com.pandox.nursery.domain.CommandException;
import br.com.pandox.nursery.domain.DomainNotFoundException;
import br.com.pandox.nursery.domain.alert.loader.AlertLoader;
import br.com.pandox.nursery.domain.metric.loader.MetricLoader;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.metric.model.MetricDataEntity;
import br.com.pandox.nursery.domain.metric.model.repository.MetricDataRepository;
import br.com.pandox.nursery.domain.metric.model.vo.MetricData;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MetricDataServiceImpl implements MetricDataService {

    @Autowired
    private MetricDataRepository metricDataRepository;

    @Autowired
    private MetricLoader metricLoader;

    @Autowired
    private AlertLoader alertLoader;

    @Autowired
    private EventBus eventBus;

    @Override
    public MetricData create(Integer value, Long metricId) {
        Metric metric;
        try {
            metric = metricLoader.loadByID(metricId, true, false);
        }catch(DomainNotFoundException ex){
            throw new CommandException(String.format("Metric with id [%s] not found", metricId));
        }
        MetricDataEntity entity = new MetricDataEntity(value, new Date());
        metric.addData(entity, eventBus);
        entity.setMetric(metric);

        return metricDataRepository.save(entity);
    }
}
