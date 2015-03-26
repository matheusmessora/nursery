package br.com.pandox.nursery.domain.metric.model;

import br.com.pandox.nursery.domain.metric.model.vo.MetricData;
import br.com.pandox.nursery.domain.metric.service.MetricService;
import br.com.pandox.nursery.domain.monitor.model.Monitor;

import java.util.List;

public class MetricImpl implements Metric {

    private Long id;
    private String name;
    private String type;
    private Integer timeInterval;
    private Monitor monitor;
    private List<MetricData> datas;

    private boolean datasLoaded;

    protected MetricImpl(Long id, String name, String type, Integer timeInterval, List<MetricData> datas) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.timeInterval = timeInterval;
        this.datas = datas;
        if(datas != null){
            datasLoaded = true;
        }

    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Integer getTimeInterval() {
        return timeInterval;
    }

    @Override
    public Monitor getMonitor() {
        return monitor;
    }

    @Override
    public void addData(MetricData data, MetricService service) {
//        if(status.equals(Monitor.Status.UNREGISTERED) || status.equals(Monitor.Status.STOPPED)){
//            throw new CommandException("Can not add metric to Monitor in %s status", status.name());
//        }
        this.datas.add(data);

        service.save(this);
    }

    @Override
    public List<MetricData> getDatas() {
        if(!datasLoaded){
            throw new IllegalStateException("You should load Metric with Datas to be able to get them.");
        }

        return datas;
    }

    @Override
    public boolean isDatasLoaded() {
        return datasLoaded;
    }
}
