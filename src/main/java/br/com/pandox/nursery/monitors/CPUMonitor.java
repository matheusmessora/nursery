package br.com.pandox.nursery.monitors;

import br.com.pandox.nursery.domain.metricData.MetricDataService;
import com.sun.management.OperatingSystemMXBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;

@Service
@Profile("self-monitored")
public class CPUMonitor {


    @Autowired
    private MetricDataService metricDataService;

    @Scheduled(fixedDelay = 30000)
    public void read() {
        OperatingSystemMXBean mxBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        double cpu = mxBean.getSystemCpuLoad() * 100;
        metricDataService.create((int) cpu, 2L);
    }
}
