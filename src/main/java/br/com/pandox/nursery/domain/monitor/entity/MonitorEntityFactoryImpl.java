package br.com.pandox.nursery.domain.monitor.entity;

import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.repository.MonitorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonitorEntityFactoryImpl implements MonitorEntityFactory {

    @Autowired
    private MonitorRepository repository;

    @Override
    public MonitorEntity fabric(Monitor monitor) {
        MonitorEntity entity = new MonitorEntity();
        BeanUtils.copyProperties(monitor, entity);

        entity.setRepository(repository);

        return entity;
    }
}
