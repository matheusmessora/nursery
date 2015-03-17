package br.com.pandox.nursery.acl.config;

import br.com.pandox.nursery.framework.boot.PluginBoot;
import br.com.pandox.nursery.framework.classloading.ResourceLoader;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component(value = "acl-configurer")
public class Configurer {

    private List<Tcp> tcps;

    @PostConstruct
    public void init() throws IOException {
        tcps = new ArrayList<Tcp>();

        List<URL> resources = ResourceLoader.loadResources(PluginBoot.PLUGIN_NAME);
        List<String> properties = new ArrayList<String>();

        for (URL resource : resources) {
            properties.addAll(Resources.readLines(resource, Charsets.UTF_8));
        }

        for (String prop : properties) {
            tcps.add(new Tcp(prop));
        }
    }

    public List<Tcp> getTcps() {
        return tcps;
    }
}
