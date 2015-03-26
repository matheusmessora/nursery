package br.com.pandox.nursery.domain.monitor.entity;

import br.com.pandox.nursery.domain.CommandException;
import br.com.pandox.nursery.domain.metric.entity.MetricBuilder;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import org.testng.annotations.Test;

public class MonitorTest {


    @Test(expectedExceptions = CommandException.class, expectedExceptionsMessageRegExp = "Can not add metric to Monitor in STOPPED status")
    public void throw_exception_on_addMetric_when_monitor_in_bad_state() {
        Monitor monitor = new MonitorBuilder().setStatus(Monitor.Status.STOPPED).build();

        Metric metric = new MetricBuilder().setTimeInterval(1).setName("a").build();
        monitor.addMetric(metric, null);
    }

}
