package br.com.pandox.nursery.integration;

import br.com.pandox.nursery.domain.monitor.entity.MonitorEntity;
import br.com.pandox.nursery.infrastructure.controller.rest.ErroDTO;
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
        MonitorDTO dto = new MonitorDTO();
        dto.name = "testMonitor";
        dto.machine = "localhost";


        HttpResponse httpResponse = Request.Post("http://127.0.0.1:6666/api/vSNAPSHOT/monitor")
                .connectTimeout(1000)
                .socketTimeout(1000)
                .bodyString(RestUtil.toJson(dto), ContentType.APPLICATION_JSON)
                .execute().returnResponse();
        StatusLine statusLine = httpResponse.getStatusLine();

        int httpExpected = HttpStatus.SC_CREATED;
        if (statusLine.getStatusCode() != httpExpected) {
            Assert.fail(String.format("http status must be %s but it was %s", httpExpected, statusLine.getStatusCode()));
        } else {
            MonitorDTO response = RestUtil.createResponseObject(httpResponse, MonitorDTO.class);
            Assert.assertEquals(response.id.longValue(), 1L);
            Assert.assertEquals(response.name, "testMonitor");
            Assert.assertEquals(response.machine, "localhost");
            Assert.assertEquals(response.status, MonitorEntity.Status.READY.name());
        }
    }

    @Test
    public void should_get() throws Exception {
        HttpResponse httpResponse = Request.Get("http://127.0.0.1:6666/api/vSNAPSHOT/monitor/1")
            .connectTimeout(1000)
            .socketTimeout(1000)
            .execute().returnResponse();

        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);

        MonitorDTO response = RestUtil.createResponseObject(httpResponse, MonitorDTO.class);

        Assert.assertEquals(response.id.longValue(), 1L);
        Assert.assertEquals(response.name, "testMonitor");
        Assert.assertEquals(response.machine, "localhost");
        Assert.assertEquals(response.status, MonitorEntity.Status.READY.name());
    }

    @Test
    public void should_return_notFound() throws Exception {
        HttpResponse httpResponse = Request.Get("http://127.0.0.1:6666/api/vSNAPSHOT/monitor/0")
            .connectTimeout(1000)
            .socketTimeout(1000)
            .execute().returnResponse();

        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND);

        ErroDTO erroDTO = RestUtil.createResponseObject(httpResponse, ErroDTO.class);

        Assert.assertEquals(erroDTO.getError().message, "Monitor não encontrado");
    }


    @Test
    public void should_create_another() throws Exception {
        // Execute a GET with timeout settings and return response content as String.
        MonitorDTO dto = new MonitorDTO();
        dto.name = "testMonitor2";
        dto.machine = "localhost";


        HttpResponse httpResponse = Request.Post("http://127.0.0.1:6666/api/vSNAPSHOT/monitor")
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
            Assert.assertEquals(response.id.longValue(), 2L);
            Assert.assertEquals(response.name, "testMonitor2");
            Assert.assertEquals(response.machine, "localhost");
            Assert.assertEquals(response.status, MonitorEntity.Status.READY.name());
        }
    }

    @Test
    public void should_return_badRequest_when_sent_an_unknow_status() throws Exception {
        // Execute a GET with timeout settings and return response content as String.
        MonitorDTO dto = new MonitorDTO();
        dto.name = "testMonitor2";
        dto.machine = "localhost";
        dto.status = "Blablabla";


        HttpResponse httpResponse = Request.Post("http://127.0.0.1:6666/api/vSNAPSHOT/monitor")
                .connectTimeout(1000)
                .socketTimeout(1000)
                .bodyString(RestUtil.toJson(dto), ContentType.APPLICATION_JSON)
                .execute().returnResponse();
        StatusLine statusLine = httpResponse.getStatusLine();

        int httpExpected = HttpStatus.SC_BAD_REQUEST;
        if (statusLine.getStatusCode() != httpExpected) {
            Assert.fail(String.format("http status must be %s but it was %s", httpExpected, statusLine.getStatusCode()));
        }else {
            ErroDTO erroDTO = RestUtil.createResponseObject(httpResponse, ErroDTO.class);

            Assert.assertEquals(erroDTO.getError().message, "Status informado não reconhecido");
        }
    }

    @Test
    public void should_return_badRequest_when_machine_empty() throws Exception {
        // Execute a GET with timeout settings and return response content as String.
        MonitorDTO dto = new MonitorDTO();
        dto.name = "testMonitor2";
        dto.machine = "";
        dto.status = "Blablabla";


        HttpResponse httpResponse = Request.Post("http://127.0.0.1:6666/api/vSNAPSHOT/monitor")
                .connectTimeout(1000)
                .socketTimeout(1000)
                .bodyString(RestUtil.toJson(dto), ContentType.APPLICATION_JSON)
                .execute().returnResponse();
        StatusLine statusLine = httpResponse.getStatusLine();

        int httpExpected = HttpStatus.SC_BAD_REQUEST;
        if (statusLine.getStatusCode() != httpExpected) {
            Assert.fail(String.format("http status must be %s but it was %s", httpExpected, statusLine.getStatusCode()));
        }else {
            ErroDTO erroDTO = RestUtil.createResponseObject(httpResponse, ErroDTO.class);

            Assert.assertEquals(erroDTO.getError().message, "Missing required attribute: machine");
        }
    }

    @Test
    public void should_return_badRequest_when_name_empty() throws Exception {
        // Execute a GET with timeout settings and return response content as String.
        MonitorDTO dto = new MonitorDTO();
        dto.name = "";
        dto.machine = "localhost";
        dto.status = "Blablabla";


        HttpResponse httpResponse = Request.Post("http://127.0.0.1:6666/api/vSNAPSHOT/monitor")
                .connectTimeout(1000)
                .socketTimeout(1000)
                .bodyString(RestUtil.toJson(dto), ContentType.APPLICATION_JSON)
                .execute().returnResponse();
        StatusLine statusLine = httpResponse.getStatusLine();

        int httpExpected = HttpStatus.SC_BAD_REQUEST;
        if (statusLine.getStatusCode() != httpExpected) {
            Assert.fail(String.format("http status must be %s but it was %s", httpExpected, statusLine.getStatusCode()));
        }else {
            ErroDTO erroDTO = RestUtil.createResponseObject(httpResponse, ErroDTO.class);

            Assert.assertEquals(erroDTO.getError().message, "Missing required attribute: name");
        }
    }

    @Test
    public void should_create_monitor_with_status_READY_even_when_sent_another_status() throws Exception {
        // Execute a GET with timeout settings and return response content as String.
        MonitorDTO dto = new MonitorDTO();
        dto.name = "testMonitor3";
        dto.machine = "localhost";
        dto.setStatus(MonitorEntity.Status.RUNNING.name());


        HttpResponse httpResponse = Request.Post("http://127.0.0.1:6666/api/vSNAPSHOT/monitor")
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
            Assert.assertEquals(response.id.longValue(), 3L);
            Assert.assertEquals(response.name, "testMonitor3");
            Assert.assertEquals(response.machine, "localhost");
            Assert.assertEquals(response.status, MonitorEntity.Status.READY.name());
        }
    }

    @Test
    public void should_get_all() throws Exception {
        HttpResponse httpResponse = Request.Get("http://127.0.0.1:6666/api/vSNAPSHOT/monitor")
                .connectTimeout(1000)
                .socketTimeout(1000)
                .execute().returnResponse();

        List<MonitorDTO> response = RestUtil.createListResponseObject(httpResponse, MonitorDTO.class);

        Assert.assertEquals(response.size(), 3);

        Assert.assertEquals(response.get(0).id.longValue(), 1L);
        Assert.assertEquals(response.get(0).name, "testMonitor");
        Assert.assertEquals(response.get(0).machine, "localhost");
        Assert.assertEquals(response.get(0).status, MonitorEntity.Status.READY.name());


        Assert.assertEquals(response.get(1).id.longValue(), 2L);
        Assert.assertEquals(response.get(1).name, "testMonitor2");
        Assert.assertEquals(response.get(1).machine, "localhost");
        Assert.assertEquals(response.get(1).status, MonitorEntity.Status.READY.name());
    }
}
