package br.com.pandox.nursery.view.rest.monitor;


import br.com.pandox.nursery.domain.DomainNotFoundException;
import br.com.pandox.nursery.domain.monitor.factory.MonitorFactory;
import br.com.pandox.nursery.domain.monitor.loader.MonitorLoader;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.service.MonitorService;
import br.com.pandox.nursery.view.exception.DomainIllegalAttributeException;
import br.com.pandox.nursery.view.exception.DomainMandatoryAttributeException;
import br.com.pandox.nursery.view.exception.ResourceNotFoundException;
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
public class MonitorEndpoint {

    @Autowired
    private MonitorLoader loader;

    @Autowired
    private MonitorFactory factory;


    @Autowired
    private MonitorService service;


    @RequestMapping(value = "/api/monitor", method = RequestMethod.GET)
    public ResponseEntity<List<MonitorDTO>> findAll() {
        List<Monitor> all = loader.loadAll(false);

        List<MonitorDTO> dtos = parse(all);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/monitor/{id}", method = RequestMethod.GET)
    public ResponseEntity<MonitorDTO> findById(@PathVariable Long id) {
        try {
            Monitor monitor = loader.loadByID(id, false);
            MonitorDTO dto = new MonitorDTO(monitor);
            return new ResponseEntity<>(dto, HttpStatus.OK);

        } catch(DomainNotFoundException ex){
            throw new ResourceNotFoundException("monitor");
        }
    }

    @RequestMapping(value = "/api/monitor", method = RequestMethod.POST)
    public ResponseEntity<MonitorDTO> save(@RequestBody MonitorDTO dto) {
        validate(dto);

        Monitor monitor = service.create(factory.from(dto));

        dto = new MonitorDTO(monitor);
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
            dtos.add(new MonitorDTO(entity));
        }
        return dtos;
    }
}
