package br.com.pandox.nursery.integration;


import br.com.pandox.nursery.boot.NurseryServer;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class ITHelper {

    private String baseURL;

    NurseryServer server = new NurseryServer();

    @BeforeMethod(alwaysRun = true)
    public void init() throws Exception {
        server = new NurseryServer();
        server.start();
        baseURL = "http://127.0.0.1:" + server.getJettyPort() + "/api/vSNAPSHOT/";
    }

    @AfterMethod(alwaysRun = true)
    public void destroy() throws Exception {
        server.stop();
        server = null;
    }

    public String getBaseURL() {
        return baseURL;
    }
}
