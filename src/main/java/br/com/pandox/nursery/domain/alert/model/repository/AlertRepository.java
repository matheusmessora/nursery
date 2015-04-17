package br.com.pandox.nursery.domain.alert.model.repository;

import br.com.pandox.nursery.domain.alert.model.AlertEntity;
import org.springframework.data.repository.CrudRepository;

public interface AlertRepository extends CrudRepository<AlertEntity, Long>  {

}
