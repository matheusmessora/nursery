package br.com.pandox.nursery.integration;

import br.com.pandox.nursery.domain.monitor.view.MonitorDTO;
import br.com.pandox.nursery.rest.RestUtil;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MonitorControllerIT extends ITHelper {

    @Test
    public void should_create() throws Exception {
        // Execute a GET with timeout settings and return response content as String.
        MonitorDTO dto = new MonitorDTO();
        dto.setMachine("localhost");
        dto.setName("testMonitor");

        HttpResponse httpResponse = Request.Post("http://127.0.0.1:6666/vSNAPSHOT/monitor")
                .connectTimeout(1000)
                .socketTimeout(1000)
                .bodyString(RestUtil.toJson(dto), ContentType.APPLICATION_JSON)
                .execute().returnResponse();
        StatusLine statusLine = httpResponse.getStatusLine();

        int httpExpected = HttpStatus.SC_CREATED;
        if (statusLine.getStatusCode() != httpExpected) {
            Assert.fail(String.format("http status must be %s", httpExpected));
        }else {
            MonitorDTO response = RestUtil.createResponseObject(httpResponse, MonitorDTO.class);
            Assert.assertEquals(response.getId().longValue(), 1L);
            Assert.assertEquals(response.getName(), "testMonitor");
            Assert.assertEquals(response.getMachine(), "localhost");
        }
    }

//    @Test
//    public void should_get() throws Exception {
//        // Execute a GET with timeout settings and return response content as String.
//
//        HttpResponse httpResponse = Request.Get("http://127.0.0.1:6666/vSNAPSHOT/monitor/1")
//            .connectTimeout(1000)
//            .socketTimeout(1000)
//            .execute().returnResponse();
//
//        MonitorEntity monitorEntity = RestUtil.createResponseObject(httpResponse, MonitorEntity.class);
//
//        Assert.assertEquals(monitorEntity.getId().longValue(), 1L);
//    }
}
