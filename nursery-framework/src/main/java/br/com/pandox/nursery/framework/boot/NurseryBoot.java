package br.com.pandox.nursery.framework.boot;

import br.com.pandox.nursery.framework.stereotype.PluginConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.reflections.Reflections;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@ComponentScan("br.com.pandox.nursery")
public class NurseryBoot {

    private static final Logger LOGGER = LogManager.getLogger();

    public NurseryBoot() {
        LOGGER.debug("Initializing Nursery Context");
        Reflections reflections = new Reflections("");
        Set<Class<?>> types = reflections.getTypesAnnotatedWith(PluginConfiguration.class);

        LOGGER.debug(new StringFormattedMessage("Found %s PluginsConfiguration", types.size()).getFormattedMessage());

    }
}
