package br.com.pandox.nursery.controller;

import br.com.pandox.nursery.entity.TestEntity;
import com.sun.xml.internal.ws.api.pipe.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class TestController {

    private static final Logger LOGGER = LogManager.getLogger();

    @PostConstruct
    public void init(){
        LOGGER.debug("ENTROU");
    }


    @RequestMapping(value = "/test", produces = "application/json")
    public ResponseEntity teste() {
        return new ResponseEntity(new TestEntity(), HttpStatus.OK);
    }

}
