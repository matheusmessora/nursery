package br.com.pandox.nursery.domain.monitor.service;

import br.com.pandox.nursery.domain.monitor.model.Monitor;

import java.util.List;
import java.util.Set;

public interface MonitorService {

    Monitor findByID(Long id);

    List<Monitor> loadAll();
}
