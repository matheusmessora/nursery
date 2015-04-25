package br.com.pandox.nursery.domain.threshold.factory;

import br.com.pandox.nursery.domain.threshold.model.Threshold;
import br.com.pandox.nursery.domain.threshold.model.ThresholdBuilder;
import br.com.pandox.nursery.view.rest.threshold.ThresholdDTO;
import org.springframework.stereotype.Component;

@Component
public class ThresholdFactoryImpl implements ThresholdFactory {

    @Override
    public Threshold from(ThresholdDTO dto) {
        return new ThresholdBuilder()
                .type(dto.getType())
                .value(dto.getValue())
                .build();
    }
}
