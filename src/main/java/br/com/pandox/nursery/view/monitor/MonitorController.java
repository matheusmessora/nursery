package br.com.pandox.nursery.view.monitor;


import br.com.pandox.nursery.domain.monitor.loader.MonitorLoader;
import br.com.pandox.nursery.domain.monitor.command.impl.CreateMonitorCommand;
import br.com.pandox.nursery.domain.monitor.command.executor.MonitorCommandExecutor;
import br.com.pandox.nursery.domain.monitor.factory.MonitorFactory;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.infrastructure.controller.rest.ResourceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MonitorController implements ResourceController<Monitor> {

    @Autowired
    private MonitorLoader loader;

    @Autowired
    private MonitorCommandExecutor executor;

    @Autowired
    private MonitorFactory factory;

    @RequestMapping(value = "/monitor")
    @Override public ResponseEntity<List<Monitor>> findAll() {
        List<Monitor> all = loader.loadAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @RequestMapping(value = "/monitor/{id}")
    @Override public ResponseEntity<Monitor> findById(@PathVariable Long id) {
        Monitor Monitor = loader.loadByID(id);
        return new ResponseEntity<>(Monitor, HttpStatus.OK);
    }

    @RequestMapping(value = "/monitor", method = RequestMethod.POST)
    public ResponseEntity<MonitorDTO> save(@RequestBody MonitorDTO dto) {
        CreateMonitorCommand command = new CreateMonitorCommand(dto);
        Monitor monitor = executor.execute(command);

        dto = factory.fabric(monitor);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
