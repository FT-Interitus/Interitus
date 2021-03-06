/*
 * Copyright (c) 2021.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes;

import com.badlogic.gdx.Gdx;
import de.ft.interitus.WindowManager;
import de.ft.interitus.Program;
import de.ft.interitus.ProgramingSpace;
import de.ft.interitus.UI.Notification.Notification;
import de.ft.interitus.UI.Notification.NotificationManager;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.UIElements.TabBar.Tab;
import de.ft.interitus.UI.tappedbar.BlockTappedBar;
import de.ft.interitus.Var;
import de.ft.interitus.datamanager.programmdata.experience.ExperienceVar;
import de.ft.interitus.datamanager.userdata.save.DataSaver;
import de.ft.interitus.events.EventVar;
import de.ft.interitus.events.global.GlobalCloseEvent;
import de.ft.interitus.events.global.GlobalEventAdapter;
import de.ft.interitus.events.global.GlobalTabClickEvent;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.utils.ClearActOpenProgramm;

import java.io.File;
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

                        if(Var.openprojects.get(i).path!=""&&new File(Var.openprojects.get(i).path).exists()) {

                            DataSaver.save(Gdx.files.absolute(Var.openprojects.get(i).path),Var.openprojects.get(i));

                        }else {

                            canbeclosed = false;
                            NotificationManager.sendNotification(new Notification(AssetLoader.information, "Schliesen abgebrochen", "In deinem Projekt " + Var.openprojects.get(i).getFilename() + " wurden\nungspeicherte Änderungen erkannt!"));

                        }


                    } else {
                        closeProject(Var.openprojects.indexOf(Var.openprojects.get(i)));
                        i--;
                    }

                }

                return canbeclosed;
            }
        });


        time = new Timer();
        time.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (ProjectManager.getActProjectVar() != null) {
                    ProjectManager.getActProjectVar().projectType.update();
                }


            }
        }, 0, 1700);

    }

    public static void change(int index) {

        WindowManager.switchTo(WindowManager.Windows.programingspace);
        if(getActProjectVar()!=null)
        getActProjectVar().programmingtime = (System.currentTimeMillis() - getActProjectVar().currentstarttime) + getActProjectVar().programmingtime;

        UI.runselection.setDefaultText("");

        ProgramingSpace.cam.position.set(Var.openprojects.get(index).cam_pos.x, Var.openprojects.get(index).cam_pos.y, 0);
        ProgramingSpace.cam.zoom = Var.openprojects.get(index).zoom;
        Var.openprojectindex = index;
        getActProjectVar().currentstarttime = System.currentTimeMillis();

        BlockTappedBar.init();

        /***
         * Remove Marked Block if it doesn't exist in the Block ArrayList
         */
        if(ProjectManager.getActProjectVar()!=null) {
            if(!ProjectManager.getActProjectVar().blocks.contains(ProjectManager.getActProjectVar().marked_blocks)) {
                ProjectManager.getActProjectVar().marked_blocks.clear();

            }
        }
        UI.runselection.clear();



        ProjectManager.getActProjectVar().projectType.getProjectFunktions().switchedTo();
        UI.tabbar.setSelectedTabindex(index);
        Program.logger.config("changed tab");
    }

    /***
     *
     * @return the opened ProjectsVar
     */
    public static ProjectVar getActProjectVar() {
        if (Var.openprojects.size() == 0) {


            WindowManager.switchTo(WindowManager.Windows.welcome);


            return null;
        } else {
            if(Var.openprojectindex<0) { //You are in Homesection
                return null;
            }

            try {
                return Var.openprojects.get(Var.openprojectindex);
            }catch (IndexOutOfBoundsException e) {
                Program.logger.severe("Program-Error: Restart is necessary");
                return null;

            }
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

        WindowManager.switchTo(WindowManager.Windows.programingspace);
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


    public static void closeProject(int index) {

        try {
            ClearActOpenProgramm.clear(index);
            UI.tabbar.getTabbs().remove(index);
            for (Tab tab : UI.tabbar.getTabbs()) {
                tab.setIndex(tab.getIndex() - 1);
            }


            if (Var.openprojectindex - 1 == -1) {


                WindowManager.switchTo(WindowManager.Windows.welcome);


            } else {
                change(Var.openprojectindex - 1);


            }
            Var.openprojects.remove(index);
        } catch (Exception e) {
            e.printStackTrace();
        }


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
