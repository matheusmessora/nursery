package br.com.pandox.nursery.monitors;

import br.com.pandox.nursery.domain.metricData.MetricDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MemoryMonitorImpl implements MemoryMonitor {


    @Autowired
    private MetricDataService metricDataService;

    @Override
    @Scheduled(fixedDelay = 30000)
    public void read() {
        long memoryMB = Runtime.getRuntime().freeMemory() / 1000000;
        long maxMemoryMB = Runtime.getRuntime().maxMemory() / 1000000;
        metricDataService.create((int) memoryMB, 1L);
    }
}
