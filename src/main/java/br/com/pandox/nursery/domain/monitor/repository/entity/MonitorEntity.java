package br.com.pandox.nursery.domain.monitor.repository.entity;

import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.repository.MonitorRepository;

import javax.persistence.*;

@Entity
public class MonitorEntity implements Monitor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String machine;

    @Column
    private String status;

    @Column(unique = true)
    private String name;

    @Column
    private String version;

    @Deprecated
    public MonitorEntity(){}

    protected MonitorEntity(Long id, String machine, String status, String name, String version) {
        this.id = id;
        this.machine = machine;
        this.status = status;
        this.name = name;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public String getMachine() {
        return machine;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public void save(MonitorRepository repository) {
        repository.save(this);
    }
}
