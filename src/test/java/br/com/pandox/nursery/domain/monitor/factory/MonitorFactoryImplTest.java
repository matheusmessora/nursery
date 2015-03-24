package br.com.pandox.nursery.domain.monitor.factory;

import br.com.pandox.nursery.domain.monitor.factory.impl.MonitorFactoryImpl;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.entity.MonitorEntity;
import br.com.pandox.nursery.infrastructure.exception.DomainIllegalAttributeException;
import br.com.pandox.nursery.view.monitor.MonitorDTO;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MonitorFactoryImplTest {


    @Test
    public void should_return_unregistered_by_default() {
        MonitorDTO monitorDTO = new MonitorDTO();
        monitorDTO.setName("name");
        monitorDTO.setMachine("localhost");
        Monitor result = new MonitorFactoryImpl().fabric(monitorDTO);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getStatus(), MonitorEntity.Status.UNREGISTERED);
    }

    @Test(expectedExceptions = DomainIllegalAttributeException.class)
    public void should_throw_illegalArgument_when_status_not_recognized() {
        MonitorDTO monitorDTO = new MonitorDTO();
        monitorDTO.setName("name");
        monitorDTO.setMachine("localhost");
        monitorDTO.setStatus("blablabla");
        new MonitorFactoryImpl().fabric(monitorDTO);
    }

}
