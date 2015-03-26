package br.com.pandox.nursery.domain.monitor.model;

import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.monitor.entity.MonitorEntity;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MonitorBuilder {
    private Long id;
    private String machine;
    private Monitor.Status status;
    private String name;
    private String version;
    private List<Metric> metrics;

    public MonitorBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public MonitorBuilder setMachine(String machine) {
        Assert.hasText(machine, "Machine must have text");
        this.machine = machine;
        return this;
    }

    public MonitorBuilder setStatus(Monitor.Status status) {
        this.status = status;
        return this;
    }

    public MonitorBuilder setStatus(String status) {
        if (!StringUtils.isEmpty(status)) {
            Monitor.Status monitorStatus;
            try {
                monitorStatus = Monitor.Status.valueOf(status);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Malformed attribute: status. It should be one of the following " +
                    Arrays.asList(Monitor.Status.values()));
            }
            this.status = monitorStatus;
        }else {
            this.status = Monitor.Status.UNREGISTERED;
        }

        return this;
    }

    public MonitorBuilder setName(String name) {
        Assert.hasText(name, "name");
        this.name = name;
        return this;
    }

    public MonitorBuilder setVersion(String version) {
        this.version = version;
        return this;
    }

    public MonitorBuilder setMetrics(List<Metric> metrics) {
        this.metrics = metrics;
        return this;
    }

    public Monitor build() {
        if(metrics == null) {
            metrics = new ArrayList<>();
        }
        return new MonitorImpl(id, machine, status, name, version, metrics);
    }
}
