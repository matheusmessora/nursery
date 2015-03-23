package br.com.pandox.nursery.domain.monitor.loader.impl;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MonitorNotFoundExceptionTest {


    @Test
    public void should_generate_correctly_code() {
        try {
            throw new MonitorNotFoundException();
        } catch (MonitorNotFoundException e) {
            Assert.assertEquals(e.getCode(), "monitor.not.found");
        }
    }

}