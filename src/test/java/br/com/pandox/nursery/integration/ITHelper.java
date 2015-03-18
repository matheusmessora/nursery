package br.com.pandox.nursery.integration;


import br.com.pandox.nursery.boot.NurseryServer;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class ITHelper {

    @BeforeClass
    public void init() throws Exception {
        NurseryServer.start(6666);
    }

    @AfterClass
    public void destroy() throws Exception {
        NurseryServer.stop();
    }
}
