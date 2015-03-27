package br.com.pandox.nursery.boot.environment.db;

import br.com.pandox.nursery.domain.metric.factory.MetricFactory;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.metric.service.MetricService;
import br.com.pandox.nursery.domain.monitor.factory.MonitorFactory;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.sevice.MonitorService;
import br.com.pandox.nursery.view.rest.metric.MetricDTO;
import br.com.pandox.nursery.view.rest.monitor.MonitorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("preload")
public class StagingDatabase {

    @Autowired
    private MonitorService monitorService;

    @Autowired
    private MetricService metricService;

    @Autowired
    private MonitorFactory monitorFactory;

    @Autowired
    private MetricFactory metricFactory;

    @PostConstruct
    public void init() {
        loadMonitors();
        loadMetrics();
    }

    private void loadMonitors(){
        MonitorDTO dto = new MonitorDTO();
        dto.setName("Apache");
        dto.setMachine("localhost");
        Monitor monitor = monitorFactory.fabric(dto);
        monitor = monitorService.create(monitor);


        dto.setName("java-war");
        dto.setMachine("localhost");
        monitor = monitorFactory.fabric(dto);
        monitor = monitorService.create(monitor);

        dto.setName("mongodb");
        dto.setMachine("a1-baidu");
        monitor = monitorFactory.fabric(dto);
        monitor = monitorService.create(monitor);

        dto.setName("apache");
        dto.setMachine("a1-baidu");
        monitor = monitorFactory.fabric(dto);
        monitor = monitorService.create(monitor);
    }

    private void loadMetrics() {
        MetricDTO dto = new MetricDTO();
        dto.setName("Memory");
        dto.setTime_interval(1);
        dto.setMonitor(new MonitorDTO(1L));
        Metric metric = metricFactory.createFrom(dto);
        metricService.create(metric, 1L);

        dto = new MetricDTO();
        dto.setName("CPU");
        dto.setTime_interval(1);
        dto.setMonitor(new MonitorDTO(1L));
        metric = metricFactory.createFrom(dto);
        metricService.create(metric, 1L);

        dto = new MetricDTO();
        dto.setName("Threads");
        dto.setTime_interval(1);
        dto.setMonitor(new MonitorDTO(2L));
        metric = metricFactory.createFrom(dto);
        metricService.create(metric, 2L);
    }
}
