package br.com.pandox.nursery.boot.environment.db;

import br.com.pandox.nursery.domain.metric.factory.MetricFactory;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.metric.service.MetricService;
import br.com.pandox.nursery.domain.metricData.MetricDataService;
import br.com.pandox.nursery.domain.monitor.factory.MonitorFactory;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.sevice.MonitorService;
import br.com.pandox.nursery.view.rest.metric.MetricDTO;
import br.com.pandox.nursery.view.rest.monitor.MonitorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

@Component
@Profile("self-monitored")
public class StagingDatabase {

    @Autowired
    private MonitorService monitorService;

    @Autowired
    private MetricService metricService;

    @Autowired
    private MetricDataService metricDataService;

    @Autowired
    private MonitorFactory monitorFactory;

    @Autowired
    private MetricFactory metricFactory;

    @PostConstruct
    public void init() {
        Monitor monitor = loadMonitors();
        loadMetrics(monitor.getId());
    }

    @Scheduled(fixedDelay = 60000)
    public void start() {
        long memoryMB = Runtime.getRuntime().freeMemory() / 1000000;
        metricDataService.create((int) memoryMB, 1L);

        File root = File.listRoots()[0];
        long freeSpace = root.getFreeSpace() / 1000000;
        metricDataService.create((int) freeSpace, 2L);
    }

    private Monitor loadMonitors(){
        MonitorDTO dto = new MonitorDTO();
        dto.setName("java-war");
        dto.setMachine("localhost");
        Monitor monitor = monitorFactory.fabric(dto);
        return monitorService.create(monitor);
    }

    private void loadMetrics(Long monitorId) {
        MetricDTO dto = new MetricDTO();
        dto.setName("Free Memory");
        dto.setTime_interval(1);
        Metric metric = metricFactory.createFrom(dto);
        metricService.create(metric, monitorId);

        dto = new MetricDTO();
        dto.setName("Free space");
        dto.setTime_interval(1);
        metric = metricFactory.createFrom(dto);
        metricService.create(metric, monitorId);
    }
}
