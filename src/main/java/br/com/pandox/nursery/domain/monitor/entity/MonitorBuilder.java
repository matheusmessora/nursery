package br.com.pandox.nursery.domain.monitor.entity;

import br.com.pandox.nursery.domain.monitor.model.Monitor;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Arrays;

public class MonitorBuilder {
    private Long id;
    private String machine;
    private MonitorEntity.Status status;
    private String name;
    private String version;

    public MonitorBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public MonitorBuilder setMachine(String machine) {
        Assert.hasText(machine);
        this.machine = machine;
        return this;
    }

    public MonitorBuilder setStatus(MonitorEntity.Status status) {
        this.status = status;
        return this;
    }

    public MonitorBuilder setStatus(String status) {
        if (!StringUtils.isEmpty(status)) {
            MonitorEntity.Status monitorStatus;
            try {
                monitorStatus = MonitorEntity.Status.valueOf(status);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Malformed attribute: status. It should be one of the following " +
                    Arrays.asList(MonitorEntity.Status.values()));
            }
            this.status = monitorStatus;
        }else {
            this.status = MonitorEntity.Status.UNREGISTERED;
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

    public Monitor build() {
        return new MonitorEntity(id, machine, status, name, version);
    }
}
