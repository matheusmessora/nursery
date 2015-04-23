package br.com.pandox.nursery.domain.model;

import br.com.pandox.nursery.domain.alert.Alert;
import br.com.pandox.nursery.domain.alert.model.AlertEntity;
import br.com.pandox.nursery.domain.metric.model.Metric;

import java.util.Date;

public class AlertMock extends AlertEntity implements Alert {

    private Long id;

    public AlertMock(Long id) {
        this.id = id;
    }

    public AlertMock(){}

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public Metric alertedBy() {
        return null;
    }

    @Override
    public Date getDate() {
        return null;
    }
}
