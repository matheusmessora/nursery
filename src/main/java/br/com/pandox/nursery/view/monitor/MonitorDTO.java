package br.com.pandox.nursery.view.monitor;


import br.com.pandox.nursery.DataTransferObject;

public class MonitorDTO implements DataTransferObject {

    public MonitorDTO() {
    }

    public MonitorDTO(String name, String machine) {
        this.name = name;
        this.machine = machine;
    }

    public MonitorDTO(String name, String machine, String status) {
        this.machine = machine;
        this.status = status;
        this.name = name;
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
