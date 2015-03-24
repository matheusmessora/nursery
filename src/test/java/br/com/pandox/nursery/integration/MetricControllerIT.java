package br.com.pandox.nursery.integration;

import br.com.pandox.nursery.rest.RestUtil;
import br.com.pandox.nursery.view.metric.MetricDTO;
import br.com.pandox.nursery.view.monitor.MonitorDTO;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.fluent.Request;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MetricControllerIT extends ITHelper {

    private MonitorDTO createMonitor(MonitorDTO monitorDTO) throws Exception {
        return new MonitorHelper()
            .withBaseURL(getBaseURL())
            .withMonitor(monitorDTO)
            .create();
    }

    private MetricDTO createMetric(MetricDTO metricDTO) throws Exception {
        return new MetricHelper()
            .withBaseURL(getBaseURL())
            .withDTO(metricDTO)
            .create();
    }

    @Test
    public void should_create() throws Exception {
        MonitorDTO monitorDTO = new MonitorDTO("testMonitor", "localhost");
        monitorDTO = createMonitor(monitorDTO);

        MetricDTO dto = new MetricDTO();
        dto.setMonitor(monitorDTO);
        dto.setName("MetricA");


        dto = createMetric(dto);
        Assert.assertEquals(dto.getId().longValue(), 1L);
        Assert.assertEquals(dto.getName(), "MetricA");
    }

    @Test
    public void should_get() throws Exception {
        MonitorDTO monitorDTO = new MonitorDTO("testMonitor", "localhost");
        monitorDTO = createMonitor(monitorDTO);

        MetricDTO dto = new MetricDTO();
        dto.setMonitor(monitorDTO);
        dto.setName("MetricA");
        createMetric(dto);

        HttpResponse httpResponse = Request.Get(getBaseURL() + "metric/1")
            .connectTimeout(10000)
            .socketTimeout(10000)
            .execute().returnResponse();

        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);

        MetricDTO response = RestUtil.createResponseObject(httpResponse, MetricDTO.class);

        Assert.assertEquals(response.getId().longValue(), 1L);
        Assert.assertEquals(response.getName(), "MetricA");
    }

}
