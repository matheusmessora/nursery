package br.com.pandox.nursery.framework.plugins.metadata;


import br.com.pandox.nursery.framework.plugins.Plugin;

import java.util.Set;

public interface PluginMetadata {

    String name();

    Plugin getPluginConfiguration();

    String getScanPackages();

    Set<Class<?>> getServices();




}
