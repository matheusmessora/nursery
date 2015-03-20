package br.com.pandox.nursery.domain.monitor.model;


import br.com.pandox.nursery.Model;
import br.com.pandox.nursery.domain.monitor.repository.MonitorRepository;

public interface Monitor extends Model {

    void save(MonitorRepository repository);

    Long getId();

    String getMachine();

    String getStatus();

    String getName();

}
