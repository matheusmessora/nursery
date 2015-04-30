package br.com.pandox.nursery.domain.alert.event;

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
        new DataViolatedThresdhold(1L, null);
    }

    @Test
    public void should_return_metric() {
        MetricData data = new MetricDataMock();
        DataViolatedThresdhold edge = new DataViolatedThresdhold(1L, data);

        Assert.assertEquals(1L, edge.getMetricId().longValue());

    }

}
