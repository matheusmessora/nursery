package br.com.pandox.nursery.framework.screamer;


public interface Scream {

    public void init();

    public void shout(ScreamLevel level, String message);
}
