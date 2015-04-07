package br.com.pandox.nursery.monitors;

import br.com.pandox.nursery.domain.metricData.MetricDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MemoryMonitor {


    @Autowired
    private MetricDataService metricDataService;

    @Scheduled(fixedDelay = 30000)
    public void read() {
        long freeMemory = Runtime.getRuntime().freeMemory() / 1000000;
        long total = Runtime.getRuntime().totalMemory() / 1000000;
        metricDataService.create((int) (total - freeMemory), 1L);
    }
}
