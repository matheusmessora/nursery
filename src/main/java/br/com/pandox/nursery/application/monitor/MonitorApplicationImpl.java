package br.com.pandox.nursery.application.monitor;

import br.com.pandox.nursery.domain.monitor.factory.MonitorFactory;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.repository.MonitorRepository;
import br.com.pandox.nursery.domain.monitor.service.MonitorService;
import br.com.pandox.nursery.view.monitor.MonitorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MonitorApplicationImpl implements MonitorApplication {

    @Autowired
    private MonitorFactory factory;

    @Autowired
    private MonitorService service;

    @Autowired
    private MonitorRepository repository;

    public MonitorDTO save(MonitorDTO monitorDTO){
        Monitor monitor = factory.fabric(monitorDTO);
        monitor.save(repository);

        return factory.fabric(monitor);
    }

    public MonitorDTO findByID(Long id) {
        Monitor monitor = service.findByID(id);
        return factory.fabric(monitor);
    }

    public List<MonitorDTO> findAll() {
        List<Monitor> monitors = service.findAll();

        List<MonitorDTO> dtos = new ArrayList<>();
        for (Monitor monitor : monitors) {
            MonitorDTO dto = factory.fabric(monitor);
            dtos.add(dto);
        }

        return dtos;
    }
}
