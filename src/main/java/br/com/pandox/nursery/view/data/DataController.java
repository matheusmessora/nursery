package br.com.pandox.nursery.view.data;

import br.com.pandox.nursery.domain.metric.command.AddDataToMetricCommand;
import br.com.pandox.nursery.domain.metric.factory.MetricFactory;
import br.com.pandox.nursery.domain.metric.loader.MetricLoader;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.infrastructure.command.executor.CommandExecutor;
import br.com.pandox.nursery.view.exception.DomainMandatoryAttributeException;
import br.com.pandox.nursery.view.metric.MetricDTO;
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
public class DataController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private CommandExecutor executor;

    @Autowired
    private MetricLoader loader;

    @Autowired
    private MetricFactory factory;

    @RequestMapping(value = "/data", method = RequestMethod.POST)
    public ResponseEntity<MetricDTO> save(@RequestBody DataDTO dataDTO) {
        validate(dataDTO);

        executor.execute(new AddDataToMetricCommand(dataDTO.getMetric().getId(), dataDTO));

        Metric metric = loader.loadByID(dataDTO.getMetric().getId(), true);

        MetricDTO dto = factory.fabric(metric);


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
