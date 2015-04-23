package br.com.pandox.nursery.domain.alert.event.processor;

import br.com.pandox.nursery.domain.alert.Alert;
import br.com.pandox.nursery.domain.alert.event.CreateAlertFromMetricDataEdge;
import br.com.pandox.nursery.domain.alert.factory.AlertFactory;
import br.com.pandox.nursery.domain.alert.service.AlertService;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.metric.model.MetricEntity;
import br.com.pandox.nursery.domain.model.AlertMock;
import com.google.common.eventbus.EventBus;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class CreateAlertFromMetricDataEdgeProcessorTest {

    @Mock
    private AlertFactory alertFactory;

    @Mock
    private EventBus eventBus;

    @InjectMocks
    private CreateAlertFromMetricDataEdgeProcessor processor;

    @BeforeTest
    public void init() {
        processor = new CreateAlertFromMetricDataEdgeProcessor();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_register_to_eventBus() {
        processor.init();
    }

    @Test
    public void should_process() {
        when(alertFactory.from(any(Metric.class)))
                .thenReturn(new AlertMock());


        AlertServiceMock alertService = new AlertServiceMock();
        processor.injectAlertservice(alertService);
        CreateAlertFromMetricDataEdge event = new CreateAlertFromMetricDataEdge(new MetricEntity());
        processor.process(event);

        Assert.assertNotNull(alertService.getCreatedAlert());
    }

    class AlertServiceMock implements AlertService {

        private Alert alert;

        protected Alert getCreatedAlert() {
            return alert;
        }


        @Override
        public Alert create(Alert alert) {
            return this.alert = alert;
        }
    }


}
