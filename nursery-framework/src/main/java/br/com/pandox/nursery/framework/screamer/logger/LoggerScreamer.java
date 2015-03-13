package br.com.pandox.nursery.framework.screamer.logger;


import br.com.pandox.nursery.framework.screamer.Scream;
import br.com.pandox.nursery.framework.screamer.ScreamLevel;
import br.com.pandox.nursery.framework.screamer.ScremManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class LoggerScreamer implements Scream {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerScreamer.class);

    @Autowired
    private ScremManager manager;

    @PostConstruct
    public void init() {
        manager.setScream(this);

    }

    @Override
    public void shout(ScreamLevel level, String message) {
        if(ScreamLevel.INFO.equals(level)){
            LOGGER.info(message);
        }else {
            LOGGER.error(message);
        }
    }
}
