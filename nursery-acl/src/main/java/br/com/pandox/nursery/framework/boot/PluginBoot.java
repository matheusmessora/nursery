package br.com.pandox.nursery.framework.boot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan("br.com.pandox.nursery.acl")
@EnableScheduling
public class PluginBoot {

    public static final String PLUGIN_NAME = "acl";

}
