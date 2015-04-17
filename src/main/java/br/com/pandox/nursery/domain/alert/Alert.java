package br.com.pandox.nursery.domain.alert;

import br.com.pandox.nursery.domain.metric.model.Metric;

import java.util.Date;

public interface Alert {

    Long getId();

    Metric alertedBy();

    Date getDate();
}
