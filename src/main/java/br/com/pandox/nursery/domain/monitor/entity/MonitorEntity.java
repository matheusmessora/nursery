package br.com.pandox.nursery.domain.monitor.entity;

import br.com.pandox.nursery.domain.CommandException;
import br.com.pandox.nursery.domain.metric.entity.MetricEntity;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.monitor.entity.repository.MonitorRepository;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import com.google.common.collect.ImmutableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;
import java.util.List;

@Entity
public class MonitorEntity implements Monitor {

    private static final Logger LOGGER = LogManager.getLogger();

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

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = MetricEntity.class)
    private List<Metric> metrics;

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
    public List<Metric> getMetrics() {
        Iterable<Metric> all = this.metrics;

        ImmutableList.Builder<Metric> builder = ImmutableList.builder();
        return builder.addAll(all).build();
    }

    @Override
    public boolean isInSync() {
        return inSync;
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

    public void save(MonitorRepository repository) {
        status = Status.READY;
        repository.save(this);
        LOGGER.debug(this);
        inSync = true;
    }

    public void addMetric(Metric metric, MonitorRepository repository) {
        inSync = false;
        if(status.equals(Status.UNREGISTERED) || status.equals(Status.STOPPED)){
            throw new CommandException("Can not add metric to Monitor in %s status", status.name());
        }

        MetricEntity metricEntity = (MetricEntity) metric;
        this.metrics.add(metricEntity);

        repository.save(this);
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
