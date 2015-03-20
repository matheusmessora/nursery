package br.com.pandox.nursery.domain.monitor.repository.entity;

import br.com.pandox.nursery.domain.monitor.model.Monitor;

public class MonitorBuilder {
    private Long id;
    private String machine;
    private String status;
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

    public Monitor build() {
        return new MonitorEntity(id, machine, status, name, version);
    }
}
