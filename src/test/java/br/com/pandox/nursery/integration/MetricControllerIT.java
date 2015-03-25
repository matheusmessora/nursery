package br.com.pandox.nursery.integration;

import br.com.pandox.nursery.infrastructure.controller.rest.ErroDTO;
import br.com.pandox.nursery.integration.helpers.ITHelper;
import br.com.pandox.nursery.integration.helpers.MetricHelper;
import br.com.pandox.nursery.integration.helpers.MonitorHelper;
import br.com.pandox.nursery.rest.RestUtil;
import br.com.pandox.nursery.view.metric.MetricDTO;
import br.com.pandox.nursery.view.monitor.MonitorDTO;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

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
        dto.setTimeInterval(1);


        dto = createMetric(dto);
        Assert.assertEquals(dto.getId().longValue(), 1L);
        Assert.assertEquals(dto.getName(), "MetricA");
    }

    @Test
    public void should_throw_badRequest_when_timeInterval_incorrect() throws Exception {
        MonitorDTO monitorDTO = new MonitorDTO("testMonitor", "localhost");
        monitorDTO = createMonitor(monitorDTO);

        MetricDTO dto = new MetricDTO();
        dto.setMonitor(monitorDTO);
        dto.setName("MetricA");
        dto.setTimeInterval(0);

        HttpResponse httpResponse = Request.Post(getBaseURL() + "metric")
            .connectTimeout(1000)
            .socketTimeout(1000)
            .bodyString(RestUtil.toJson(dto), ContentType.APPLICATION_JSON)
            .execute().returnResponse();
        StatusLine statusLine = httpResponse.getStatusLine();

        int httpExpected = HttpStatus.SC_BAD_REQUEST;
        if (statusLine.getStatusCode() != httpExpected) {
            Assert.fail(String.format("http status must be %s but it was %s", httpExpected, statusLine.getStatusCode()));
        } else {
            ErroDTO erroDTO = RestUtil.createResponseObject(httpResponse, ErroDTO.class);

            Assert.assertEquals(erroDTO.getError().message, "Malformed attribute: time_interval. It must be between 1 and 1440");
        }
    }

    @Test
    public void should_throw_badRequest_when_name_incorrect() throws Exception {
        MonitorDTO monitorDTO = new MonitorDTO("testMonitor", "localhost");
        monitorDTO = createMonitor(monitorDTO);

        MetricDTO dto = new MetricDTO();
        dto.setMonitor(monitorDTO);
        dto.setName("");
        dto.setTimeInterval(0);

        HttpResponse httpResponse = Request.Post(getBaseURL() + "metric")
            .connectTimeout(1000)
            .socketTimeout(1000)
            .bodyString(RestUtil.toJson(dto), ContentType.APPLICATION_JSON)
            .execute().returnResponse();
        StatusLine statusLine = httpResponse.getStatusLine();

        int httpExpected = HttpStatus.SC_BAD_REQUEST;
        if (statusLine.getStatusCode() != httpExpected) {
            Assert.fail(String.format("http status must be %s but it was %s", httpExpected, statusLine.getStatusCode()));
        } else {
            ErroDTO erroDTO = RestUtil.createResponseObject(httpResponse, ErroDTO.class);

            Assert.assertEquals(erroDTO.getError().message, "Missing required attribute: name");
        }
    }

    @Test
    public void should_throw_badRequest_on_create_when_monitor_null() throws Exception {
        MetricDTO dto = new MetricDTO();
        dto.setMonitor(null);
        dto.setName("MetricA");
        dto.setTimeInterval(0);

        HttpResponse httpResponse = Request.Post(getBaseURL() + "metric")
            .connectTimeout(1000)
            .socketTimeout(1000)
            .bodyString(RestUtil.toJson(dto), ContentType.APPLICATION_JSON)
            .execute().returnResponse();
        StatusLine statusLine = httpResponse.getStatusLine();

        int httpExpected = HttpStatus.SC_BAD_REQUEST;
        if (statusLine.getStatusCode() != httpExpected) {
            Assert.fail(String.format("http status must be %s but it was %s", httpExpected, statusLine.getStatusCode()));
        } else {
            ErroDTO erroDTO = RestUtil.createResponseObject(httpResponse, ErroDTO.class);

            Assert.assertEquals(erroDTO.getError().message, "Missing required attribute: monitor.id");
        }
    }

    @Test
    public void should_throw_badRequest_on_create_when_monitor_id_null() throws Exception {
        MetricDTO dto = new MetricDTO();
        dto.setMonitor(new MonitorDTO());
        dto.setName("MetricA");
        dto.setTimeInterval(0);

        HttpResponse httpResponse = Request.Post(getBaseURL() + "metric")
            .connectTimeout(1000)
            .socketTimeout(1000)
            .bodyString(RestUtil.toJson(dto), ContentType.APPLICATION_JSON)
            .execute().returnResponse();
        StatusLine statusLine = httpResponse.getStatusLine();

        int httpExpected = HttpStatus.SC_BAD_REQUEST;
        if (statusLine.getStatusCode() != httpExpected) {
            Assert.fail(String.format("http status must be %s but it was %s", httpExpected, statusLine.getStatusCode()));
        } else {
            ErroDTO erroDTO = RestUtil.createResponseObject(httpResponse, ErroDTO.class);

            Assert.assertEquals(erroDTO.getError().message, "Missing required attribute: monitor.id");
        }
    }

    @Test
    public void should_throw_badRequest_on_create_when_monitor_not_found() throws Exception {
        MonitorDTO monitorDTO = new MonitorDTO("testMonitor", "localhost");
        monitorDTO.setId(0L);

        MetricDTO dto = new MetricDTO();
        dto.setMonitor(monitorDTO);
        dto.setName("MetricA");
        dto.setTimeInterval(1);

        HttpResponse httpResponse = Request.Post(getBaseURL() + "metric")
            .connectTimeout(1000)
            .socketTimeout(1000)
            .bodyString(RestUtil.toJson(dto), ContentType.APPLICATION_JSON)
            .execute().returnResponse();
        StatusLine statusLine = httpResponse.getStatusLine();

        int httpExpected = HttpStatus.SC_BAD_REQUEST;
        if (statusLine.getStatusCode() != httpExpected) {
            Assert.fail(String.format("http status must be %s but it was %s", httpExpected, statusLine.getStatusCode()));
        } else {
            ErroDTO erroDTO = RestUtil.createResponseObject(httpResponse, ErroDTO.class);

            Assert.assertEquals(erroDTO.getError().message, "Given monitor with id [0] not found");
        }
    }

    @Test
    public void should_get_by_id() throws Exception {
        MonitorDTO monitorDTO = new MonitorDTO("testMonitor", "localhost");
        monitorDTO = createMonitor(monitorDTO);

        MetricDTO dto = new MetricDTO();
        dto.setMonitor(monitorDTO);
        dto.setName("MetricA");
        dto.setTimeInterval(1);
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

    @Test
    public void should_get_metric_from_monitor() throws Exception {
        MonitorDTO monitorDTO = new MonitorDTO("testMonitor", "localhost");
        monitorDTO = createMonitor(monitorDTO);

        MetricDTO dto = new MetricDTO();
        dto.setMonitor(monitorDTO);
        dto.setName("MetricA");
        dto.setTimeInterval(1);
        createMetric(dto);

        HttpResponse httpResponse = Request.Get(getBaseURL() + "metric?monitor_id=1")
            .connectTimeout(10000)
            .socketTimeout(10000)
            .execute().returnResponse();

        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);

        List<MetricDTO> response = RestUtil.createListResponseObject(httpResponse, MetricDTO.class);

        Assert.assertEquals(response.get(0).getId().longValue(), 1L);
        Assert.assertEquals(response.get(0).getName(), "MetricA");
    }

}
