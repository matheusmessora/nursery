package br.com.pandox.nursery.domain.alert.service;

import br.com.pandox.nursery.domain.alert.Alert;
import br.com.pandox.nursery.domain.alert.model.AlertEntity;
import br.com.pandox.nursery.domain.alert.model.repository.AlertRepository;
import br.com.pandox.nursery.domain.model.AlertMock;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AlertServiceImplTest {

    @Mock
    private AlertRepository alertRepository;

    @InjectMocks
    private AlertService alertService;

    @BeforeTest
    public void init() {
        alertService = new AlertServiceImpl();
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void should_return_createdAlert() {
        AlertMock alertMock = new AlertMock(1L);
        Mockito
                .when(alertRepository.save(Mockito.any(AlertEntity.class)))
                .thenReturn(alertMock);

        Alert alert = alertService.create(new AlertMock());
        Assert.assertEquals(alert.getId().longValue(), 1L);
    }




}
