package br.com.pandox.nursery.integration;

import br.com.pandox.nursery.entity.Monitor;
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
        Monitor monitor = new Monitor();
        monitor.setMachine("localhost");
        monitor.setName("testMonitor");

        StatusLine statusLine = Request.Post("http://127.0.0.1:6666/vSNAPSHOT/monitor")
                .connectTimeout(1000)
                .socketTimeout(1000)
                .bodyString(RestUtil.toJson(monitor), ContentType.APPLICATION_JSON)
                .execute().returnResponse().getStatusLine();

        if(statusLine.getStatusCode() != HttpStatus.SC_CREATED){
            Assert.fail();
        }
    }

    @Test
    public void should_get() throws Exception {
        // Execute a GET with timeout settings and return response content as String.

        HttpResponse httpResponse = Request.Get("http://127.0.0.1:6666/vSNAPSHOT/monitor")
            .connectTimeout(1000)
            .socketTimeout(1000)
            .execute().returnResponse();

        if(statusLine.getStatusCode() != HttpStatus.SC_OK){
            Assert.fail();
        }
    }
}
