package br.com.pandox.nursery.domain.alert.factory.impl;

import br.com.pandox.nursery.domain.alert.Alert;
import br.com.pandox.nursery.domain.alert.factory.AlertFactory;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.metric.model.MetricMock;
import br.com.pandox.nursery.domain.metric.model.vo.MetricData;
import br.com.pandox.nursery.domain.metricData.model.MetricDataMock;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlertFactoryImplTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void should_throw_exception_when_metric_null() {
        AlertFactory factory = new AlertFactoryImpl();
        factory.from(null, new MetricDataMock());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void should_throw_exception_when_data_null() {
        AlertFactory factory = new AlertFactoryImpl();
        factory.from(new MetricMock(), null);
    }

    @Test
    public void should_return_alert_build_from_metric() {
        Metric metric = new MetricMock();
        MetricData data = new MetricDataMock();

        AlertFactory factory = new AlertFactoryImpl();
        Alert alert = factory.from(metric, data);


        Assert.assertNotNull(alert);
        Assert.assertEquals(alert.alertedBy(), metric);
    }


}
