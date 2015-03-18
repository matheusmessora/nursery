package br.com.pandox.nursery.controller;


import br.com.pandox.nursery.entity.Monitor;
import br.com.pandox.nursery.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MonitorController implements ResourceController<Monitor> {

    @Autowired
    private MonitorService service;

    @RequestMapping(value = "/monitor")
    @Override public ResponseEntity<List<Monitor>> findAll() {
        List<Monitor> monitors = service.findAll();
        return new ResponseEntity<List<Monitor>>(monitors, HttpStatus.OK);
    }

    @RequestMapping(value = "/monitor/{id}")
    @Override public ResponseEntity<Monitor> findById(@PathVariable Long id) {
        Monitor monitor = service.persist(new Monitor());

        return new ResponseEntity<>(monitor, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/monitor", method = RequestMethod.POST)
    public ResponseEntity<Monitor> save() {
        Monitor monitor = service.persist(new Monitor());

        return new ResponseEntity<>(monitor, HttpStatus.CREATED);
    }
}
