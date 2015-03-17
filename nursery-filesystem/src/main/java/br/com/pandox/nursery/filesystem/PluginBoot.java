package br.com.pandox.nursery.filesystem;

import br.com.pandox.nursery.framework.stereotype.PluginConfiguration;

@PluginConfiguration(PluginBoot.PLUGIN_NAME)
public class PluginBoot {

    public static final String PLUGIN_NAME = "filesystem";

    public PluginBoot(){
        System.out.println("PLUGIN-FILESYSTEM1 booting...");
    }


}
