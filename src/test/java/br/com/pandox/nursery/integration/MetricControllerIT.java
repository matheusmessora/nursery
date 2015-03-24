package br.com.pandox.nursery.integration;

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

public class MetricControllerIT extends ITHelper {

    @Test
    public void should_create() throws Exception {
        MonitorDTO monitorDTO = new MonitorDTO();
        monitorDTO.name = "testMonitor";
        monitorDTO.machine = "localhost";


        HttpResponse httpResponse = Request.Post("http://127.0.0.1:6666/api/vSNAPSHOT/monitor")
                .bodyString(RestUtil.toJson(monitorDTO), ContentType.APPLICATION_JSON)
                .execute().returnResponse();
        StatusLine statusLine = httpResponse.getStatusLine();
        int httpExpected = HttpStatus.SC_CREATED;
        if (statusLine.getStatusCode() != httpExpected) {
            Assert.fail(String.format("http status must be %s but it was %s", httpExpected, statusLine.getStatusCode()));
        }
        monitorDTO = RestUtil.createResponseObject(httpResponse, MonitorDTO.class);

        MetricDTO dto = new MetricDTO();
        dto.setMonitor(monitorDTO);
        dto.setName("MetricA");

        httpResponse = Request.Post("http://127.0.0.1:6666/api/vSNAPSHOT/metric")
                .bodyString(RestUtil.toJson(dto), ContentType.APPLICATION_JSON)
                .execute().returnResponse();
        statusLine = httpResponse.getStatusLine();

        httpExpected = HttpStatus.SC_CREATED;
        if (statusLine.getStatusCode() != httpExpected) {
            Assert.fail(String.format("http status must be %s but it was %s", httpExpected, statusLine.getStatusCode()));
        } else {
            MetricDTO response = RestUtil.createResponseObject(httpResponse, MetricDTO.class);
            Assert.assertEquals(response.getId().longValue(), 1L);
            Assert.assertEquals(response.getName(), "MetricA");
        }
    }

//    @Test
    public void should_get() throws Exception {
        HttpResponse httpResponse = Request.Get("http://127.0.0.1:6666/api/vSNAPSHOT/metric/1")
            .connectTimeout(10000)
            .socketTimeout(10000)
            .execute().returnResponse();

        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);

        MetricDTO response = RestUtil.createResponseObject(httpResponse, MetricDTO.class);

        Assert.assertEquals(response.getId().longValue(), 1L);
        Assert.assertEquals(response.getName(), "MetricA");
    }

}
