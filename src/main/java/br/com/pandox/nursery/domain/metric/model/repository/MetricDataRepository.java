package br.com.pandox.nursery.domain.metric.model.repository;

import br.com.pandox.nursery.domain.metric.model.MetricDataEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MetricDataRepository extends CrudRepository<MetricDataEntity, Long>{

    List<MetricDataEntity> findByMetric_idOrderByIdAsc(Long metricId);
}
