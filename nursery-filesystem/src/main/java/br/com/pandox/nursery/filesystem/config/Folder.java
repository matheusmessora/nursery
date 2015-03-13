package br.com.pandox.nursery.filesystem.config;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Folder {

    private String location;
    private List<String> permissions;

    public Folder(String prop) {
        permissions = new ArrayList<String>();

        String[] split = prop.split("\\|");
        location = split[0];

        for (int i = 1; i < split.length; i++) {
            String s = split[i];
            permissions.add(s);
        }
    }

    public String getLocation() {
        return location;
    }

    public List<String> getPermissions() {
        return permissions;
    }
}
