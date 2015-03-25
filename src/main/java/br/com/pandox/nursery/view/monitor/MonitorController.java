package br.com.pandox.nursery.view.monitor;


import br.com.pandox.nursery.domain.DomainNotFoundException;
import br.com.pandox.nursery.domain.monitor.command.executor.SimpleMonitorCommandExecutor;
import br.com.pandox.nursery.domain.monitor.command.impl.CreateMonitorCommand;
import br.com.pandox.nursery.domain.monitor.entity.MonitorEntity;
import br.com.pandox.nursery.domain.monitor.factory.MonitorFactory;
import br.com.pandox.nursery.domain.monitor.loader.MonitorLoader;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.infrastructure.controller.rest.ResourceController;
import br.com.pandox.nursery.view.exception.ResourceNotFoundException;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Scope("request")
public class MonitorController implements ResourceController<MonitorDTO> {

    @Autowired
    private MonitorLoader loader;

    @Autowired
    private SimpleMonitorCommandExecutor executor;

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
        try {
            Monitor monitor = loader.loadByID(id);
            MonitorDTO dto = factory.fabric(monitor);
            return new ResponseEntity<>(dto, HttpStatus.OK);

        } catch(DomainNotFoundException ex){
            throw new ResourceNotFoundException("monitor");
        }
    }

    @RequestMapping(value = "/monitor", method = RequestMethod.POST)
    public ResponseEntity<MonitorDTO> save(@RequestBody MonitorDTO dto) {
        executor.execute(new CreateMonitorCommand(dto));
        Optional<MonitorEntity> monitor = loader.loadByName(dto.getName());
        if(!monitor.isPresent()) {
            throw new ResourceNotFoundException("monitor");
        }

        dto = factory.fabric(monitor.get());
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
