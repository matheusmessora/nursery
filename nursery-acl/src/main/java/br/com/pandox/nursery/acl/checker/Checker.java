package br.com.pandox.nursery.acl.checker;

import br.com.pandox.nursery.acl.config.Configurer;
import br.com.pandox.nursery.acl.config.Tcp;
import br.com.pandox.nursery.acl.rest.RestClient;
import br.com.pandox.nursery.framework.screamer.ScreamLevel;
import br.com.pandox.nursery.framework.screamer.ScremManager;
import br.com.pandox.nursery.framework.stereotype.PluginService;
import org.apache.http.StatusLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

@PluginService
public class Checker {

    @Autowired
    private Configurer config;

    @Autowired
    private RestClient restClient;

    @Autowired
    private ScremManager manager;

    @Scheduled(fixedRate = 5000)
    public void scheduler(){
        for (Tcp tcp : config.getTcps()) {
            check(tcp);
        }
    }

    private void check(Tcp tcp){
        StatusLine status = restClient.execute(tcp);
        if(status.getStatusCode() == 200){
            manager.shout(ScreamLevel.INFO, "uri=" + tcp.toURI());
        }else {
            manager.shout(ScreamLevel.FATAL, "uri=" + tcp.toURI());
        }
    }
}
