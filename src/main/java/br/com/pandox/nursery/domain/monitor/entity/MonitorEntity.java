package br.com.pandox.nursery.domain.monitor.entity;

import br.com.pandox.nursery.domain.metric.entity.MetricEntity;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.monitor.entity.repository.MonitorRepository;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import com.google.common.collect.Iterables;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MonitorEntity implements Monitor {

    private static final Logger LOGGER = LogManager.getLogger();

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

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = MetricEntity.class)
    private List<MetricEntity> metrics;

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

    @Override public List<MetricEntity> getMetrics() {
        Iterable<MetricEntity> all = this.metrics;

        List<MetricEntity> results = new ArrayList<>();
        for (MetricEntity metricEntity : all) {
            results.add(metricEntity);
        }
        return results;

//        ImmutableList.Builder<Metric> builder = ImmutableList.builder();
//        return builder.addAll(all).build();
    }

    @Override
    public boolean isInSync() {
        return inSync;
    }

    @Override
    public Metric getMetric(Long metricId) {
        for (MetricEntity metric : metrics) {
            if (metric.getId().equals(metricId)) {
                return metric;
            }
        }

        throw new MetricNotFoundException();
    }

    public void save(MonitorRepository repository) {
        status = Status.READY;
        repository.save(this);
        LOGGER.info(this);
        inSync = true;
    }

    public Long addMetric(Metric metric, MonitorRepository repository) {
        inSync = false;

        MetricEntity metricEntity = (MetricEntity) metric;
        this.metrics.add(metricEntity);

        MonitorEntity save = repository.save(this);

        metric = Iterables.getLast(save.getMetrics());
        return metric.getId();
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
