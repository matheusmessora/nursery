package br.com.pandox.nursery.domain.monitor.model;

import br.com.pandox.nursery.Model;
import br.com.pandox.nursery.domain.metric.entity.MetricEntity;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.monitor.entity.MonitorEntity;
import br.com.pandox.nursery.domain.monitor.entity.repository.MonitorRepository;

import java.util.List;

public interface Monitor extends Model {

    public static enum Fields {
        MACHINE, NAME, STATUS
    }


    void save(MonitorRepository repository);

    Long addMetric(Metric metric, MonitorRepository repository);

    Long getId();

    String getMachine();

    MonitorEntity.Status getStatus();

    String getName();

   List<MetricEntity> getMetrics();

    boolean isInSync();

    Metric getMetric(Long metricId);
}
