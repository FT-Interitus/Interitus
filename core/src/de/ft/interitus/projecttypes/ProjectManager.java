/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes;

import de.ft.interitus.Programm;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.TabBar.Tab;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.Var;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.global.GlobalEventAdapter;
import de.ft.interitus.events.global.GlobalTabClickEvent;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.utils.ClearActOpenProgramm;

import java.util.Timer;
import java.util.TimerTask;


public class ProjectManager {
    static Timer time = new Timer();
    static Timer lock = new Timer();

    public static void init() {

        EventVar.globalEventManager.addListener(new GlobalEventAdapter() {
            @Override
            public void tabclicked(GlobalTabClickEvent e, Tab tab) {

               change(tab.getIndex());

            }
        });

    }

    public static void change(int index) {

        getActProjectVar().programmingtime = (System.currentTimeMillis() - getActProjectVar().currentstarttime) + getActProjectVar().programmingtime;


        ProgrammingSpace.cam.position.set(Var.openprojects.get(index).cam_pos.x, Var.openprojects.get(index).cam_pos.y, 0);
        ProgrammingSpace.cam.zoom = Var.openprojects.get(index).zoom;
        Var.openprojectindex = index;
        getActProjectVar().currentstarttime = System.currentTimeMillis();

        time.cancel();
        time.purge();
        time = new Timer();

        UIVar.isdialogeopend = true;

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        UIVar.isdialogeopend = false;
                        UI.runselection.clear();
                    }
                },
                2000
        );



        time.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                ProjectManager.getActProjectVar().projectType.update();

            }
        }, 2000, 6000);



        Programm.logger.config("changed tab");
    }

    /***
     *
     * @return the opened ProjectsVar
     */
    public static ProjectVar getActProjectVar() {
        if (Var.openprojects.size() == 0) {
            throw new IllegalCallerException();
        } else {
            return Var.openprojects.get(Var.openprojectindex);
        }
    }

    public static void addProject(ProjectVar projectVar) {

        Var.openprojects.add(projectVar);

        Tab tab = new Tab();
        tab.getTabButton().setImage(AssetLoader.img_Tab);
        tab.getTabButton().setW(300);
        tab.getTabButton().widthoverText = true;
        tab.getTabButton().setText(Var.openprojects.getLastObject().getFilename());
        tab.setIndex(Var.openprojects.size()-1);
        UI.tabbar.addTab(tab);


    }

    public static ProjectVar getProjectVar(int index) {
        if(Var.openprojects.size() ==0 ) {
            return null;
        }

        if(index>=Var.openprojects.size()) {
            return null;
        }

        return Var.openprojects.get(index);
    }

    public static void CloseProject(int index) {


        ClearActOpenProgramm.clear(index);
        Var.openprojects.remove(index);
        UI.tabbar.getTabbs().remove(index);

        if(Var.openprojectindex==index) {
            Var.openprojectindex--;
        }

    }





}
