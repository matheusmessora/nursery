package br.com.pandox.nursery.domain.metric.entity.repository;

import br.com.pandox.nursery.domain.metric.entity.MetricEntity;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MetricRepository extends CrudRepository<MetricEntity,Long> {

    MetricEntity findByName(String name);

    List<MetricEntity> findByMonitor(Monitor monitor);


    @Query("SELECT m FROM MetricEntity m LEFT JOIN FETCH m.datas WHERE m.id = (:id)")
    MetricEntity findOneLoadDatas(@Param("id") Long id);

}
