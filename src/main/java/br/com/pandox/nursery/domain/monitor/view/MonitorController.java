package br.com.pandox.nursery.domain.monitor.view;


import br.com.pandox.nursery.FactoryStrategy;
import br.com.pandox.nursery.controller.ResourceController;
import br.com.pandox.nursery.domain.monitor.factory.MonitorFactory;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MonitorController implements ResourceController<MonitorDTO> {

//    @Autowired
//    private FactoryStrategy factory;

    @Autowired
    private MonitorFactory monitorFactory;

    @RequestMapping(value = "/monitor")
    @Override public ResponseEntity<List<MonitorDTO>> findAll() {
//        List<Monitor> monitorEntities = service.findAll();
//        return new ResponseEntity<>(monitorEntities, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/monitor/{id}")
    @Override public ResponseEntity<MonitorDTO> findById(@PathVariable Long id) {
//        Monitor Monitor = service.findById(id);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/monitor", method = RequestMethod.POST)
    public ResponseEntity<MonitorDTO> save(@RequestBody MonitorDTO monitorDTO) {
        Monitor monitor = monitorFactory.fabric(monitorDTO);
        monitor.save();

        monitorDTO = monitorFactory.fabric(monitor);
        return new ResponseEntity<>(monitorDTO, HttpStatus.CREATED);
    }
}
