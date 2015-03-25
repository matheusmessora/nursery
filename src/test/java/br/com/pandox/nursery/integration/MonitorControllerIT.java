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

    private MonitorDTO createMonitor(MonitorDTO monitorDTO) throws Exception {
        return new MonitorHelper()
            .withBaseURL(getBaseURL())
            .withMonitor(monitorDTO)
            .create();
    }

    @Test
    public void should_create() throws Exception {
        MonitorDTO result = createMonitor(new MonitorDTO("testMonitor", "localhost"));
        Assert.assertEquals(result.id.longValue(), 1L);
        Assert.assertEquals(result.name, "testMonitor");
        Assert.assertEquals(result.machine, "localhost");
        Assert.assertEquals(result.status, MonitorEntity.Status.READY.name());
    }

    @Test
    public void should_get() throws Exception {
        createMonitor(new MonitorDTO("testMonitor", "localhost"));
        HttpResponse httpResponse = Request.Get(getBaseURL() + "monitor/1")
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
        HttpResponse httpResponse = Request.Get(getBaseURL() + "monitor/0")
            .connectTimeout(1000)
            .socketTimeout(1000)
            .execute().returnResponse();

        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND);

        ErroDTO erroDTO = RestUtil.createResponseObject(httpResponse, ErroDTO.class);

        Assert.assertEquals(erroDTO.getError().message, "Monitor não encontrado");
    }


    @Test
    public void should_create_another() throws Exception {
        createMonitor(new MonitorDTO("testMonitor1", "localhost"));

        MonitorDTO result = createMonitor(new MonitorDTO("testMonitor2", "localhost"));

        Assert.assertEquals(result.id.longValue(), 2L);
        Assert.assertEquals(result.name, "testMonitor2");
        Assert.assertEquals(result.machine, "localhost");
        Assert.assertEquals(result.status, MonitorEntity.Status.READY.name());
    }

    @Test
    public void should_return_badRequest_when_sent_an_unknow_status() throws Exception {
        MonitorDTO dto = new MonitorDTO("testMonitor", "localhost", "blablabla");

        HttpResponse httpResponse = Request.Post(getBaseURL() + "monitor")
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
        MonitorDTO dto = new MonitorDTO("testMonitor", "", "blablabla");

        HttpResponse httpResponse = Request.Post(getBaseURL() + "monitor")
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
        MonitorDTO dto = new MonitorDTO("", "localhost", "blablabla");

        HttpResponse httpResponse = Request.Post(getBaseURL() + "monitor")
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
        MonitorDTO response = new MonitorDTO("testMonitor3", "localhost", MonitorEntity.Status.RUNNING.name());

        response = createMonitor(response);
        Assert.assertEquals(response.id.longValue(), 1L);
        Assert.assertEquals(response.name, "testMonitor3");
        Assert.assertEquals(response.machine, "localhost");
        Assert.assertEquals(response.status, MonitorEntity.Status.READY.name());
    }

    @Test
    public void should_get_all() throws Exception {
        createMonitor(new MonitorDTO("testMonitor", "localhost"));
        createMonitor(new MonitorDTO("testMonitor2", "localhost"));
        createMonitor(new MonitorDTO("testMonitor3", "localhost"));

        HttpResponse httpResponse = Request.Get(getBaseURL() + "monitor")
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
