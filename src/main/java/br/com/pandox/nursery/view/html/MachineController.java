package br.com.pandox.nursery.view.html;

import br.com.pandox.nursery.domain.monitor.loader.MonitorLoader;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class MachineController {

    @Autowired
    private MonitorLoader monitorLoader;

    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }

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
                machines.put(monitor.getMachine(), monitorList);
            }
            monitorList.add(monitor);

        }

        model.addAttribute("machines", machines);
        return "machines";
    }

    @RequestMapping("/machine")
    public String machine(@RequestParam String uid, Model model) {
        Set<Monitor> monitors = monitorLoader.loadByMachine(uid);

        model.addAttribute("monitors", monitors);
        model.addAttribute("machine", uid);
        return "machine";
    }

    @RequestMapping("/monitor/{id}")
    public String machine(Model model, @PathVariable("id") Long id) {
        Monitor monitor = monitorLoader.loadByID(id, true);

        model.addAttribute("monitor", monitor);
        return "monitor";
    }
}
