package br.com.pandox.nursery.view.metric;

import br.com.pandox.nursery.domain.metric.factory.MetricFactory;
import br.com.pandox.nursery.domain.metric.loader.MetricLoader;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.monitor.command.AddMetricToMonitorCommand;
import br.com.pandox.nursery.infrastructure.command.executor.CommandExecutor;
import br.com.pandox.nursery.view.exception.DomainIllegalAttributeException;
import br.com.pandox.nursery.view.exception.DomainMandatoryAttributeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MetricController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private CommandExecutor executor;

    @Autowired
    private MetricLoader loader;

    @Autowired
    private MetricFactory metricFactory;

    @RequestMapping(value = "/metric", method = RequestMethod.POST)
    public ResponseEntity<MetricDTO> save(@RequestBody MetricDTO metricDTO) {
        validate(metricDTO);

        executor.execute(new AddMetricToMonitorCommand(metricDTO.getMonitor().getId(), metricDTO));
        Metric metric = loader.loadByName(metricDTO.getName());

        MetricDTO dto = metricFactory.fabric(metric);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/metric", method = RequestMethod.GET)
    public ResponseEntity<List<MetricDTO>> findByMonitorID(@RequestParam(value = "monitor_id") Long monitorId) {
        List<Metric> metrics = loader.loadByMonitorID(monitorId);

        List<MetricDTO> result = new ArrayList<>();
        for (Metric metric : metrics) {
            MetricDTO dto = metricFactory.fabric(metric);
            result.add(dto);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/metric/{id}")
    public ResponseEntity<MetricDTO> findById(@PathVariable Long id, @RequestParam(required = false, value = "load") boolean loadData) {
        Metric metric = loader.loadByID(id, loadData);
        MetricDTO dto = metricFactory.fabric(metric);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    private void validate(MetricDTO dto) {
        if(StringUtils.isEmpty(dto.getName())) {
            throw new DomainMandatoryAttributeException("name");
        }
        if(dto.getMonitor() == null || dto.getMonitor().getId() == null) {
            throw new DomainMandatoryAttributeException("monitor.id");
        }
        Integer timeInterval = dto.getTime_interval();
        if(timeInterval == null || timeInterval < 1 || timeInterval > 1440) {
            throw new DomainIllegalAttributeException("metric", "time_interval");
        }

    }
}
