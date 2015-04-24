package br.com.pandox.nursery.view.html;

import br.com.pandox.nursery.domain.alert.Alert;
import br.com.pandox.nursery.domain.metric.model.Metric;
import br.com.pandox.nursery.domain.monitor.loader.MonitorLoader;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AlertController {

    @Autowired
    private MonitorLoader monitorLoader;

    @RequestMapping("/alerts")
    public String index(Model model) {
        List<Monitor> monitors = monitorLoader.loadAll(true);

        List<Alert> alerts = new ArrayList<>();
        for (Monitor monitor : monitors) {
            for (Metric metric : monitor.getMetrics()) {
                alerts.addAll(metric.getAlerts());
            }
        }


        model.addAttribute("alerts", alerts);


        return "alerts";
    }
}
