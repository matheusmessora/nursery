package br.com.pandox.nursery.domain.monitor.factory;


import br.com.pandox.nursery.domain.monitor.view.MonitorDTO;
import br.com.pandox.nursery.domain.monitor.entity.MonitorEntityFactory;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.model.MonitorBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonitorFactoryImpl implements MonitorFactory {

    @Autowired
    private MonitorEntityFactory entityFactory;

    @Override
    public Monitor fabric(MonitorDTO dto) {
        return new MonitorBuilder()
                .setId(dto.getId())
                .setMachine(dto.getMachine())
                .setName(dto.getName())
                .setStatus(dto.getStatus())
                .setVersion(dto.getVersion())
                .setFactory(entityFactory)
                .fabric();
    }

    public MonitorDTO fabric(Monitor monitor){
        MonitorDTO dto = new MonitorDTO();
        BeanUtils.copyProperties(monitor, dto);
        return dto;
    }
}
