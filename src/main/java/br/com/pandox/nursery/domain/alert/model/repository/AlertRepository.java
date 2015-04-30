package br.com.pandox.nursery.domain.alert.model.repository;

import br.com.pandox.nursery.domain.alert.Alert;
import br.com.pandox.nursery.domain.alert.model.AlertEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlertRepository extends CrudRepository<AlertEntity, Long>  {

    List<Alert> findByMetric_id(Long id);
}
