package br.com.pandox.nursery.domain.metric.model;

import br.com.pandox.nursery.domain.CommandException;
import br.com.pandox.nursery.domain.metric.event.CreateDataEvent;
import br.com.pandox.nursery.domain.metric.model.vo.MetricData;
import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.infrastructure.event.listener.EventListener;
import com.google.common.base.Optional;
import com.google.common.collect.Iterables;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Minutes;

import java.util.List;

public class MetricImpl implements Metric {

    private static final Logger LOGGER = LogManager.getLogger();

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
    public void addData(MetricData data, EventListener eventListener) {
        Optional<MetricData> lastData = getLastData();
        if(lastData.isPresent()){
            DateTime lastCreationDate = new DateTime(lastData.get().getDateCreation());
            DateTime now = new DateTime();
            int minutes = Minutes.minutesBetween(lastCreationDate, now).getMinutes();
            LOGGER.info("Minutes between: " + minutes);
            if(minutes < getTimeInterval()) {
                throw new CommandException("You must wait %s minutes for sending another data", getTimeInterval());
            }
        }
//        if(status.equals(Monitor.Status.UNREGISTERED) || status.equals(Monitor.Status.STOPPED)){
//            throw new CommandException("Can not add metric to Monitor in %s status", status.name());
//        }
        this.datas.add(data);
        eventListener.post(new CreateDataEvent(this));
    }

    private Optional<MetricData> getLastData(){
        MetricData last = Iterables.getLast(datas, null);
        return Optional.fromNullable(last);
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
