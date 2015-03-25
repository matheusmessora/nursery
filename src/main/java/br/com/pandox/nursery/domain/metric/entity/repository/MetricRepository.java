package br.com.pandox.nursery.domain.metric.entity.repository;

import br.com.pandox.nursery.domain.metric.entity.MetricEntity;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MetricRepository extends CrudRepository<MetricEntity,Long> {

    MetricEntity findByName(String name);

    List<MetricEntity> findByMonitor(Monitor monitor);

}
