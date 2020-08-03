/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.ManualConfig;

import java.io.Serializable;

public class DeviceParameter implements Serializable {

    private Object parameter;

    public DeviceParameter(Object parameter) {
        this.parameter = parameter;
    }


    public Object getParameter() {
        return parameter;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }
}
