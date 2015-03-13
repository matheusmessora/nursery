package br.com.pandox.nursery.acl.config;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class Configurer {

    @PostConstruct
    public void init() throws IOException {
        tcp = new ArrayList<Tcp>();
        URL resource = Resources.getResource("nursery/acl/acl.properties");
        List<String> urls = Resources.readLines(resource, Charsets.UTF_8);

        for (String url : urls) {
            tcp.add(new Tcp(url));
        }
    }

    private List<Tcp> tcp;

    public List<Tcp> getTcp() {
        return tcp;
    }

    public void setTcp(List<Tcp> tcp) {
        this.tcp = tcp;
    }
}
