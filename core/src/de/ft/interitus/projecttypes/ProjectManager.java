package de.ft.interitus.projecttypes;

import de.ft.interitus.Programm;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.Var;

import java.util.Timer;
import java.util.TimerTask;

public class ProjectManager {
   static Timer time = new Timer();
    public static void change(int index) {

        getActProjectVar().programmingtime = (System.currentTimeMillis() - getActProjectVar().currentstarttime) + getActProjectVar().programmingtime;


        ProgrammingSpace.cam.position.set(Var.openprojects.get(index).cam_pos.x, Var.openprojects.get(index).cam_pos.y, 0);
        ProgrammingSpace.cam.zoom = Var.openprojects.get(index).zoom;
        Var.openprojectindex = index;
        getActProjectVar().currentstarttime = System.currentTimeMillis();

        time.purge();
        time = new Timer();


    time.scheduleAtFixedRate(new TimerTask() {
    @Override
    public void run() {
        ProjectManager.getActProjectVar().projectType.update();

    }
    },0,1000);





        Programm.logger.config("changed tab");
    }

    /***
     *
     * @return the opened ProjectsVar
     */
    public static ProjectVar getActProjectVar() {
        if(Var.openprojects.size()==0) {
            return null;
        }else {
            return Var.openprojects.get(Var.openprojectindex);
        }
    }
}
