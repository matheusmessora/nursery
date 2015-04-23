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
@Profile("self-monitored")
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
        Monitor monitor = loadAppMonitors();
        loadServerMonitors();

        loadMetrics(monitor.getId());
    }

    private void loadServerMonitors() {
        MonitorDTO dto = new MonitorDTO();
        dto.setName("machine");
        dto.setMachine("localhost");
        Monitor monitor = monitorFactory.from(dto);
        monitor = monitorService.create(monitor);

        MetricDTO metricDTO = new MetricDTO();
        metricDTO.setName("Memory");
        metricDTO.setTime_interval(1);
        metricDTO.setMax_value(8192);
        Metric metric = metricFactory.from(metricDTO);
        metricService.create(metric, monitor.getId());

        metricDTO = new MetricDTO();
        metricDTO.setName("CPU");
        metricDTO.setTime_interval(1);
        metricDTO.setMax_value(100);
        metric = metricFactory.from(metricDTO);
        metricService.create(metric, monitor.getId());
    }

    private Monitor loadAppMonitors(){
        MonitorDTO dto = new MonitorDTO();
        dto.setName("java-war");
        dto.setMachine("localhost");
        Monitor monitor = monitorFactory.from(dto);
        return monitorService.create(monitor);
    }

    private void loadMetrics(Long monitorId) {
        MetricDTO dto = new MetricDTO();
        dto.setName("Memory");
        dto.setTime_interval(1);
        dto.setMax_value(1024);
        dto.setEdgeLowValue(1);
        dto.setEdgeHighValue(10);
        Metric metric = metricFactory.from(dto);
        metricService.create(metric, monitorId);

        dto = new MetricDTO();
        dto.setName("Threads");
        dto.setTime_interval(1);
        metric = metricFactory.from(dto);
        metricService.create(metric, monitorId);

        dto = new MetricDTO();
        dto.setName("ResponseTime");
        dto.setTime_interval(1);
        metric = metricFactory.from(dto);
        metricService.create(metric, monitorId);
    }
}
