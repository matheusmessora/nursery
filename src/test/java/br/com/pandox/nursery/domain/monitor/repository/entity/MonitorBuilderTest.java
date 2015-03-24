package br.com.pandox.nursery.domain.monitor.repository.entity;

import br.com.pandox.nursery.domain.monitor.entity.MonitorBuilder;
import br.com.pandox.nursery.domain.monitor.entity.MonitorEntity;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.infrastructure.exception.DomainIllegalAttributeException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MonitorBuilderTest {


    @Test
    public void should_build_with_status_null() {
        MonitorBuilder builder = new MonitorBuilder();
        Monitor result = builder.setStatus("").build();

        Assert.assertEquals(result.getStatus(), MonitorEntity.Status.UNREGISTERED);
    }

    @Test(expectedExceptions = DomainIllegalAttributeException.class)
    public void should_throw_illegalArgument_when_status_incompatible() {
        MonitorBuilder builder = new MonitorBuilder();
        Monitor result = builder.setStatus("BlaBlaBla").build();
    }

}
