package de.ft.robocontrol.data.user.changes;

import de.ft.robocontrol.Block.Block;

public class DataManager {
    public static boolean changes = false; //Wurde etwas geändert seit dem letzten speichern
    public static String filename = ""; //Der Name der aktuell geöffneten Datei
    public static volatile String path = ""; // Wo ist die aktuell geöffnete Datei gespeichert

    public static void change(Block block, boolean created, boolean deleted) { //wird aufgreufen wenn etwas verändert wird
        if (!changes) {
            changes = true;
            //Gdx.graphics.setTitle(filename+ "*"); //TODO Windows problems
        }
        SaveChanges.changedValue(block, created, deleted); //Die Änderung wird in den Rückgänig Stack geschrieben
    }

    public static void saved() { //Wird aufgerufen wenn das programm gespeichert wurde und somit keine Ungespeicherten änderungen vorliegen
        changes = false;
        // Gdx.graphics.setTitle(filename);
    }
}
