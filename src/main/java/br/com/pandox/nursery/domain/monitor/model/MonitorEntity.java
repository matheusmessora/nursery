package br.com.pandox.nursery.domain.monitor.model;

import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.metric.model.MetricEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;
import java.util.Set;

@Entity
public class MonitorEntity implements Monitor {

    private static final Logger LOGGER = LogManager.getLogger();

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String machine;

    @Enumerated(EnumType.STRING)
    private Monitor.Status status = Monitor.Status.UNREGISTERED;

    @Column
    private String name;

    @Column
    private String version;

    @Column
    private String password;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "monitor", targetEntity = MetricEntity.class)
    private Set<Metric> metrics;

    @Deprecated
    public MonitorEntity(){
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
    }

    public MonitorEntity(Long id, String machine, Status status, String name, String version, Set<Metric> metrics) {
        this.id = id;
        this.machine = machine;
        this.status = status;
        this.name = name;
        this.version = version;
        this.metrics = metrics;
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

    public Set<Metric> getMetrics() {
        return metrics;
    }

    @Override
    public Metric getMetric(Long metricId) {
        for (Metric metric : metrics) {
            if (metric.getId().equals(metricId)) {
                return metric;
            }
        }
        return null;
    }

    @Override
    public void addMetric(Metric metric) {
        this.getMetrics().add(metric);
    }

    @Override
    public String toString() {
        return "MonitorEntity{" +
                "id=" + id +
                ", machine='" + machine + '\'' +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }

    public void setMetrics(Set<Metric> metrics) {
        this.metrics = metrics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonitorEntity that = (MonitorEntity) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
