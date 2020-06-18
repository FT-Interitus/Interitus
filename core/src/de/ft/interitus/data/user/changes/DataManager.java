package de.ft.interitus.data.user.changes;

import de.ft.interitus.Block.Block;
import de.ft.interitus.projecttypes.ProjectManager;

public class DataManager {


    public static void change(Block block, boolean created, boolean deleted) { //wird aufgreufen wenn etwas verändert wird
        if (!ProjectManager.getActProjectVar().changes) {
            ProjectManager.getActProjectVar().changes = true;
            //Gdx.graphics.setTitle(filename+ "*"); //TODO Windows problems
        }
        //SaveChanges.changedValue(block, created, deleted); //Die Änderung wird in den Rückgänig Stack geschrieben
    }

    public static void saved() { //Wird aufgerufen wenn das programm gespeichert wurde und somit keine Ungespeicherten änderungen vorliegen
        ProjectManager.getActProjectVar().changes = false;
        // Gdx.graphics.setTitle(filename);
    }
}
