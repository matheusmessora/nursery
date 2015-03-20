package br.com.pandox.nursery.application.monitor;


import br.com.pandox.nursery.domain.monitor.model.Monitor;

import java.util.List;

public interface MonitorLoader {

    Monitor loadByID(Long id);

    List<Monitor> loadAll();
}
