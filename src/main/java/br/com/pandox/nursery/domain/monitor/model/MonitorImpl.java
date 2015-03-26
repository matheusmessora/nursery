package br.com.pandox.nursery.domain.monitor.model;

import br.com.pandox.nursery.domain.CommandException;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.monitor.sevice.MonitorService;

import java.util.List;

public class MonitorImpl implements Monitor {

    private Long id;
    private String machine;
    private Status status = Status.UNREGISTERED;
    private String name;
    private String version;
    private List<Metric> metrics;
    private transient boolean inSync;

    protected MonitorImpl(Long id, String machine, Status status, String name, String version, List<Metric> metrics) {
        this.id = id;
        this.machine = machine;
        this.status = status;
        this.name = name;
        this.version = version;
        this.inSync = false;
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

    @Override
    public List<Metric> getMetrics() {
        return this.metrics;
    }

    @Override
    public boolean isInSync() {
        return inSync;
    }

    @Override
    public Metric getMetric(Long metricId) {
        for (Metric metric : metrics) {
            if (metric.getId().equals(metricId)) {
                return metric;
            }
        }
        return null;
    }

    public void save(MonitorService service) {
        status = Status.READY;
        service.save(this);
        inSync = true;
    }

    public void addMetric(Metric metric, MonitorService service) {
        if(status.equals(Status.UNREGISTERED) || status.equals(Status.STOPPED)){
            throw new CommandException("Can not add metric to Monitor in %s status", status.name());
        }

        this.metrics.add(metric);

        service.save(this);
    }
}
