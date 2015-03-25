package br.com.pandox.nursery.domain.monitor.loader.impl;

import br.com.pandox.nursery.domain.DomainNotFoundException;
import br.com.pandox.nursery.domain.monitor.entity.repository.MonitorRepository;
import br.com.pandox.nursery.domain.monitor.loader.MonitorLoader;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MonitorLoaderImplTest {

    @Mock
    private MonitorRepository repository;

    @InjectMocks
    private MonitorLoader loader;

    @BeforeTest
    public void init(){
        loader = new MonitorLoaderImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "MonitorID must not be null")
    public void exception_when_id_null() {
        loader.loadByID(null, false);
    }

    @Test(expectedExceptions = DomainNotFoundException.class)
    public void exception_when_not_found() {
        Mockito.when(repository.findOne(Mockito.anyLong())).thenReturn(null);
        loader.loadByID(1L, false);
    }

}
