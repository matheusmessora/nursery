package br.com.pandox.nursery.domain.alert.model;

import br.com.pandox.nursery.domain.alert.Alert;
import br.com.pandox.nursery.domain.metric.model.MetricMock;
import br.com.pandox.nursery.domain.metric.model.vo.MetricData;
import br.com.pandox.nursery.domain.metricData.model.MetricDataMock;
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

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void should_throw_exception_when_build_data_null() {
        AlertBuilder builder = new AlertBuilder();
        builder.setMetric(new MetricMock());
        builder.build();
    }

    @Test
    public void should_return_alert_build_from_metric() {
        MetricMock metric = new MetricMock();
        MetricData metricData = new MetricDataMock();


        Alert alert = new AlertBuilder()
                .setMetric(metric)
                .setData(metricData)
                .build();

        Assert.assertNotNull(alert);
        Assert.assertEquals(alert.alertedBy(), metric);
    }

}
