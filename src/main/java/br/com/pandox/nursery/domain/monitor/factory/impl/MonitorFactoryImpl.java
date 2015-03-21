package br.com.pandox.nursery.domain.monitor.factory.impl;


import br.com.pandox.nursery.domain.monitor.factory.MonitorFactory;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.repository.entity.MonitorBuilder;
import br.com.pandox.nursery.view.monitor.MonitorDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MonitorFactoryImpl implements MonitorFactory {

    @Override
    public Monitor fabric(MonitorDTO dto) {
        return new MonitorBuilder()
                .setId(dto.id)
                .setMachine(dto.machine)
                .setName(dto.name)
                .setStatus(dto.status)
                .setVersion(dto.version)
                .build();
    }

    public MonitorDTO fabric(Monitor monitor){
        MonitorDTO dto = new MonitorDTO();
        BeanUtils.copyProperties(monitor, dto);
        dto.status = monitor.getStatus().name();
        return dto;
    }

}
