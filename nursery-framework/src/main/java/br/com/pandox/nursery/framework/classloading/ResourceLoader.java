package br.com.pandox.nursery.framework.classloading;

import com.google.common.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class ResourceLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceLoader.class);

    private static final String DEFAULT_PROPERTY = "config.properties";

//    * Nursery Properties files must follow this structure to be well-processed by the @{ResourceLoader}
//    * - Plugins properties for default configurations must be located in classpath:/nursery/{plugin-name}/config.properties
//     * - Override properties must be located under classpath:/nursery/custom/{plugin-name}/*.properties

    public static List<URL> loadResources(String plugin) throws IOException {
        checkNotNull(plugin, "plugin must be specified");

        List<URL> urls = new ArrayList<URL>();

        URL url = loadDefaultProperty(plugin);
        if(url != null) {
            urls.add(url);
        }


        url = loadCustomProperty(plugin);
        if (url != null) {
            urls.add(url);
        }

        return urls;
    }


    private static URL loadDefaultProperty(String plugin){
        URL resource = null;
        try {
            resource = Resources.getResource("nursery/" + plugin + "/config.properties");
        } catch (IllegalArgumentException e) {
            LOGGER.info("Plugin {} does not have a default config.properties", plugin);
        }

        return resource;
    }

    private static URL loadCustomProperty(String plugin){
        URL resource = null;
        try {
            resource = Resources.getResource("nursery/custom/" + plugin + "/config.properties");
        } catch (IllegalArgumentException e) {
            LOGGER.info("Plugin {} does not have a custom config.properties", plugin);
        }

        return resource;
    }


}
