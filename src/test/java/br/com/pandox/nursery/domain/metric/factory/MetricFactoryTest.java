package br.com.pandox.nursery.domain.metric.factory;

import br.com.pandox.nursery.domain.metric.factory.impl.MetricFactoryImpl;
import br.com.pandox.nursery.view.metric.MetricDTO;
import org.testng.annotations.Test;

public class MetricFactoryTest {


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void should_throw_illegalArgument_when_name_empty() {
        MetricDTO metricDTO = new MetricDTO();
        metricDTO.setName("");
        new MetricFactoryImpl().fabric(metricDTO);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void should_throw_illegalArgument_when_timeInterval_empty() {
        MetricDTO metricDTO = new MetricDTO();
        metricDTO.setName("name");
        metricDTO.setTimeInterval(null);
        new MetricFactoryImpl().fabric(metricDTO);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void should_throw_illegalArgument_when_timeInterval_lower_1() {
        MetricDTO metricDTO = new MetricDTO();
        metricDTO.setName("name");
        metricDTO.setTimeInterval(0);
        new MetricFactoryImpl().fabric(metricDTO);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void should_throw_illegalArgument_when_timeInterval_greather_than_oneDay() {
        MetricDTO metricDTO = new MetricDTO();
        metricDTO.setName("name");
        metricDTO.setTimeInterval(1441);
        new MetricFactoryImpl().fabric(metricDTO);
    }



}
