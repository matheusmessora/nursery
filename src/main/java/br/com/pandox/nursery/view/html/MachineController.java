package br.com.pandox.nursery.view.html;

import br.com.pandox.nursery.domain.monitor.loader.MonitorLoader;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MachineController {

    @Autowired
    private MonitorLoader monitorLoader;

    @RequestMapping("/machines")
    public String contacts(Model model) {
        List<Monitor> monitors = monitorLoader.loadAll();

        Map<String, List<Monitor>> machines = new HashMap<>();
        for (Monitor monitor : monitors) {
            List<Monitor> monitorList;

            if (machines.containsKey(monitor.getMachine())) {
                monitorList = machines.get(monitor.getMachine());
            } else {
                monitorList = new ArrayList<>();
            }
            monitorList.add(monitor);
            machines.put(monitor.getMachine(), monitorList);
        }

        model.addAttribute("machines", machines);
        return "machines";
    }
}
