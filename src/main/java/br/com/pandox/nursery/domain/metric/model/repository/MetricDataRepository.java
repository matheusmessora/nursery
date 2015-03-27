package br.com.pandox.nursery.domain.metric.model.repository;

import br.com.pandox.nursery.domain.metric.model.MetricDataEntity;
import org.springframework.data.repository.CrudRepository;

public interface MetricDataRepository extends CrudRepository<MetricDataEntity, Long>{
}
