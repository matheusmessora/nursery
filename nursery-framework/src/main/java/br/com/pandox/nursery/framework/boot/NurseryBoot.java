package br.com.pandox.nursery.framework.boot;

import br.com.pandox.nursery.framework.plugins.Plugin;
import br.com.pandox.nursery.framework.stereotype.PluginConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import java.util.Set;

@Configuration
@ComponentScan("br.com.pandox.nursery")
public class NurseryBoot {

    private static final Logger LOGGER = LogManager.getLogger();

    public NurseryBoot() throws Exception {
        LOGGER.debug("Initializing Nursery Context");
        Reflections reflections = new Reflections("");
        Set<Class<?>> types = reflections.getTypesAnnotatedWith(PluginConfiguration.class);

        LOGGER.debug("Found {} PluginsConfiguration", types.size());
        for (Class<?> type : types) {
            if(Plugin.class.isAssignableFrom(type)){
                Plugin instance = (Plugin) type.newInstance();

                try {
                    validatePlugin(instance);
                    startPlugin(instance);
                } catch (IllegalArgumentException e){
                    LOGGER.warn("Plugin must has an unique name");
                }
            }else {
                LOGGER.warn("Your PluginConfiguration must implements Plugin");
            }
        }
    }

    private void validatePlugin(Plugin plugin){
        Assert.hasText(plugin.getName());
    }

    private void startPlugin(Plugin plugin) {
        LOGGER.debug("Starting [{}] plugin", plugin.getName());
        plugin.start();
        if(plugin.isRunning()){
            LOGGER.debug("Plugin [{}] running", plugin.getName());
            initializeBeans(plugin);
        }
    }

    private void initializeBeans(Plugin plugin){
    }
}
