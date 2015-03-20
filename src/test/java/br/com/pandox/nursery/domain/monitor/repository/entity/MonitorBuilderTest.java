package br.com.pandox.nursery.domain.monitor.repository.entity;

import br.com.pandox.nursery.domain.monitor.model.Monitor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MonitorBuilderTest {


    @Test
    public void should_build_with_status_null() {
        MonitorBuilder builder = new MonitorBuilder();
        Monitor result = builder.setStatus("").build();

        Assert.assertNull(result.getStatus());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void should_throw_IAE_when_status_incompatible() {
        MonitorBuilder builder = new MonitorBuilder();
        Monitor result = builder.setStatus("BlaBlaBla").build();
    }

}