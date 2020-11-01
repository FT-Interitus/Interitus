/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes.BlockTypes.Interitus.Ev3;

import de.ft.interitus.projecttypes.BlockTypes.BlockMode;
import de.ft.interitus.projecttypes.CodeShowable;

public abstract class Ev3Block extends BlockMode implements CodeShowable {
    /**
     * @return Ev3 code
     * https://www.lego.com/cdn/cs/set/assets/blt77bd61c3ac436ea3/LEGO_MINDSTORMS_EV3_Firmware_Developer_Kit.pdf
     */
    public abstract String getCode();

    @Override
    final public String getShowCode() {
        return this.getCode();
    }
}
