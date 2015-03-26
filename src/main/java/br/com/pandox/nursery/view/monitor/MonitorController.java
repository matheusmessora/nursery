package br.com.pandox.nursery.view.monitor;


import br.com.pandox.nursery.domain.DomainNotFoundException;
import br.com.pandox.nursery.domain.monitor.command.CreateMonitorCommand;
import br.com.pandox.nursery.domain.monitor.factory.MonitorFactory;
import br.com.pandox.nursery.domain.monitor.loader.MonitorLoader;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.infrastructure.command.executor.CommandExecutor;
import br.com.pandox.nursery.view.exception.DomainIllegalAttributeException;
import br.com.pandox.nursery.view.exception.DomainMandatoryAttributeException;
import br.com.pandox.nursery.view.exception.ResourceNotFoundException;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Scope("request")
public class MonitorController {

    @Autowired
    private MonitorLoader loader;

    @Autowired
    private CommandExecutor executor;

    @Autowired
    private MonitorFactory factory;

    @RequestMapping(value = "/monitor", method = RequestMethod.GET)
    public ResponseEntity<List<MonitorDTO>> findAll() {
        List<Monitor> all = loader.loadAll();

        List<MonitorDTO> dtos = parse(all);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/monitor/{id}", method = RequestMethod.GET)
    public ResponseEntity<MonitorDTO> findById(@PathVariable Long id) {
        try {
            Monitor monitor = loader.loadByID(id, false);
            MonitorDTO dto = factory.fabric(monitor);
            return new ResponseEntity<>(dto, HttpStatus.OK);

        } catch(DomainNotFoundException ex){
            throw new ResourceNotFoundException("monitor");
        }
    }

    @RequestMapping(value = "/monitor", method = RequestMethod.POST)
    public ResponseEntity<MonitorDTO> save(@RequestBody MonitorDTO dto) {
        validate(dto);

        executor.execute(new CreateMonitorCommand(dto));
        Optional<Monitor> monitor = loader.loadByName(dto.getName());
        if(!monitor.isPresent()) {
            throw new ResourceNotFoundException("monitor");
        }

        dto = factory.fabric(monitor.get());
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    private void validate(MonitorDTO dto){
        if(StringUtils.isEmpty(dto.getName())) {
            throw new DomainMandatoryAttributeException("name");
        }
        if(StringUtils.isEmpty(dto.getMachine())) {
            throw new DomainMandatoryAttributeException("machine");
        }
        if(!StringUtils.isEmpty(dto.getStatus())) {
            try {
                Monitor.Status.valueOf(dto.getStatus());
            } catch(IllegalArgumentException ex) {
                throw new DomainIllegalAttributeException("monitor", "status");
            }
        }
    }

    private List<MonitorDTO> parse(List<Monitor> monitor) {
        List<MonitorDTO> dtos = new ArrayList<>();
        for (Monitor entity : monitor) {
            dtos.add(factory.fabric(entity));
        }
        return dtos;
    }
}
