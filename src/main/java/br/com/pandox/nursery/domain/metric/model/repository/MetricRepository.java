package br.com.pandox.nursery.domain.metric.model.repository;

import br.com.pandox.nursery.domain.metric.model.MetricEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MetricRepository extends CrudRepository<MetricEntity,Long> {

    MetricEntity findByName(String name);

    List<MetricEntity> findByMonitor_Id(Long monitor_Id);

    @Query("SELECT m FROM MetricEntity m LEFT JOIN FETCH m.monitor monitor LEFT JOIN FETCH m.datas data WHERE m.id = (:id) ORDER BY data.id ASC")
    MetricEntity findOneLoadDatas(@Param("id") Long id);

}
