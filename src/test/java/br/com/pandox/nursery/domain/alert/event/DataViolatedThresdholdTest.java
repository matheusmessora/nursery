package br.com.pandox.nursery.domain.alert.event;

import br.com.pandox.nursery.domain.metric.model.MetricMock;
import br.com.pandox.nursery.domain.metric.model.vo.MetricData;
import br.com.pandox.nursery.domain.metricData.model.MetricDataMock;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataViolatedThresdholdTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void should_throw_exception_when_metric_null() {
        new DataViolatedThresdhold(null, new MetricDataMock());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void should_throw_exception_when_data_null() {
        new DataViolatedThresdhold(new MetricMock(), null);
    }

    @Test
    public void should_return_metric() {
        MetricMock metric = new MetricMock();
        MetricData data = new MetricDataMock();
        DataViolatedThresdhold edge = new DataViolatedThresdhold(metric, data);

        Assert.assertEquals(metric, edge.getMetric());

    }

}
