package br.com.pandox.nursery.domain.monitor.factory;

import br.com.pandox.nursery.domain.alert.Alert;
import br.com.pandox.nursery.domain.metric.factory.MetricFactory;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.metric.model.MetricEntity;
import br.com.pandox.nursery.domain.metric.model.vo.MetricData;
import br.com.pandox.nursery.domain.monitor.factory.impl.MonitorFactoryImpl;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.model.MonitorEntity;
import br.com.pandox.nursery.domain.threshold.model.Threshold;
import br.com.pandox.nursery.view.rest.monitor.MonitorDTO;
import com.google.common.eventbus.EventBus;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

public class MonitorFactoryImplTest {

    @Mock
    private MetricFactory metricFactory;

    @InjectMocks
    private MonitorFactory factory;

    @BeforeTest
    public void init() {
        factory = new MonitorFactoryImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_return_unregistered_by_default() {
        MonitorDTO monitorDTO = new MonitorDTO();
        monitorDTO.setName("name");
        monitorDTO.setMachine("localhost");
        Monitor result = factory.from(monitorDTO);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getStatus(), Monitor.Status.UNREGISTERED);
    }

    @Test
    public void should_build_with_unregistered_status() {
        MonitorDTO monitorDTO = new MonitorDTO();
        monitorDTO.setName("name");
        monitorDTO.setMachine("localhost");
        monitorDTO.setStatus(null);
        Monitor monitor = factory.from(monitorDTO);
        Assert.assertEquals(monitor.getStatus(), Monitor.Status.UNREGISTERED);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void should_throw_illegalArgument_when_status_not_recognized() {
        MonitorDTO monitorDTO = new MonitorDTO();
        monitorDTO.setName("name");
        monitorDTO.setMachine("localhost");
        monitorDTO.setStatus("blablabla");
        factory.from(monitorDTO);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void should_throw_illegalArgument_when_name_null() {
        MonitorDTO monitorDTO = new MonitorDTO();
        monitorDTO.setName("");
        monitorDTO.setMachine("localhost");
        monitorDTO.setStatus(Monitor.Status.UNREGISTERED.name());
        factory.from(monitorDTO);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void should_throw_illegalArgument_when_machine_null() {
        MonitorDTO monitorDTO = new MonitorDTO();
        monitorDTO.setName("name");
        monitorDTO.setMachine("");
        monitorDTO.setStatus(Monitor.Status.UNREGISTERED.name());
        factory.from(monitorDTO);
    }

    @Test
    public void should_build_with_entity() {
        MonitorEntity entity = new MonitorEntity(1L);
        entity.setMachine("machine");
        entity.setName("name");
        entity.setStatus(Monitor.Status.STARTED);

        Monitor monitor = factory.from(entity, false);
        Assert.assertEquals(monitor.getId().longValue(), 1L);
        Assert.assertEquals(monitor.getStatus(), Monitor.Status.STARTED);
    }

    @Test
    public void should_build_with_entity_loaded() {
        MonitorEntity entity = new MonitorEntity(1L);
        entity.setMachine("machine");
        entity.setName("name");
        entity.setStatus(Monitor.Status.STARTED);
        entity.setMetrics(new HashSet<Metric>());

        Monitor monitor = factory.from(entity, true);
        Assert.assertEquals(monitor.getId().longValue(), 1L);
        Assert.assertEquals(monitor.getStatus(), Monitor.Status.STARTED);
    }

    @Test
    public void should_build_with_entity_loaded_with_lists() {
        MonitorEntity entity = new MonitorEntity(1L);
        entity.setMachine("machine");
        entity.setName("name");
        entity.setStatus(Monitor.Status.STARTED);
        Set<Metric> metrics = new HashSet<>();
        metrics.add(new MetricEntity(1L, "metric", "type", 1));
        entity.setMetrics(metrics);

        MetricMock mock = new MetricMock(1L, "metric");
        when(metricFactory.from(Mockito.any(MetricEntity.class), Mockito.anyBoolean())).thenReturn(mock);

        Monitor monitor = factory.from(entity, true);
        Assert.assertEquals(monitor.getId().longValue(), 1L);
        Assert.assertEquals(monitor.getStatus(), Monitor.Status.STARTED);

        Assert.assertEquals(monitor.getMetric(1L).getName(), "metric");

    }

    class MetricMock implements Metric {

        Long id;
        String name;

        public MetricMock(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public Long getId() {
            return id;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getType() {
            return null;
        }

        @Override
        public Integer getTimeInterval() {
            return null;
        }

        @Override
        public Integer getMaxValue() {
            return null;
        }

        @Override
        public Monitor getMonitor() {
            return null;
        }

        @Override
        public void addData(MetricData data, EventBus eventBus) {

        }

        @Override
        public List<MetricData> getDatas() {
            return null;
        }

        @Override
        public boolean isDatasLoaded() {
            return false;
        }


        @Override
        public List<Threshold> getThresholds() {
            return null;
        }

        @Override
        public void addThreshold(Threshold threshold) {

        }

        @Override
        public List<Alert> getAlerts() {
            return null;
        }

    }

}
