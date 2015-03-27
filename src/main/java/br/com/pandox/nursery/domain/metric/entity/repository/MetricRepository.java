package br.com.pandox.nursery.domain.metric.entity.repository;

import br.com.pandox.nursery.domain.metric.entity.MetricEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MetricRepository extends CrudRepository<MetricEntity,Long> {

    MetricEntity findByName(String name);

    List<MetricEntity> findByMonitor_Id(Long monitor_Id);

//    List<MetricEntity> findByMonitor(Monitor monitor);


    @Query("SELECT m FROM MetricEntity m LEFT JOIN FETCH m.monitor monitor LEFT JOIN FETCH m.datas data WHERE m.id = (:id) ORDER BY data.id ASC")
    MetricEntity findOneLoadDatas(@Param("id") Long id);

}
