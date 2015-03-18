package br.com.pandox.nursery.integration;


import br.com.pandox.nursery.boot.NurseryServer;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class ITHelper {

    @BeforeMethod
    public void init() throws Exception {
        NurseryServer.start(6666);
    }

    @AfterMethod
    public void destroy() throws Exception {
        NurseryServer.stop();
    }
}
