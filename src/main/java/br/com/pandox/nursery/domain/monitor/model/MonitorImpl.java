package br.com.pandox.nursery.domain.monitor.model;


import br.com.pandox.nursery.domain.monitor.entity.MonitorEntity;
import br.com.pandox.nursery.domain.monitor.entity.MonitorEntityFactory;

public class MonitorImpl implements Monitor {

    protected MonitorImpl(Long id, String machine, String status, String name, String version, MonitorEntityFactory factory) {
        this.id = id;
        this.machine = machine;
        this.status = status;
        this.name = name;
        this.version = version;
        this.factory = factory;
    }

    private Long id;
    private String machine;
    private String status;
    private String name;
    private String version;
    private MonitorEntityFactory factory;

    @Override
    public void save() {
        MonitorEntity entity = factory.fabric(this);
        entity.save();

        setId(entity.getId());
    }

    private void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getMachine() {
        return machine;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public String getName() {
        return name;
    }
}
