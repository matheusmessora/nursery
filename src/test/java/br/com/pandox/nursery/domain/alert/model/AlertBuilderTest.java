package br.com.pandox.nursery.domain.alert.model;

import br.com.pandox.nursery.domain.alert.Alert;
import br.com.pandox.nursery.domain.metric.model.MetricMock;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlertBuilderTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void should_throw_exception_when_metric_null() {
        AlertBuilder builder = new AlertBuilder();
        builder.setMetric(null);
        builder.build();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void should_throw_exception_when_build_metric_null() {
        AlertBuilder builder = new AlertBuilder();
        builder.build();
    }

    @Test
    public void should_return_alert_build_from_metric() {
        AlertBuilder builder = new AlertBuilder();
        MetricMock metric = new MetricMock();
        builder.setMetric(metric);
        Alert alert = builder.build();

        Assert.assertNotNull(alert);
        Assert.assertEquals(alert.alertedBy(), metric);
    }

}
