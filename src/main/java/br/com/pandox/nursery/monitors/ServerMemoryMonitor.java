package br.com.pandox.nursery.monitors;

import br.com.pandox.nursery.domain.metricData.MetricDataService;
import com.jezhumble.javasysmon.JavaSysMon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ServerMemoryMonitor {


    @Autowired
    private MetricDataService metricDataService;

    @Scheduled(fixedDelay = 30000)
    public void read() {
        long freeMemory = getFreeMemory();
        long total = getTotalMemory();
        metricDataService.create((int) (total - freeMemory), 3L);
    }

    private long getFreeMemory(){
        JavaSysMon monitor =   new JavaSysMon();
        return monitor.physical().getFreeBytes() / 1000000;
    }

    private long getTotalMemory(){
        JavaSysMon monitor =   new JavaSysMon();
        return monitor.physical().getTotalBytes() / 1000000;
    }
}
