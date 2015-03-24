package br.com.pandox.nursery.view.metric;

import br.com.pandox.nursery.domain.metric.factory.MetricFactory;
import br.com.pandox.nursery.domain.monitor.command.executor.MonitorCommandExecutor;
import br.com.pandox.nursery.domain.monitor.command.impl.AddMetricToMonitorCommand;
import br.com.pandox.nursery.domain.monitor.factory.MonitorFactory;
import br.com.pandox.nursery.domain.monitor.loader.MonitorLoader;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.infrastructure.controller.rest.ResourceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MetricController implements ResourceController<MetricDTO> {

	@Autowired
	private MonitorCommandExecutor executor;

	@Autowired private MonitorLoader monitorLoader;

	@Autowired
	private MonitorFactory factory;

	@Autowired
	private MetricFactory metricFactory;

	@RequestMapping(value = "/metric")
	public ResponseEntity<MetricDTO> save(@RequestBody MetricDTO metricDTO) {
		Monitor monitor = monitorLoader.loadByID(metricDTO.getMonitor().getId());
		monitor = executor.execute(new AddMetricToMonitorCommand(monitor, metricDTO));

		MetricDTO dto = metricFactory.fabric(monitor.getMetrics().get(monitor.getMetrics().size()-1));
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}

	@Override public ResponseEntity<MetricDTO> findById(@PathVariable Long id) {
		return null;
	}
}
