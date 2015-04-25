package br.com.pandox.nursery.domain.monitor.service;

import br.com.pandox.nursery.domain.CommandException;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.model.MonitorEntity;
import br.com.pandox.nursery.domain.monitor.model.repository.MonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonitorServiceImpl implements MonitorService {

    @Autowired
    private MonitorRepository repository;

    @Override
    public Monitor create(Monitor monitor) {
        MonitorEntity domain = repository.findByMachineAndName(monitor.getMachine(), monitor.getName());
        if(domain != null) {
            throw new CommandException(String.format("Given monitor already exists with name %s inside machine %s", monitor.getName(), monitor.getMachine()));
        }
        monitor.setStatus(Monitor.Status.READY);

        MonitorEntity entity = (MonitorEntity) monitor;
        return repository.save(entity);
    }
}
