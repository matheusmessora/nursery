package br.com.pandox.nursery.domain.metric.event.processor;

import br.com.pandox.nursery.domain.metric.entity.MetricDataEntity;
import br.com.pandox.nursery.domain.metric.entity.MetricEntity;
import br.com.pandox.nursery.domain.metric.entity.repository.MetricDataRepository;
import br.com.pandox.nursery.domain.metric.event.CreateDataEvent;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.metric.model.vo.MetricData;
import br.com.pandox.nursery.domain.metric.service.MetricService;
import br.com.pandox.nursery.infrastructure.event.processor.AbstractEventProcessor;
import br.com.pandox.nursery.infrastructure.event.processor.EventProcessor;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateDataEventProcessor extends AbstractEventProcessor<CreateDataEvent> implements EventProcessor<CreateDataEvent> {

    @Autowired
    private MetricService metricService;

    @Autowired
    private MetricDataRepository metricDataRepository;

    @Override
    @Subscribe
    public void process(CreateDataEvent event) {
        Metric metric = event.getMetric();
        MetricData data = metric.getDatas().get(0);

        MetricDataEntity entity = new MetricDataEntity(data.getValue(), data.getDateCreation());
        MetricEntity metricEntity = new MetricEntity();
        metricEntity.setId(metric.getId());
        entity.setMetric(metricEntity);
        metricDataRepository.save(entity);
    }
}
