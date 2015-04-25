package integration.helpers;

import br.com.pandox.nursery.rest.RestUtil;
import br.com.pandox.nursery.view.rest.threshold.ThresholdDTO;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.testng.Assert;

import java.io.IOException;

public class ThresholdHelper {
    private String baseURL;
    private ThresholdDTO dto;

    public ThresholdHelper withBaseURL(String baseURL) {
        this.baseURL = baseURL;
        return this;
    }

    public ThresholdHelper withDTO(ThresholdDTO dto) throws Exception {
        this.dto = dto;
        return this;
    }

    public ThresholdDTO create() throws IOException {
        HttpResponse httpResponse = Request.Post(baseURL + "threshold")
                .bodyString(RestUtil.toJson(dto), ContentType.APPLICATION_JSON)
                .execute().returnResponse();
        StatusLine statusLine = httpResponse.getStatusLine();

        int httpExpected = HttpStatus.SC_CREATED;
        if (statusLine.getStatusCode() != httpExpected) {
            Assert.fail(String.format("http status must be %s but it was %s", httpExpected, statusLine.getStatusCode()));
        }
        return RestUtil.createResponseObject(httpResponse, ThresholdDTO.class);
    }
}
