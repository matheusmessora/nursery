package br.com.pandox.nursery.repository;


import br.com.pandox.nursery.model.TestEntity;
import org.springframework.data.repository.CrudRepository;

public interface TestRepository extends CrudRepository<TestEntity, Long> {

}
