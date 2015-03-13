package br.com.pandox.nursery.filesystem.check;

import br.com.pandox.nursery.filesystem.config.Configurer;
import br.com.pandox.nursery.filesystem.config.Folder;
import br.com.pandox.nursery.framework.screamer.ScreamLevel;
import br.com.pandox.nursery.framework.screamer.ScremManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FilePermission;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.AccessControlException;
import java.security.AccessController;

@Service(value = "filesystem-checker")
public class Checker {

    @Autowired
    private Configurer configurer;

    @Autowired
    private ScremManager manager;

    @Scheduled(fixedRate = 5000)
    public void scheduler(){
        for (Folder folder : configurer.getFolders()) {
            check(folder);
        }
    }

    private void check(Folder folder){
        String location = folder.getLocation();

        try {


            boolean readable = Files.isReadable(Paths.get(location));
            if (!readable)
                throw new AccessControlException("opa");

            AccessController.checkPermission(new FilePermission(location, "read"));
            manager.shout(ScreamLevel.INFO, "Pasta OKAY");
        } catch (AccessControlException e) {
            manager.shout(ScreamLevel.FATAL, String.format("Folder %s with bad permissions", location));
        }
    }
}
