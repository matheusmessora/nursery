package br.com.pandox.nursery.domain.monitor.factory;


import br.com.pandox.nursery.domain.monitor.model.MonitorEntity;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.view.rest.monitor.MonitorDTO;

public interface MonitorFactory {

    Monitor createFrom(MonitorEntity entity, boolean loadMetrics);

    Monitor fabric(MonitorDTO monitorDTO);

    MonitorDTO fabric(Monitor model);
}
