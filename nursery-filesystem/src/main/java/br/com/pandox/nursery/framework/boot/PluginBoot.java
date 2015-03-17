package br.com.pandox.nursery.framework.boot;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

@Configuration
@EnableScheduling
public class PluginBoot {

    public static final String PLUGIN_NAME = "filesystem";

    public PluginBoot(){
        System.out.println("PLUGIN-FILESYSTEM1 booting...");
    }


}
