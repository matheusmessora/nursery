package br.com.pandox.nursery.domain.monitor.repository.entity;

import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.infrastructure.exception.DomainIllegalAttributeException;
import org.springframework.util.StringUtils;

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
                throw new DomainIllegalAttributeException("monitor", "status");
            }
            this.status = monitorStatus;
        }else {
            this.status = MonitorEntity.Status.UNREGISTERED;
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

    public Monitor build() {
        return new MonitorEntity(id, machine, status, name, version);
    }
}
