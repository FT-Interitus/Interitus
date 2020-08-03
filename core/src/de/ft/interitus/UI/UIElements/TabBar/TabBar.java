/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.UI.UIElements.TabBar;

import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.global.GlobalFileDropedEvent;
import de.ft.interitus.events.global.GlobalTabClickEvent;
import de.ft.interitus.utils.ArrayList;


public class TabBar {
   private int x = 0;
    private int y = 0;
    private int w = 50;
    private int h = 25;
    private final ArrayList<Tab> tabbs = new ArrayList<>();


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
                EventVar.globalEventManager.tabclicked(new GlobalTabClickEvent(this),tabbs.get(i));
            }
        }

    }

    public ArrayList<Tab> getTabbs() {
        return tabbs;
    }





}
