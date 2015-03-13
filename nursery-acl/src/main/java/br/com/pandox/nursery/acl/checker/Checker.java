package br.com.pandox.nursery.acl.checker;

import br.com.pandox.nursery.acl.config.Configurer;
import br.com.pandox.nursery.acl.config.Tcp;
import br.com.pandox.nursery.acl.rest.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Checker {

    @Autowired
    private Configurer config;

    @Autowired
    private RestClient restClient;

    @Scheduled(fixedRate = 5000)
    public void go(){
        for (Tcp tcp : config.getTcp()) {
            restClient.execute(config.getTcp().get(0));
        }
    }
}
