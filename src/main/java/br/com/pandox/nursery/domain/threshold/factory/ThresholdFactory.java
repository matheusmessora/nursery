package br.com.pandox.nursery.domain.threshold.factory;

import br.com.pandox.nursery.domain.threshold.model.Threshold;
import br.com.pandox.nursery.view.rest.threshold.ThresholdDTO;

public interface ThresholdFactory {

    Threshold from(ThresholdDTO dto);
}
