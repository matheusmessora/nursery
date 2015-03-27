package br.com.pandox.nursery.view.rest.monitor;


import br.com.pandox.nursery.view.rest.AbstractDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

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
