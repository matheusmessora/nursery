package br.com.pandox.nursery.domain.metric.entity.repository;

import br.com.pandox.nursery.domain.metric.entity.MetricEntity;
import org.springframework.data.repository.CrudRepository;

//@RepositoryDefinition(domainClass = MetricEntity.class, idClass = Long.class)
public interface MetricRepository extends CrudRepository<MetricEntity,Long> {

    MetricEntity findByName(String name);

}
