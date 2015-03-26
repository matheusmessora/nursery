package br.com.pandox.nursery.domain.monitor.model;

import br.com.pandox.nursery.Model;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.monitor.sevice.MonitorService;

import java.util.List;

public interface Monitor extends Model {

    // ALWAYS ADD NEW STATUS AT THE END - because the entityStatus field is
    // annotated as ordinal in sake of performance
    public static enum Status {
        UNREGISTERED, READY, STARTED, RUNNING, STOPPED;
    }


    void save(MonitorService service);

    void addMetric(Metric metric, MonitorService service);

    Long getId();

    String getMachine();

    Status getStatus();

    String getName();

   List<Metric> getMetrics();

    boolean isInSync();

    Metric getMetric(Long metricId);
}
