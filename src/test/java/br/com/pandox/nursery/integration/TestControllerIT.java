package br.com.pandox.nursery.integration;

import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.fluent.Request;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestControllerIT extends ITHelper {


    @Test
    public void okay() throws Exception {
        // Execute a GET with timeout settings and return response content as String.
        StatusLine statusLine = Request.Get("http://127.0.0.1:6666/vSNAPSHOT/test")
                .connectTimeout(1000)
                .socketTimeout(1000)
                .execute().returnResponse().getStatusLine();

        if(statusLine.getStatusCode() != HttpStatus.SC_OK){
            Assert.fail();
        }
    }
}
