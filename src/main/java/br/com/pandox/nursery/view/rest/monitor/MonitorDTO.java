package br.com.pandox.nursery.view.rest.monitor;


import br.com.pandox.nursery.domain.monitor.model.Monitor;
import br.com.pandox.nursery.view.rest.AbstractDTO;
import br.com.pandox.nursery.view.rest.Link;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.BeanUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MonitorDTO extends AbstractDTO {

    public MonitorDTO() {
        super();
    }

    public MonitorDTO(String name, String machine) {
        this();
        this.name = name;
        this.machine = machine;
    }

    public MonitorDTO(String name, String machine, String status) {
        this();
        this.name = name;
        this.machine = machine;
        this.status = status;
    }

    public Long id;
    public String machine;
    public String status;
    public String name;
    public String version;

    public MonitorDTO(long monitorId) {

    }

    public MonitorDTO(Monitor monitor) {
        super();
        BeanUtils.copyProperties(monitor, this);
        this.status = monitor.getStatus().name();

        this.addLink(new Link("/api/vSNAPSHOT/metric?monitor_id=" + this.getId(), "metrics"));
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public String getMachine() {
        return machine;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

}
