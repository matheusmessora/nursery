package br.com.pandox.nursery.domain.alert.factory.impl;

import br.com.pandox.nursery.domain.alert.Alert;
import br.com.pandox.nursery.domain.alert.factory.AlertFactory;
import br.com.pandox.nursery.domain.alert.model.AlertBuilder;
import br.com.pandox.nursery.domain.metric.model.MetricMock;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlertFactoryImplTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void should_throw_exception_when_metric_null() {
        AlertFactory factory = new AlertFactoryImpl();
        factory.from(null);
    }

    @Test
    public void should_return_alert_build_from_metric() {
        AlertBuilder builder = new AlertBuilder();
        MetricMock metric = new MetricMock();
        builder.setMetric(metric);

        AlertFactory factory = new AlertFactoryImpl();
        Alert alert = factory.from(metric);


        Assert.assertNotNull(alert);
        Assert.assertEquals(alert.alertedBy(), metric);
    }


}
