package br.com.pandox.nursery.view.rest.threshold;

import br.com.pandox.nursery.domain.metric.loader.MetricLoader;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.metric.service.MetricService;
import br.com.pandox.nursery.domain.threshold.model.Threshold;
import br.com.pandox.nursery.domain.threshold.factory.ThresholdFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ThresholdEndpoint {

    @Autowired
    private ThresholdFactory factory;

    @Autowired
    private MetricLoader metricLoader;

    @Autowired
    private MetricService metricService;



    @RequestMapping(value = "/api/threshold", method = RequestMethod.POST)
    public ResponseEntity<ThresholdDTO> save(@RequestBody ThresholdDTO dto) {
//        validate(metricDTO);

        Threshold threshold = factory.from(dto);
        Metric metric = metricLoader.loadByID(dto.getMetricDTO().getId(), false, false);
        metric.addThreshold(threshold);
        metricService.update(metric);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
