package br.com.pandox.nursery.domain.threshold.service;

import br.com.pandox.nursery.domain.threshold.model.Threshold;

public interface ThresholdService {

    Threshold create(Threshold threshold, Long metricId);
}
