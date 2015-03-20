package br.com.pandox.nursery.domain.monitor.factory;


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
                .setId(dto.getId())
                .setMachine(dto.getMachine())
                .setName(dto.getName())
                .setStatus(dto.getStatus())
                .setVersion(dto.getVersion())
                .build();
    }

    public Monitor fabric() {
        return new MonitorBuilder().build();
    }

    public MonitorDTO fabric(Monitor monitor){
        MonitorDTO dto = new MonitorDTO();
        BeanUtils.copyProperties(monitor, dto);
        return dto;
    }

}
