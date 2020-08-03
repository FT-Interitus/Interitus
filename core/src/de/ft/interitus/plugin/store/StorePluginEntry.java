/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.plugin.store;

public class StorePluginEntry {
    int id;
    String name;
    double version;
    String description;
    String image;
    String path;
    String detailed_description;

    public StorePluginEntry(int id, String name, double version, String path, String description, String image, String detailed_description) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.path = path;
        this.version = version;
        this.description = description;
        this.detailed_description = detailed_description;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDetailed_description() {
        return detailed_description;
    }

    public void setDetailed_description(String detailed_description) {
        this.detailed_description = detailed_description;
    }
}
