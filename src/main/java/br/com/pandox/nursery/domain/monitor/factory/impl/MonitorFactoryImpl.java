package br.com.pandox.nursery.domain.monitor.factory.impl;


import br.com.pandox.nursery.domain.metric.factory.MetricFactory;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.monitor.factory.MonitorFactory;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.model.MonitorBuilder;
import br.com.pandox.nursery.domain.monitor.model.MonitorEntity;
import br.com.pandox.nursery.view.rest.Link;
import br.com.pandox.nursery.view.rest.monitor.MonitorDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MonitorFactoryImpl implements MonitorFactory {

    @Autowired
    private MetricFactory metricFactory;

    @Override
    public Monitor createFrom(MonitorEntity entity, boolean loadMetrics) {
        MonitorBuilder builder = new MonitorBuilder()
                .setId(entity.getId())
                .setName(entity.getName())
                .setMachine(entity.getMachine())
                .setStatus(entity.getStatus());


        if(loadMetrics) {
            Set<Metric> metrics = new HashSet<>();
            for (Metric metric : entity.getMetrics()) {
                metrics.add(metric);
            }
            builder.setMetrics(metrics);
        }
        return builder.build();
    }

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

        dto.addLink(new Link("/api/vSNAPSHOT/metric?monitor_id=" + dto.getId(), "metrics"));

        return dto;
    }

}
