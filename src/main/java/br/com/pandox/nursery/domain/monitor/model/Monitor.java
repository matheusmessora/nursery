package br.com.pandox.nursery.domain.monitor.model;

import br.com.pandox.nursery.Model;
import br.com.pandox.nursery.domain.metric.model.Metric;

import java.util.Set;

public interface Monitor extends Model {

    void setStatus(Status status);

    // ALWAYS ADD NEW STATUS AT THE END - because the entityStatus field is
    // annotated as ordinal in sake of performance
    public static enum Status {
        UNREGISTERED, READY, STARTED, RUNNING, STOPPED;
    }

    Long getId();

    String getMachine();

    Status getStatus();

    String getName();

    Set<Metric> getMetrics();

    Metric getMetric(Long metricId);

    void addMetric(Metric metric);

}
