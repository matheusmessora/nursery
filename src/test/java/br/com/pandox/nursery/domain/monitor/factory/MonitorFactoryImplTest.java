package br.com.pandox.nursery.domain.monitor.factory;

import br.com.pandox.nursery.domain.monitor.factory.impl.MonitorFactoryImpl;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.repository.entity.MonitorEntity;
import br.com.pandox.nursery.view.monitor.MonitorDTO;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MonitorFactoryImplTest {



    @Test
    public void should_return_UNREGISTERED_by_default() {
        MonitorDTO monitorDTO = new MonitorDTO();
        Monitor result = new MonitorFactoryImpl().fabric(monitorDTO);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getStatus(), MonitorEntity.Status.UNREGISTERED);
    }

    @Test
    public void should_throw_() {
        MonitorDTO monitorDTO = new MonitorDTO();
        Monitor result = new MonitorFactoryImpl().fabric(monitorDTO);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getStatus(), MonitorEntity.Status.UNREGISTERED);
    }

}
