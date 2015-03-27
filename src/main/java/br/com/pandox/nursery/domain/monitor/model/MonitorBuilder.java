package br.com.pandox.nursery.domain.monitor.model;

import br.com.pandox.nursery.domain.metric.model.Metric;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.*;

public class MonitorBuilder {
    private Long id;
    private String machine;
    private Monitor.Status status;
    private String name;
    private String version;
    private Set<Metric> metrics;

    public MonitorBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public MonitorBuilder setMachine(String machine) {
        this.machine = machine;
        return this;
    }

    public MonitorBuilder setStatus(Monitor.Status status) {
        this.status = status;
        return this;
    }

    public MonitorBuilder setStatus(String status) {
        if(!StringUtils.isEmpty(status)) {
            Monitor.Status monitorStatus;
            try {
                monitorStatus = Monitor.Status.valueOf(status);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Malformed attribute: status. It should be one of the following " +
                        Arrays.asList(Monitor.Status.values()));
            }
            this.status = monitorStatus;
        }

        return this;
    }

    public MonitorBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public MonitorBuilder setVersion(String version) {
        this.version = version;
        return this;
    }

    public MonitorBuilder setMetrics(Set<Metric> metrics) {
        this.metrics = metrics;
        return this;
    }

    public Monitor build() {
        validate();

        return new MonitorEntity(id, machine, status, name, version, metrics);
    }

    private void validate(){
        Assert.hasText(machine, "machine must not be null");
        Assert.hasText(name, "name must not be null");

        if(status == null) {
            status = Monitor.Status.UNREGISTERED;
        }

        if(metrics == null) {
            metrics = new HashSet<>();
        }
    }
}
