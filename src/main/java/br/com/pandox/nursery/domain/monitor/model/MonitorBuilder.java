package br.com.pandox.nursery.domain.monitor.model;

import br.com.pandox.nursery.domain.monitor.entity.MonitorEntityFactory;
import org.springframework.util.Assert;

public class MonitorBuilder {
    private Long id;
    private String machine;
    private String status;
    private String name;
    private String version;
    private MonitorEntityFactory factory;

    public MonitorBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public MonitorBuilder setMachine(String machine) {
        this.machine = machine;
        return this;
    }

    public MonitorBuilder setStatus(String status) {
        this.status = status;
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

    public MonitorBuilder setFactory(MonitorEntityFactory factory) {
        this.factory = factory;
        return this;
    }

    public MonitorImpl fabric() {
        Assert.notNull(factory);
        return new MonitorImpl(id, machine, status, name, version, factory);
    }
}