package de.ft.interitus.UI.ManualConfig;


import de.ft.interitus.UI.UI;
import de.ft.interitus.projecttypes.ProjectManager;

import java.io.Serializable;
import java.util.ArrayList;

public class DeviceConfiguration implements Serializable {

    private String name;
    private ArrayList<DeviceParameter> parameters = new ArrayList<>();

    public DeviceConfiguration(String name, ArrayList<DeviceParameter> parameters) {
        this.name = name;
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addParameter(DeviceParameter parameter) {
        parameters.add(parameter);
    }

    public DeviceParameter get(int id) {
        return parameters.get(id);
    }

    public ArrayList<DeviceParameter> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<DeviceParameter> parameters) {
        this.parameters = parameters;
    }

    public boolean updateEntry() {
        int ID =  ProjectManager.getActProjectVar().deviceConfigurations.indexOf(this);

       if(ID!=-1) {

           UI.MANUALCONFIG.updateNodeText(ID,this.name);
           return true;
       }else{
           return false;
       }
    }
}
