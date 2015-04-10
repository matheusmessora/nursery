package br.com.pandox.nursery.monitors;

import br.com.pandox.nursery.domain.metricData.MetricDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AppMemoryMonitor {


    @Autowired
    private MetricDataService metricDataService;

    @Scheduled(fixedDelay = 30000)
    public void read() {
        long freeMemory = getFreeMemory();
        long total = getTotalMemory();
        metricDataService.create((int) (total - freeMemory), 1L);
    }

    private long getFreeMemory() {
        return Runtime.getRuntime().freeMemory() / 1000000;
    }

    private long getTotalMemory() {
        return Runtime.getRuntime().totalMemory() / 1000000;
    }
}
