package integration;

import br.com.pandox.nursery.domain.threshold.model.Threshold;
import br.com.pandox.nursery.view.rest.metric.MetricDTO;
import br.com.pandox.nursery.view.rest.monitor.MonitorDTO;
import br.com.pandox.nursery.view.rest.threshold.ThresholdDTO;
import integration.helpers.ITHelper;
import integration.helpers.MetricHelper;
import integration.helpers.MonitorHelper;
import integration.helpers.ThresholdHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ThresholdControllerIT extends ITHelper {

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

    private ThresholdDTO createThreshold(ThresholdDTO dto) throws Exception {
        return new ThresholdHelper()
                .withBaseURL(getBaseURL())
                .withDTO(dto)
                .create();
    }

    private ThresholdDTO createThreshold() throws Exception {
        MonitorDTO monitorDTO = new MonitorDTO("testMonitor", "localhost");
        monitorDTO = createMonitor(monitorDTO);

        MetricDTO metricDTO = new MetricDTO();
        metricDTO.setMonitor(monitorDTO);
        metricDTO.setName("MetricA");
        metricDTO.setTime_interval(1);

        metricDTO = createMetric(metricDTO);

        ThresholdDTO dto = new ThresholdDTO();
        dto.setValue(1);
        dto.setType(Threshold.ThresholdType.ABOVE.name());
        dto.setMetricDTO(metricDTO);

        ThresholdDTO data = createThreshold(dto);
        LOGGER.info(data);
        Assert.assertEquals(data.getValue().intValue(), 1);
        Assert.assertNotNull(data.getType(), "ABOVE");
        return data;
    }

    @Test
    public void should_create() throws Exception {
        createThreshold();
    }

////    @Test
//    public void should_return_badRequest_when_multiple_requests() throws Exception {
//        MetricDTO data = createThreshold();
//        DataDTO dto = new DataDTO();
//        dto.setValue(1);
//        dto.setMetric(data);
//
//
//        HttpResponse httpResponse = Request.Post(getBaseURL() + "data")
//                .bodyString(RestUtil.toJson(dto), ContentType.APPLICATION_JSON)
//                .execute().returnResponse();
//        StatusLine statusLine = httpResponse.getStatusLine();
//
//        int httpExpected = HttpStatus.SC_BAD_REQUEST;
//        if (statusLine.getStatusCode() != httpExpected) {
//            Assert.fail(String.format("http status must be %s but it was %s", httpExpected, statusLine.getStatusCode()));
//        }
//        ErroDTO erroDTO = RestUtil.createResponseObject(httpResponse, ErroDTO.class);
//
//        System.out.println("erroDTO = " + erroDTO);
//        Assert.assertNotNull(erroDTO);
//        Assert.assertNotNull(erroDTO.getError());
//    }




}
