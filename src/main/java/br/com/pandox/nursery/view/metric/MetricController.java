package br.com.pandox.nursery.view.metric;

import br.com.pandox.nursery.domain.metric.factory.MetricFactory;
import br.com.pandox.nursery.domain.metric.loader.MetricLoader;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.monitor.command.executor.SimpleMetricCommandExecutor;
import br.com.pandox.nursery.domain.monitor.command.impl.AddMetricToMonitorCommand;
import br.com.pandox.nursery.infrastructure.controller.rest.ResourceController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MetricController implements ResourceController<MetricDTO> {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private SimpleMetricCommandExecutor executor;

    @Autowired
    private MetricLoader loader;

    @Autowired
    private MetricFactory metricFactory;

    @RequestMapping(value = "/metric")
    public ResponseEntity<MetricDTO> save(@RequestBody MetricDTO metricDTO) {
        executor.execute(new AddMetricToMonitorCommand(metricDTO.getMonitor().getId(), metricDTO));
        Metric metric = loader.loadByName(metricDTO.getName());

        MetricDTO dto = metricFactory.fabric(metric);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/metric/{id}")
    @Override
    public ResponseEntity<MetricDTO> findById(@PathVariable Long id) {
        Metric metric = loader.loadByID(id);
        MetricDTO dto = metricFactory.fabric(metric);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
