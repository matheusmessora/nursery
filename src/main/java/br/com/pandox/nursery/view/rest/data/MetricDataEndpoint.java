package br.com.pandox.nursery.view.rest.data;

import br.com.pandox.nursery.domain.metric.factory.MetricFactory;
import br.com.pandox.nursery.domain.metric.loader.MetricLoader;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.metricData.MetricDataService;
import br.com.pandox.nursery.view.exception.DomainMandatoryAttributeException;
import br.com.pandox.nursery.view.rest.metric.MetricDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricDataEndpoint {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private MetricLoader metricLoader;

    @Autowired
    private MetricFactory factory;

    @Autowired
    private MetricDataService metricDataService;


    @RequestMapping(value = "/api/data", method = RequestMethod.POST)
    public ResponseEntity<MetricDTO> save(@RequestBody DataDTO dataDTO) {
        validate(dataDTO);

        metricDataService.create(dataDTO.getValue(), dataDTO.getMetric().getId());

        Metric metric = metricLoader.loadByID(dataDTO.getMetric().getId(), true);

        MetricDTO dto = new MetricDTO(metric);


        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    private void validate(DataDTO dto) {
        if(StringUtils.isEmpty(dto.getValue())) {
            throw new DomainMandatoryAttributeException("value");
        }
        if(dto.getMetric() == null || dto.getMetric().getId() == null) {
            throw new DomainMandatoryAttributeException("metric.id");
        }

    }


}
