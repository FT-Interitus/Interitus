/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.UIElements;

import de.ft.interitus.events.UI.UIToggleEvent;
import de.ft.interitus.utils.ArrayList;

public class RadioButtonGroup {
   private final ArrayList<RadioButton> radioButtons = new ArrayList<>();

    public void add(RadioButton radioButton) {

        radioButtons.add(radioButton);
        radioButton.getToggleEvent().addListener(new UIToggleEvent() {

            @Override
            public void toggled(boolean enabled, UIElement sender) {

                for(RadioButton radioButton1:radioButtons) {
                    if(radioButton1==sender) {
                        radioButton1.setToggleState(true);
                        continue;

                    }
                    radioButton1.setToggleState(false);
                }

            }
        });

    }
}
