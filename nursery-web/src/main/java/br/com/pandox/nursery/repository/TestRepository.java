package br.com.pandox.nursery.repository;


import br.com.pandox.nursery.entity.TestEntity;
import org.springframework.data.repository.CrudRepository;

public interface TestRepository extends CrudRepository<TestEntity, Long> {
}
