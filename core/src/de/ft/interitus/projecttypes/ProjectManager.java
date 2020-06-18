package de.ft.interitus.projecttypes;

import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.Var;

public class ProjectManager {
    public static void change(int index) {


        ProgrammingSpace.cam.position.set(Var.openprojects.get(index).cam_pos.x,Var.openprojects.get(index).cam_pos.y,0);
        ProgrammingSpace.cam.zoom = Var.openprojects.get(index).zoom;
        Var.openprojectindex = index;
    }

    public static ProjectVar getActProjectVar() {
        return Var.openprojects.get(Var.openprojectindex);
    }
}
