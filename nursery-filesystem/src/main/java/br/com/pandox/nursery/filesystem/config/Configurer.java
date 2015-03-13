package br.com.pandox.nursery.filesystem.config;

import br.com.pandox.nursery.framework.boot.PluginBoot;
import br.com.pandox.nursery.framework.classloading.ResourceLoader;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.nio.file.attribute.AclEntryPermission;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.*;

@Component(value = "filesystem-configurer")
public class Configurer {

    private List<Folder> folders;

    @PostConstruct
    public void init() throws IOException {
        folders = new ArrayList<Folder>();

        List<URL> resources = ResourceLoader.loadResources(PluginBoot.PLUGIN_NAME);
        List<String> properties = new ArrayList<String>();

        for (URL resource : resources) {
            properties.addAll(Resources.readLines(resource, Charsets.UTF_8));
        }


        for (String prop : properties) {
            checkPropConfiguration(prop);
            folders.add(new Folder(prop));
        }
    }

    public List<Folder> getFolders() {
        return folders;
    }

    private static void checkPropConfiguration(String props){
        String[] split = props.split("|");
        checkState(split.length >= 2, "Malformed property. You should configure it as {folder}|{permissions...}. {}", props);

        for (int i = 1; i < split.length; i++) {
            String permission = split[i];

            boolean invalidArgument = !"read".equals(permission) && !"write".equals(permission);
            checkState(invalidArgument, "Malformed property. You should configure it as {folder}|{permissions...}. {}", props);
        }
    }
}
