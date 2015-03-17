package br.com.pandox.nursery.acl;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

@Configuration
@EnableScheduling
public class PluginBoot {

    public static final String PLUGIN_NAME = "acl";

    @PostConstruct
    public void init(){
        System.out.println("PLUGIN-ACL2 booting...");
    }


}
