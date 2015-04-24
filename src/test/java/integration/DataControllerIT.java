package integration;

import br.com.pandox.nursery.infrastructure.controller.rest.ErroDTO;
import integration.helpers.DataHelper;
import integration.helpers.ITHelper;
import integration.helpers.MetricHelper;
import integration.helpers.MonitorHelper;
import br.com.pandox.nursery.rest.RestUtil;
import br.com.pandox.nursery.view.rest.data.DataDTO;
import br.com.pandox.nursery.view.rest.metric.MetricDTO;
import br.com.pandox.nursery.view.rest.monitor.MonitorDTO;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataControllerIT extends ITHelper {

    private static final Logger LOGGER = LogManager.getLogger();

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

    private MetricDTO createData(DataDTO dataDTO) throws Exception {
        return new DataHelper()
                .withBaseURL(getBaseURL())
                .withDTO(dataDTO)
                .create();
    }

    private MetricDTO createData() throws Exception {
        MonitorDTO monitorDTO = new MonitorDTO("testMonitor", "localhost");
        monitorDTO = createMonitor(monitorDTO);

        MetricDTO metricDTO = new MetricDTO();
        metricDTO.setMonitor(monitorDTO);
        metricDTO.setName("MetricA");
        metricDTO.setTime_interval(1);

        metricDTO = createMetric(metricDTO);

        DataDTO dto = new DataDTO();
        dto.setMetric(metricDTO);
        dto.setValue(1);

        MetricDTO data = createData(dto);
        LOGGER.info(data);
        Assert.assertEquals(data.getId().longValue(), 1L);
        Assert.assertEquals(data.getDatas().get(0).getValue().intValue(), 1);
        Assert.assertNotNull(data.getDatas().get(0).getDate_creation());
        return data;
    }

    @Test
    public void should_create() throws Exception {
        createData();
    }

//    @Test
    public void should_return_badRequest_when_multiple_requests() throws Exception {
        MetricDTO data = createData();
        DataDTO dto = new DataDTO();
        dto.setValue(1);
        dto.setMetric(data);


        HttpResponse httpResponse = Request.Post(getBaseURL() + "data")
                .bodyString(RestUtil.toJson(dto), ContentType.APPLICATION_JSON)
                .execute().returnResponse();
        StatusLine statusLine = httpResponse.getStatusLine();

        int httpExpected = HttpStatus.SC_BAD_REQUEST;
        if (statusLine.getStatusCode() != httpExpected) {
            Assert.fail(String.format("http status must be %s but it was %s", httpExpected, statusLine.getStatusCode()));
        }
        ErroDTO erroDTO = RestUtil.createResponseObject(httpResponse, ErroDTO.class);

        System.out.println("erroDTO = " + erroDTO);
        Assert.assertNotNull(erroDTO);
        Assert.assertNotNull(erroDTO.getError());
    }




}
