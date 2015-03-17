package br.com.pandox.nursery.filesystem;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

@Configuration
@EnableScheduling
public class PluginBoot {

    public static final String PLUGIN_NAME = "filesystem";

    @PostConstruct
    public void init(){
        System.out.println("PLUGIN-FILESYSTEM1 booting...");
    }


}
