package br.com.pandox.nursery.domain.monitor.sevice;

import br.com.pandox.nursery.domain.metric.entity.MetricEntity;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.monitor.entity.MonitorEntity;
import br.com.pandox.nursery.domain.monitor.entity.repository.MonitorRepository;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MonitorService {

    @Autowired
    private MonitorRepository repository;


    public void save(Monitor monitor) {
        List<MetricEntity> metricEntities = new ArrayList<>();
        MonitorEntity entity = new MonitorEntity(monitor.getId(), monitor.getMachine(), monitor.getStatus(), monitor.getName(), null);
        for (Metric metric : monitor.getMetrics()) {
            MetricEntity metricEntity = new MetricEntity(metric.getId(), metric.getName(), metric.getType(), metric.getTimeInterval());
            metricEntity.setMonitor(entity);
            metricEntities.add(metricEntity);
        }
        entity.setMetrics(metricEntities);

        repository.save(entity);
    }


}
