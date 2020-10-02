/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes;

import com.badlogic.gdx.files.FileHandle;
import de.ft.interitus.Programm;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.UI.Notification.Notification;
import de.ft.interitus.UI.Notification.NotificationManager;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.UIElements.TabBar.Tab;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.UI.tappedbar.BlockTappedBar;
import de.ft.interitus.Var;
import de.ft.interitus.datamanager.programmdata.Data;
import de.ft.interitus.datamanager.programmdata.experience.ExperienceVar;
import de.ft.interitus.datamanager.userdata.load.DataLoader;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.global.GlobalCloseEvent;
import de.ft.interitus.events.global.GlobalEventAdapter;
import de.ft.interitus.events.global.GlobalTabClickEvent;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.utils.ClearActOpenProgramm;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class ProjectManager {
    static Timer time = new Timer();


    public static void init() {

        EventVar.globalEventManager.addListener(new GlobalEventAdapter() {
            @Override
            public void tabclicked(GlobalTabClickEvent e, Tab tab) {

                if (tab.getIndex() != Var.openprojectindex) {
                    change(tab.getIndex());

                }

            }

            @Override
            public boolean closeprogramm(GlobalCloseEvent e) {
                boolean canbeclosed = true;

                for (int i = 0; i < Var.openprojects.size(); i++) {


                    if (Var.openprojects.get(i).changes) {
                        canbeclosed = false;

                        NotificationManager.sendNotification(new Notification(AssetLoader.information, "Schliesen abgebrochen", "In deinem Projekt " + Var.openprojects.get(i).getFilename() + " wurden\nungspeicherte Änderungen erkannt!"));

                    } else {
                        CloseProject(Var.openprojects.indexOf(Var.openprojects.get(i)));
                        i--;
                    }

                }

                return canbeclosed;
            }
        });

    }

    public static void change(int index) {
        getActProjectVar().programmingtime = (System.currentTimeMillis() - getActProjectVar().currentstarttime) + getActProjectVar().programmingtime;

        UI.runselection.setDefaultText("");

        ProgrammingSpace.cam.position.set(Var.openprojects.get(index).cam_pos.x, Var.openprojects.get(index).cam_pos.y, 0);
        ProgrammingSpace.cam.zoom = Var.openprojects.get(index).zoom;
        Var.openprojectindex = index;
        getActProjectVar().currentstarttime = System.currentTimeMillis();

        time.cancel();
        time.purge();


        BlockTappedBar.init();

        UIVar.isdialogeopend = true;

        final Notification waitforprojectnotification = new Notification(AssetLoader.information, "Bitte Warten...", "Das Projekt wird aktiviert").setCloseable(false).setProgressbarvalue(0).rollin(false);
        NotificationManager.sendNotification(waitforprojectnotification);

        UI.runselection.clear();

        Timer Timer = new Timer();
        Timer.scheduleAtFixedRate(new TimerTask() {
            int counter = 0;


            @Override
            public void run() {


                if (counter <= 100) {
                    waitforprojectnotification.setProgressbarvalue(counter);
                }


                if (counter == 110) {

                    waitforprojectnotification.close();
                    UIVar.isdialogeopend = false;
                    UI.runselection.clear();
                    System.out.println("clear");
                    this.cancel();
                }

                counter++;

            }
        }, 0, 15);

        time = new Timer();
        time.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                ProjectManager.getActProjectVar().projectType.update();

            }
        }, 1700, 1000);


       ProjectManager.getActProjectVar().projectType.getProjectFunktions().switchedto();
       UI.tabbar.setSelectedTabindex(index);
        Programm.logger.config("changed tab");
    }

    /***
     *
     * @return the opened ProjectsVar
     */
    public static ProjectVar getActProjectVar() {
        if (Var.openprojects.size() == 0) {
            Data.close(true);
            System.exit(0);
            return null;
        } else {
            return Var.openprojects.get(Var.openprojectindex);
        }

    }

    public static void addProject(ProjectVar projectVar) {

        Var.openprojects.add(projectVar);

        ExperienceVar.newprojects++;
        Tab tab = new Tab();
        // tab.getTabButton().setImage(AssetLoader.img_Tab);
        tab.getCloseButton().setImage(AssetLoader.close_notification);
        tab.getCloseButton().setImage_mouseover(AssetLoader.close_notification_mouseover);
        tab.getTabButton().setW(300);
        tab.getCloseButton().setW(25);
        tab.getTabButton().widthoverText = true;
        tab.getTabButton().setText(Var.openprojects.getLastObject().getFilename());
        tab.getTabButton().setText_pos_change_y(1);
        tab.setIndex(Var.openprojects.size() - 1);
        UI.tabbar.addTab(tab);


    }

    public static ProjectVar getProjectVar(int index) {
        if (Var.openprojects.size() == 0) {
            return null;
        }

        if (index >= Var.openprojects.size()) {
            return null;
        }

        return Var.openprojects.get(index);
    }

    public static void CloseProject(int index) {
        UI.tabbar.setSelectedTabindex(0);
        CloseProject(index,true);
    }

    public static void CloseProject(int index,boolean allowedtoclose) {


        ClearActOpenProgramm.clear(index);
        UI.tabbar.getTabbs().remove(index);
        for (Tab tab : UI.tabbar.getTabbs()) {
            tab.setIndex(tab.getIndex() - 1);
        }

        if (Var.openprojectindex - 1 == -1) {
            Data.close(true);
            if(allowedtoclose) {
                System.exit(0);
            }
            return;
        }
        change(Var.openprojectindex - 1);
        Var.openprojects.remove(index);


    }


/* usage?
    public static void reloadProject(int index) {
        if(Objects.requireNonNull(getProjectVar(index)).changes) {
            return;
        }



       String path = getProjectVar(index).path;
        if(path.contentEquals("")) {
            return;
        }
       String name = getProjectVar(index).path;


        NotificationManager.suppress = true;
        CloseProject(index,false);

        while(Var.isclearing);

        DataLoader.load(new FileHandle(path),name,path);
        NotificationManager.suppress = false;


    }

 */


}
