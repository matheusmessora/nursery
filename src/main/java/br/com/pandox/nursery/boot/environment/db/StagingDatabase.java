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
import java.util.Random;

@Component
@Profile("preload")
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
        loadMonitors();
        loadMetrics();
    }

    @Scheduled(fixedDelay = 1000)
    public void start() {
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int min = 25;
        int max = 60;
        int randomNum = rand.nextInt((max - min) + 1) + min;
        metricDataService.create(randomNum, 1L);

        randomNum = rand.nextInt((max - min) + 1) + min;
        metricDataService.create(randomNum, 2L);

        randomNum = rand.nextInt((max - min) + 1) + min;
        metricDataService.create(randomNum, 3L);
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
        Metric metric = metricFactory.createFrom(dto);
        metricService.create(metric, 1L);

        dto = new MetricDTO();
        dto.setName("CPU");
        dto.setTime_interval(1);
        metric = metricFactory.createFrom(dto);
        metricService.create(metric, 1L);

        dto = new MetricDTO();
        dto.setName("Threads");
        dto.setTime_interval(1);
        metric = metricFactory.createFrom(dto);
        metricService.create(metric, 2L);
    }
}
