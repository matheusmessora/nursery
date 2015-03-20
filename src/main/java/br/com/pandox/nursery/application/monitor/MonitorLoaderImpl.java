package br.com.pandox.nursery.application.monitor;

import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.repository.MonitorRepository;
import br.com.pandox.nursery.domain.monitor.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitorLoaderImpl implements MonitorLoader {

    @Autowired
    private MonitorService service;

    @Autowired
    private MonitorRepository repository;

    public Monitor loadByID(Long id) {
        return service.findByID(id);
    }

    public List<Monitor> loadAll() {
        return service.loadAll();
    }
}
