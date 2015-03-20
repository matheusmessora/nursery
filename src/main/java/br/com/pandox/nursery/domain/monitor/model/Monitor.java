package br.com.pandox.nursery.domain.monitor.model;


import br.com.pandox.nursery.Model;
import br.com.pandox.nursery.domain.monitor.repository.MonitorRepository;
import br.com.pandox.nursery.domain.monitor.repository.entity.MonitorEntity;

public interface Monitor extends Model {

    void save(MonitorRepository repository);

    Long getId();

    String getMachine();

    MonitorEntity.Status getStatus();

    String getName();

    boolean isInSync();

}
