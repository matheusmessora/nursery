package br.com.pandox.nursery.integration;

import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.domain.monitor.repository.entity.MonitorBuilder;
import br.com.pandox.nursery.domain.monitor.repository.entity.MonitorEntity;
import br.com.pandox.nursery.rest.RestUtil;
import br.com.pandox.nursery.view.monitor.MonitorDTO;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class MonitorControllerIT extends ITHelper {

    @Test
    public void should_create() throws Exception {
        // Execute a GET with timeout settings and return response content as String.
        Monitor dto = new MonitorBuilder().setMachine("localhost").setName("testMonitor").build();

        HttpResponse httpResponse = Request.Post("http://127.0.0.1:6666/vSNAPSHOT/monitor")
                .connectTimeout(1000)
                .socketTimeout(1000)
                .bodyString(RestUtil.toJson(dto), ContentType.APPLICATION_JSON)
                .execute().returnResponse();
        StatusLine statusLine = httpResponse.getStatusLine();

        int httpExpected = HttpStatus.SC_CREATED;
        if (statusLine.getStatusCode() != httpExpected) {
            Assert.fail(String.format("http status must be %s but it was %s", httpExpected, statusLine.getStatusCode()));
        }else {
            MonitorDTO response = RestUtil.createResponseObject(httpResponse, MonitorDTO.class);
            Assert.assertEquals(response.getId().longValue(), 1L);
            Assert.assertEquals(response.getName(), "testMonitor");
            Assert.assertEquals(response.getMachine(), "localhost");
            Assert.assertEquals(response.getStatus(), MonitorEntity.Status.READY.name());
        }
    }

    @Test
    public void should_get() throws Exception {
        HttpResponse httpResponse = Request.Get("http://127.0.0.1:6666/vSNAPSHOT/monitor/1")
            .connectTimeout(1000)
            .socketTimeout(1000)
            .execute().returnResponse();

        MonitorDTO response = RestUtil.createResponseObject(httpResponse, MonitorDTO.class);

        Assert.assertEquals(response.getId().longValue(), 1L);
        Assert.assertEquals(response.getName(), "testMonitor");
        Assert.assertEquals(response.getMachine(), "localhost");
    }


    @Test
    public void should_create_another() throws Exception {
        // Execute a GET with timeout settings and return response content as String.
        Monitor dto = new MonitorBuilder().setMachine("localhost").setName("testMonitor2").build();


        HttpResponse httpResponse = Request.Post("http://127.0.0.1:6666/vSNAPSHOT/monitor")
            .connectTimeout(1000)
            .socketTimeout(1000)
            .bodyString(RestUtil.toJson(dto), ContentType.APPLICATION_JSON)
            .execute().returnResponse();
        StatusLine statusLine = httpResponse.getStatusLine();

        int httpExpected = HttpStatus.SC_CREATED;
        if (statusLine.getStatusCode() != httpExpected) {
            Assert.fail(String.format("http status must be %s but it was %s", httpExpected, statusLine.getStatusCode()));
        }else {
            MonitorDTO response = RestUtil.createResponseObject(httpResponse, MonitorDTO.class);
            Assert.assertEquals(response.getId().longValue(), 2L);
            Assert.assertEquals(response.getName(), "testMonitor2");
            Assert.assertEquals(response.getMachine(), "localhost");
        }
    }

    @Test
    public void should_get_all() throws Exception {
        HttpResponse httpResponse = Request.Get("http://127.0.0.1:6666/vSNAPSHOT/monitor")
            .connectTimeout(1000)
            .socketTimeout(1000)
            .execute().returnResponse();

        List<MonitorDTO> response = RestUtil.createListResponseObject(httpResponse, MonitorDTO.class);

        Assert.assertEquals(response.size(), 2);

        Assert.assertEquals(response.get(0).getId().longValue(), 1L);
        Assert.assertEquals(response.get(0).getName(), "testMonitor");
        Assert.assertEquals(response.get(0).getMachine(), "localhost");

        Assert.assertEquals(response.get(1).getId().longValue(), 2L);
        Assert.assertEquals(response.get(1).getName(), "testMonitor2");
        Assert.assertEquals(response.get(1).getMachine(), "localhost");
    }
}
