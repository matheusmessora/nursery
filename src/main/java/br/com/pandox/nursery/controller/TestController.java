package br.com.pandox.nursery.controller;

import br.com.pandox.nursery.model.TestEntity;
import br.com.pandox.nursery.repository.TestRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class TestController {

    private static final Logger LOGGER = LogManager.getLogger();

    @PostConstruct
    public void init(){
        LOGGER.debug("ENTROU");
    }

    @Autowired
    private TestRepository testRepository;

    @RequestMapping(value = "/test", produces = "application/json")
    public ResponseEntity teste() {
        TestEntity entity = new TestEntity();
//        model.setId(1L);
        entity.setName("ABC");
        entity = testRepository.save(entity);

        Iterable<TestEntity> entities = testRepository.findAll();
        LOGGER.debug(entities);

        return new ResponseEntity(entities, HttpStatus.OK);
    }

}
