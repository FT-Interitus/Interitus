package de.ft.interitus.UI.UIElements.TabBar;

import de.ft.interitus.utils.ArrayList;


public class TabBar {
    int x = 0;
    int y = 0;
    int w = 50;
    int h = 25;
    ArrayList<Tab> tabbs = new ArrayList<>();
    Tab selectedTab = null;

    public TabBar(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public TabBar() {

    }


    public void setBounds(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

    }

    public void setTabs(Tab... tabbs) {
        this.tabbs.clear();
        for (int i = 0; i < tabbs.length; i++) {
            this.tabbs.add(tabbs[i]);
        }
    }

    public void addTab(Tab tabtoadd) {
        this.tabbs.add(tabtoadd);
    }

    public void draw() {
        int aktualxpluspos = 0;
        for (int i = 0; i < this.tabbs.size(); i++) {
            tabbs.get(i).getTabButton().setX(this.x + aktualxpluspos);
            tabbs.get(i).getTabButton().setY(this.y);
            tabbs.get(i).getTabButton().setH(this.h);
            tabbs.get(i).getTabButton().draw();
            aktualxpluspos = aktualxpluspos + tabbs.get(i).getTabButton().getW();
            if (tabbs.get(i).getTabButton().isjustPressednormal()) {
                selectedTab = tabbs.get(i);
            }
        }

    }

    public ArrayList<Tab> getTabbs() {
        return tabbs;
    }

    public Tab getSelectedTab() {
        return selectedTab;
    }
}
