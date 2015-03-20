package br.com.pandox.nursery.view.monitor;


import br.com.pandox.nursery.application.monitor.MonitorApplication;
import br.com.pandox.nursery.view.ResourceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MonitorController implements ResourceController<MonitorDTO> {

    @Autowired
    private MonitorApplication application;

    @RequestMapping(value = "/monitor")
    @Override public ResponseEntity<List<MonitorDTO>> findAll() {
        List<MonitorDTO> all = application.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @RequestMapping(value = "/monitor/{id}")
    @Override public ResponseEntity<MonitorDTO> findById(@PathVariable Long id) {
        MonitorDTO monitorDTO = application.findByID(id);
        return new ResponseEntity<>(monitorDTO, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/monitor", method = RequestMethod.POST)
    public ResponseEntity<MonitorDTO> save(@RequestBody MonitorDTO monitorDTO) {
        monitorDTO = application.save(monitorDTO);
        return new ResponseEntity<>(monitorDTO, HttpStatus.CREATED);
    }
}
