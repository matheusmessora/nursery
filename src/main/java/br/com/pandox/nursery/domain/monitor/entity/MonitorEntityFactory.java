package br.com.pandox.nursery.domain.monitor.entity;


import br.com.pandox.nursery.domain.monitor.model.Monitor;

public interface MonitorEntityFactory  {

    MonitorEntity fabric(Monitor monitor);
}
