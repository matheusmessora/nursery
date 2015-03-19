package br.com.pandox.nursery.domain.monitor.repository;

import br.com.pandox.nursery.domain.monitor.entity.MonitorEntity;
import org.springframework.data.repository.CrudRepository;

public interface MonitorRepository extends CrudRepository<MonitorEntity, Long>  {
}
