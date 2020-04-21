package de.ft.interitus.plugin;

public class PluginRegister {
   private String name = "";
   private double version;
    public PluginRegister() {}

    public void config(String option,String selection) {
        switch (option) {
            case "name":
                this.name = selection;
        }
    }

    public String getName() {
        return name;
    }
}
