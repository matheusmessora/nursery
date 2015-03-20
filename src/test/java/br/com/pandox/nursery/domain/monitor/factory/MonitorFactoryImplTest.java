package br.com.pandox.nursery.domain.monitor.factory;

import br.com.pandox.nursery.view.monitor.MonitorDTO;
import org.testng.annotations.Test;

public class MonitorFactoryImplTest {



    @Test
    public void should_throw_IAG_when_monitorStatus_null() {
        MonitorFactory factory = new MonitorFactoryImpl();
        MonitorDTO monitorDTO = new MonitorDTO();
        factory.fabric(monitorDTO);
    }

}