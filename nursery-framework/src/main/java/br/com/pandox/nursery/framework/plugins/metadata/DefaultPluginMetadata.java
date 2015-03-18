package br.com.pandox.nursery.framework.plugins.metadata;

import br.com.pandox.nursery.framework.plugins.Plugin;

import java.util.Set;

public class DefaultPluginMetadata implements PluginMetadata {
    @Override public String name() {
        return null;
    }

    @Override public Plugin getPluginConfiguration() {
        return null;
    }

    @Override public String getScanPackages() {
        return null;
    }

    @Override public Set<Class<?>> getServices() {
        return null;
    }
}
