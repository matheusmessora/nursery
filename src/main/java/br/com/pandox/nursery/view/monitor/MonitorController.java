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

import java.util.ArrayList;
import java.util.List;

@RestController
public class MonitorController implements ResourceController<MonitorDTO> {

    @Autowired
    private MonitorLoader loader;

    @Autowired
    private MonitorCommandExecutor executor;

    @Autowired
    private MonitorFactory factory;

    @RequestMapping(value = "/monitor")
    public ResponseEntity<List<MonitorDTO>> findAll() {
        List<Monitor> all = loader.loadAll();

        List<MonitorDTO> dtos = parse(all);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/monitor/{id}")
    @Override public ResponseEntity<MonitorDTO> findById(@PathVariable Long id) {
        Monitor monitor = loader.loadByID(id);

        MonitorDTO dto = factory.fabric(monitor);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "/monitor", method = RequestMethod.POST)
    public ResponseEntity<MonitorDTO> save(@RequestBody MonitorDTO dto) {
        CreateMonitorCommand command = new CreateMonitorCommand(dto);
        Monitor monitor = executor.execute(command);

        dto = factory.fabric(monitor);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    private List<MonitorDTO> parse(List<Monitor> monitor) {
        List<MonitorDTO> dtos = new ArrayList<>();
        for (Monitor entity : monitor) {
            dtos.add(factory.fabric(entity));
        }
        return dtos;
    }
}
