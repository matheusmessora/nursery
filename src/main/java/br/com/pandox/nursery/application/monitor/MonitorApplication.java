package br.com.pandox.nursery.application.monitor;


import br.com.pandox.nursery.view.monitor.MonitorDTO;

import java.util.List;
import java.util.Set;

public interface MonitorApplication {

    MonitorDTO save(MonitorDTO monitorDTO);

    MonitorDTO findByID(Long id);

    List<MonitorDTO> findAll();

}
