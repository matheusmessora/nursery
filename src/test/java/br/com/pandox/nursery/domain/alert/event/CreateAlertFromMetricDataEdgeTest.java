package br.com.pandox.nursery.domain.alert.event;

import br.com.pandox.nursery.domain.metric.model.MetricMock;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAlertFromMetricDataEdgeTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void should_throw_exception_when_metric_null() {
        new CreateAlertFromMetricDataEdge(null);
    }

    @Test
    public void should_return_metric() {
        MetricMock metric = new MetricMock();
        CreateAlertFromMetricDataEdge edge = new CreateAlertFromMetricDataEdge(metric);

        Assert.assertEquals(metric, edge.getMetric());

    }

}
