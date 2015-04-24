package integration.helpers;

import br.com.pandox.nursery.rest.RestUtil;
import br.com.pandox.nursery.view.rest.metric.MetricDTO;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.testng.Assert;

import java.io.IOException;

public class MetricHelper {

    private String baseURL;
    private MetricDTO dto;

    public MetricHelper withBaseURL(String baseURL) {
        this.baseURL = baseURL;
        return this;
    }

    public MetricHelper withDTO(MetricDTO dto) throws Exception {
        this.dto = dto;
        return this;
    }

    public MetricDTO create() throws IOException {
        HttpResponse httpResponse = Request.Post(baseURL + "metric")
            .bodyString(RestUtil.toJson(dto), ContentType.APPLICATION_JSON)
            .execute().returnResponse();
        StatusLine statusLine = httpResponse.getStatusLine();

        int httpExpected = HttpStatus.SC_CREATED;
        if (statusLine.getStatusCode() != httpExpected) {
            Assert.fail(String.format("http status must be %s but it was %s", httpExpected, statusLine.getStatusCode()));
        }
        return RestUtil.createResponseObject(httpResponse, MetricDTO.class);
    }
}
