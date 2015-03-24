package br.com.pandox.nursery.domain.monitor.model;

import br.com.pandox.nursery.Model;
import br.com.pandox.nursery.domain.metric.entity.MetricEntity;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.monitor.entity.MonitorEntity;
import br.com.pandox.nursery.domain.monitor.entity.repository.MonitorRepository;

import java.util.List;

public interface Monitor extends Model {

    void save(MonitorRepository repository);

    void addMetric(Metric metric, MonitorRepository repository);

    Long getId();

    String getMachine();

    MonitorEntity.Status getStatus();

    String getName();

   List<Metric> getMetrics();

    boolean isInSync();



}
