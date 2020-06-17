package de.ft.interitus.UI.UIElements.TabBar;

import de.ft.interitus.UI.UIElements.Button;

public class Tab {
    Button TabButton;
    public Tab(){
        TabButton=new Button();
    }

    public Button getTabButton() {
        return TabButton;
    }

    public void setTabButton(Button tabButton) {
        TabButton = tabButton;
    }
}
