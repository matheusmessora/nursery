package br.com.pandox.nursery.domain.threshold.service;

import br.com.pandox.nursery.domain.CommandException;
import br.com.pandox.nursery.domain.DomainNotFoundException;
import br.com.pandox.nursery.domain.metric.loader.MetricLoader;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.threshold.model.Threshold;
import br.com.pandox.nursery.domain.threshold.model.ThresholdEntity;
import br.com.pandox.nursery.domain.threshold.model.repository.ThresholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThresholdServiceImpl implements ThresholdService {

    @Autowired
    private MetricLoader metricLoader;

    @Autowired
    private ThresholdRepository repository;

    @Override
    public Threshold create(Threshold threshold, Long metricId) {
        Metric metric = load(metricId);
        metric.addThreshold(threshold);

        return repository.save((ThresholdEntity) threshold);
    }

    private Metric load(Long metricId){
        try {
            return metricLoader.loadByID(metricId, false, false);
        }catch (DomainNotFoundException e) {
            throw new CommandException(String.format("Given metric with id [%s] not found", metricId));
        }
    }
}
