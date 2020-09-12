/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI_old.ManualConfig;


import de.ft.interitus.UI_old.UI;
import de.ft.interitus.projecttypes.ProjectManager;

import java.io.Serializable;
import java.util.ArrayList;

public class DeviceConfiguration implements Serializable {

    public static final String DEFAULT_NAME = "New Config";
    private String name;
    private ArrayList<DeviceParameter> parameters = new ArrayList<>();
    private transient int DeviceConfigListIndex = -1;

    public DeviceConfiguration(String name, ArrayList<DeviceParameter> parameters) {
        this.name = name;
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() == 0) {
            name = DEFAULT_NAME;
        }
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


    /**
     * Update the name in the Configuration List
     *
     * @return true if the operation was successful
     */
    public boolean updateEntry() {
        if(DeviceConfigListIndex!=-1) {
            UI.runselection.getElements().get(DeviceConfigListIndex).setText(this.name);

        }
        int ID = ProjectManager.getActProjectVar().deviceConfigurations.indexOf(this);

        if (ID != -1) {


            return UI.MANUALCONFIG.updateNodeText(ID, this.name);
        } else {
            return false;
        }



    }

    /**
     * Update the name in the Configuration List and the identifier
     *
     * @return true if the operation was successful
     */
    public boolean updateEntry(Object identifier) {
        if(DeviceConfigListIndex!=-1) {
            UI.runselection.getElements().get(DeviceConfigListIndex).setText(this.name);
            UI.runselection.getElements().get(DeviceConfigListIndex).setIdentifier(identifier);

        }
        int ID = ProjectManager.getActProjectVar().deviceConfigurations.indexOf(this);

        if (ID != -1) {


            return UI.MANUALCONFIG.updateNodeText(ID, this.name);
        } else {
            return false;
        }


    }


    public int getDeviceConfigListIndex() {
        return DeviceConfigListIndex;
    }

    public void setDeviceConfigListIndex(int deviceConfigListIndex) {
        DeviceConfigListIndex = deviceConfigListIndex;
    }
}
