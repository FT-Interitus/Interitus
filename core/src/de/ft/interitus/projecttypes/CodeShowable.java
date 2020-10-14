/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes;

/***
 * If this interface is implemented by a Block-Mode the code from the Block-Mode will be shown to the user in the Code-Dialog or via CodeHovering
 *
 */
public interface CodeShowable {
    /***
     * Will be return
     * @return
     */
    String getShowCode();
}
