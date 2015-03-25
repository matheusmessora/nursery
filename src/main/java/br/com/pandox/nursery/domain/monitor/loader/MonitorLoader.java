package br.com.pandox.nursery.domain.monitor.loader;


import br.com.pandox.nursery.domain.monitor.model.Monitor;
import com.google.common.base.Optional;

import java.util.List;

public interface MonitorLoader {

    Monitor loadByID(Long id, boolean loadMetrics);

    Optional<Monitor> loadByName(String name);

    List<Monitor> loadAll();
}
