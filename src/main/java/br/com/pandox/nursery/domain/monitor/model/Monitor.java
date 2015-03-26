package br.com.pandox.nursery.domain.monitor.model;

import br.com.pandox.nursery.Model;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.infrastructure.event.listener.EventListener;

import java.util.List;

public interface Monitor extends Model {

    // ALWAYS ADD NEW STATUS AT THE END - because the entityStatus field is
    // annotated as ordinal in sake of performance
    public static enum Status {
        UNREGISTERED, READY, STARTED, RUNNING, STOPPED;
    }


    void save(EventListener eventListener);

    void addMetric(Metric metric, EventListener eventListener);

    Long getId();

    String getMachine();

    Status getStatus();

    String getName();

   List<Metric> getMetrics();

    Metric getMetric(Long metricId);
}
