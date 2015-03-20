package br.com.pandox.nursery.domain.monitor.factory;


import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.view.monitor.MonitorDTO;

public interface MonitorFactory {

    Monitor fabric(MonitorDTO monitorDTO);

    MonitorDTO fabric(Monitor model);
}
