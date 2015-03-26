package br.com.pandox.nursery.domain.monitor.model;

import br.com.pandox.nursery.domain.CommandException;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.monitor.event.CreateMonitorEvent;
import br.com.pandox.nursery.domain.monitor.event.NewMetricEvent;
import br.com.pandox.nursery.infrastructure.event.listener.EventListener;

import java.util.List;

public class MonitorImpl implements Monitor {

    private Long id;
    private String machine;
    private Status status = Status.UNREGISTERED;
    private String name;
    private String version;
    private List<Metric> metrics;

    protected MonitorImpl(Long id, String machine, Status status, String name, String version, List<Metric> metrics) {
        this.id = id;
        this.machine = machine;
        this.status = status;
        this.name = name;
        this.version = version;
        this.metrics = metrics;
    }

    public Long getId() {
        return id;
    }

    public String getMachine() {
        return machine;
    }

    public Status getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public List<Metric> getMetrics() {
        return this.metrics;
    }

    public Metric getMetric(Long metricId) {
        for (Metric metric : metrics) {
            if (metric.getId().equals(metricId)) {
                return metric;
            }
        }
        return null;
    }

    public void save(EventListener eventListener) {
        status = Status.READY;
        CreateMonitorEvent event = new CreateMonitorEvent(this);
        eventListener.post(event);
    }

    public void addMetric(Metric metric, EventListener eventListener) {
        if(status.equals(Status.UNREGISTERED) || status.equals(Status.STOPPED)){
            throw new CommandException("Can not add metric to Monitor in %s status", status.name());
        }

        this.metrics.add(metric);
        NewMetricEvent event = new NewMetricEvent(this);
        eventListener.post(event);
    }
}
