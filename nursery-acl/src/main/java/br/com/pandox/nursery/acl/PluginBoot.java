package br.com.pandox.nursery.acl;

import br.com.pandox.nursery.framework.plugins.Plugin;
import br.com.pandox.nursery.framework.stereotype.PluginConfiguration;

@PluginConfiguration(scan = "br.com.pandox.nursery.acl")
public class PluginBoot implements Plugin {

    public static final String PLUGIN_NAME = "acl";
    private boolean running;

    @Override public String getName() {
        return PLUGIN_NAME;
    }

    @Override public void start() {
        running = true;
    }

    @Override public boolean isRunning() {
        return running;
    }
}
