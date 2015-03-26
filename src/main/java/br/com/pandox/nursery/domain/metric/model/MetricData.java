package br.com.pandox.nursery.domain.metric.model;

import br.com.pandox.nursery.Model;

import java.util.Date;

public interface MetricData extends Model {

    Integer getValue();

    Date getDateCreation();
}
