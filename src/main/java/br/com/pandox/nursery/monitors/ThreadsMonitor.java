package br.com.pandox.nursery.monitors;

import br.com.pandox.nursery.domain.metricData.MetricDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Profile("self-monitored")
public class ThreadsMonitor {


    @Autowired
    private MetricDataService metricDataService;

    @Scheduled(fixedDelay = 30000)
    public void read() {
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        metricDataService.create(allStackTraces.size(), 4L);
    }

}
