package br.com.pandox.nursery.framework.screamer;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class ScremManager {

    private static Set<Scream> screams;

    @PostConstruct
    public void init() {
        screams = new HashSet<Scream>();
    }

    public void setScream(Scream scream) {
        screams.add(scream);
    }

    public void shout(ScreamLevel level, String message) {
        for (Scream scream : screams) {
            scream.shout(level, message);
        }
    }

}
