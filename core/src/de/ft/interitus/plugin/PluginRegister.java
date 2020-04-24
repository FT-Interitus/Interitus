package de.ft.interitus.plugin;

public class PluginRegister {
   private String name = "";
   private double version;
    public PluginRegister() {}
    public PluginRegister(String name) {}
    public void config(String option,String selection) {
        switch (option) {
            case "name":
                this.name = selection;
                break;
            case "version":
                this.version = Double.parseDouble(selection);
        }
    }

    public String getName() {
        return name;
    }

    public double getVersion() {
        return version;
    }
}
