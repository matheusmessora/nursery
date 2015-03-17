package br.com.pandox.nursery.framework.boot;

import javax.annotation.PostConstruct;

//@Configuration
//@EnableScheduling
public class PluginBoot {

    public static final String PLUGIN_NAME = "acl";

    @PostConstruct
    public void init(){
        System.out.println("PLUGIN-ACL2 booting...");
    }

    public PluginBoot(){
        System.out.println("PLUGIN-ACL2 booting...");
    }


}
