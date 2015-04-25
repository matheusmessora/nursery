package br.com.pandox.nursery.domain.metric.model;

import br.com.pandox.nursery.domain.threshold.model.ThresholdEntity;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MetricEntityTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void throw_error_when_add_null_threshold() {
        Metric entity = new MetricEntity();
        entity.addThreshold(null);
    }

    @Test
    public void append_threshold_when_add() {
        Metric entity = new MetricEntity();
        entity.addThreshold(new ThresholdEntity());
        Assert.assertEquals(entity.getThresholds().size(), 1);
    }

}
