package br.com.pandox.nursery.domain.alert.model;

import br.com.pandox.nursery.domain.alert.Alert;
import br.com.pandox.nursery.domain.metric.model.Metric;
import org.springframework.util.Assert;

public class AlertBuilder {

    private Metric metric;

    public AlertBuilder setMetric(Metric metric){
        this.metric = metric;
        return this;
    }

    public Alert build() {
        Assert.isNull(metric, "metric must not be null");

        Alert alert = new AlertEntity(metric);

        return alert;
    }


}
