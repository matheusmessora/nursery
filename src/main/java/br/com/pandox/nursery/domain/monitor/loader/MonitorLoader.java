package br.com.pandox.nursery.domain.monitor.loader;


import br.com.pandox.nursery.domain.monitor.model.Monitor;
import com.google.common.base.Optional;

import java.util.List;

public interface MonitorLoader {

    Monitor loadByID(Long id, boolean loadMetrics);

    Optional<Monitor> loadByName(String name);

    Optional<Monitor> loadByMachineAndName(String name, String machine);

    List<Monitor> loadByMachine(String machine);

    List<Monitor> loadAll(boolean loadMetrics);
}
