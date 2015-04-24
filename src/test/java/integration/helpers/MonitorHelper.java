package integration.helpers;

import br.com.pandox.nursery.rest.RestUtil;
import br.com.pandox.nursery.view.rest.monitor.MonitorDTO;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.testng.Assert;

import java.io.IOException;

public class MonitorHelper {

    private String baseURL;
    private MonitorDTO monitorDTO;

    public MonitorHelper withBaseURL(String baseURL) {
        this.baseURL = baseURL;
        return this;
    }

    public MonitorHelper withMonitor(MonitorDTO monitorDTO) throws Exception {
        this.monitorDTO = monitorDTO;
        return this;
    }

    public MonitorDTO create() throws IOException {
        HttpResponse httpResponse = Request.Post(baseURL + "monitor")
            .bodyString(RestUtil.toJson(monitorDTO), ContentType.APPLICATION_JSON)
            .execute().returnResponse();
        StatusLine statusLine = httpResponse.getStatusLine();

        int httpExpected = HttpStatus.SC_CREATED;
        if (statusLine.getStatusCode() != httpExpected) {
            Assert.fail(String.format("http status must be %s but it was %s", httpExpected, statusLine.getStatusCode()));
        }

        MonitorDTO responseObject = RestUtil.createResponseObject(httpResponse, MonitorDTO.class);
        Assert.assertNotNull(responseObject.getId());
        return responseObject;
    }
}
