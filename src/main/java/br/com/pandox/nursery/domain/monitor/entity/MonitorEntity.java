package br.com.pandox.nursery.domain.monitor.entity;

import br.com.pandox.nursery.domain.metric.entity.MetricBuilder;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.metric.entity.MetricEntity;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.entity.repository.MonitorRepository;
import com.google.common.collect.ImmutableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "monitor", cascade = CascadeType.ALL)
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

    @Override public List<Metric> getMetrics() {
        Iterable<MetricEntity> all = this.metrics;

        ImmutableList.Builder<Metric> builder = ImmutableList.builder();
        return builder.addAll(all).build();
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

    @Override
    public void addMetric(Metric metric, MonitorRepository repository) {
        MetricEntity metricEntity = new MetricBuilder()
                .setType(metric.getType())
                .setName(metric.getName())
                .setTimeInterval(metric.getTimeInterval())
                .setMonitor(this)
                .build();

        metrics.add(metricEntity);
        //        LOGGER.info(this.metrics);
        repository.save(this);

    }
}
