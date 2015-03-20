package br.com.pandox.nursery.domain.monitor.repository.entity;

import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.repository.MonitorRepository;

import javax.persistence.*;

@Entity
public class MonitorEntity implements Monitor {

    // ALWAYS ADD NEW STATUS AT THE END - because the entityStatus field is
    // annotated as ordinal in sake of performance
    public static enum Status {
        UNREGISTERED, READY, STARTED, RUNNING, STOPPED;

    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String machine;

    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.UNREGISTERED;

    @Column(unique = true)
    private String name;

    @Column
    private String version;

    @Transient
    private transient boolean inSync;

    @Deprecated
    public MonitorEntity(){
        this.inSync = false;
    }

    protected MonitorEntity(Long id, String machine, Status status, String name, String version) {
        this.id = id;
        this.machine = machine;
        this.status = status;
        this.name = name;
        this.version = version;
        this.inSync = false;
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
    public boolean isInSync() {
        return inSync;
    }

    public void save(MonitorRepository repository) {
        status = Status.READY;
        repository.save(this);
        inSync = true;
    }
}
