package de.ft.interitus.plugin.store;

public class StorePluginEntry {
    int id;
    String name;
    double version;
    String description;
    String image;
    String path;

    public StorePluginEntry(int id,String name,double version,String path,String description,String image) {
        this.id =id;
        this.name = name;
        this.image = image;
        this.path = path;
        this.version = version;
        this.description = description;

    }
}
