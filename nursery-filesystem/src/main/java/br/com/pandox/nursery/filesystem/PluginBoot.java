package br.com.pandox.nursery.filesystem;

import br.com.pandox.nursery.framework.plugins.Plugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//@PluginConfiguration
public class PluginBoot implements Plugin {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final String PLUGIN_NAME = "filesystem";

    private boolean running;

    @Override public void start() {
        LOGGER.debug("Subing filesystem...");
        running = true;
    }

    @Override public boolean isRunning() {
        return running;
    }


    @Override public String getName() {
        return PLUGIN_NAME;
    }
}
