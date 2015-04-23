package br.com.pandox.nursery.domain.monitor.model.repository;

import br.com.pandox.nursery.domain.monitor.model.MonitorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MonitorRepository extends CrudRepository<MonitorEntity, Long>  {

    MonitorEntity findByMachineAndName(String machine, String name);

    MonitorEntity findByName(String name);

    Iterable<MonitorEntity> findByMachine(String name);

    @Query("SELECT m FROM MonitorEntity m LEFT JOIN FETCH m.metrics WHERE m.id = (:id)")
    MonitorEntity findOneLoadMetrics(@Param("id") Long id);

    @Query("SELECT DISTINCT m FROM MonitorEntity m LEFT JOIN FETCH m.metrics")
    Iterable<MonitorEntity> findAllLoadMetrics();

    @Query("SELECT m FROM MonitorEntity m JOIN FETCH m.metrics me WHERE m.machine= (:machine)")
    Iterable<MonitorEntity> findByMachineLoadMetrics(@Param("machine") String machine);

    @Query("SELECT m FROM MonitorEntity m JOIN FETCH m.metrics me WHERE m.machine= (:machine)")
    List<MonitorEntity> teste(@Param("machine") String machine);
}
