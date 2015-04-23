package br.com.pandox.nursery.domain.monitor.factory.impl;


import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.monitor.factory.MonitorFactory;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.model.MonitorBuilder;
import br.com.pandox.nursery.domain.monitor.model.MonitorEntity;
import br.com.pandox.nursery.view.rest.monitor.MonitorDTO;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MonitorFactoryImpl implements MonitorFactory {

    @Override
    public Monitor from(MonitorEntity entity, boolean loadMetrics) {
        MonitorBuilder builder = new MonitorBuilder()
                .setId(entity.getId())
                .setName(entity.getName())
                .setMachine(entity.getMachine())
                .setStatus(entity.getStatus());

        loadMetrics(builder, entity, loadMetrics);


        return builder.build();
    }

    @Override
    public Monitor from(MonitorDTO dto) {
        return new MonitorBuilder()
                .setId(dto.id)
                .setMachine(dto.machine)
                .setName(dto.name)
                .setStatus(dto.status)
                .setVersion(dto.version)
                .build();
    }

    protected void loadMetrics(MonitorBuilder builder, MonitorEntity entity, boolean loadMetrics){
        if(!loadMetrics) {
            return;
        }


        Set<Metric> metrics = new HashSet<>();
        for (Metric metric : entity.getMetrics()) {
            metrics.add(metric);
        }
        builder.setMetrics(metrics);
    }

}
