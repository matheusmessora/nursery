package br.com.pandox.nursery.domain.monitor.entity;

import br.com.pandox.nursery.domain.metric.entity.MetricEntity;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;
import java.util.List;

@Entity
public class MonitorEntity {

    private static final Logger LOGGER = LogManager.getLogger();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String machine;

    @Enumerated(EnumType.ORDINAL)
    private Monitor.Status status = Monitor.Status.UNREGISTERED;

    @Column(unique = true)
    private String name;

    @Column
    private String version;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "monitor")
    private List<MetricEntity> metrics;

    @Transient
    private transient boolean inSync;

    @Deprecated
    public MonitorEntity(){
        this.inSync = false;
    }

    public MonitorEntity(Long monitorId) {
        this.id = monitorId;
    }

    public MonitorEntity(Long id, String machine, Monitor.Status status, String name, String version) {
        this.id = id;
        this.machine = machine;
        this.status = status;
        this.name = name;
        this.version = version;
        this.inSync = false;
    }

    public static Logger getLogger() {
        return LOGGER;
    }

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

    public Monitor.Status getStatus() {
        return status;
    }

    public void setStatus(Monitor.Status status) {
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

    public List<MetricEntity> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<MetricEntity> metrics) {
        this.metrics = metrics;
    }

    public boolean isInSync() {
        return inSync;
    }

    public void setInSync(boolean inSync) {
        this.inSync = inSync;
    }

    @Override
    public String toString() {
        return "MonitorEntity{" +
                "id=" + id +
                ", inSync=" + inSync +
                ", machine='" + machine + '\'' +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", metrics=" + metrics +
                '}';
    }
}
