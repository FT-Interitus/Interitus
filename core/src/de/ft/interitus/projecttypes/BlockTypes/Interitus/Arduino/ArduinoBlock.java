/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino;

import de.ft.interitus.projecttypes.BlockTypes.BlockMode;
import de.ft.interitus.projecttypes.CodeShowable;

public abstract class ArduinoBlock extends BlockMode implements CodeShowable {
    public abstract String getCode();
    public abstract String getHeaderCode(boolean inserted);

    @Override
   final public String getShowCode() {
        return this.getCode();
    }
}
