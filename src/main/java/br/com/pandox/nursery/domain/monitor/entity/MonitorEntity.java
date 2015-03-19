package br.com.pandox.nursery.domain.monitor.entity;

import br.com.pandox.nursery.domain.monitor.repository.MonitorRepository;

import javax.persistence.*;

@Entity
public class MonitorEntity {

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

    @Transient
    private transient MonitorRepository repository;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public MonitorEntity persist(MonitorRepository repository) {
        return repository.save(this);
    }

    public void setRepository(MonitorRepository repository) {
        this.repository = repository;
    }

    public void save() {
        repository.save(this);
    }
}
