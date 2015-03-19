package br.com.pandox.nursery.domain.monitor.model;


public interface Monitor extends Model{

    public void save();

    Long getId();

    String getMachine();

    String getStatus();

    String getName();

}
