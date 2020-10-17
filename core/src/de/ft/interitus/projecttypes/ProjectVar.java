/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.DataWire;
import de.ft.interitus.UI.ManualConfig.DeviceConfiguration;
import de.ft.interitus.UI.UI;
import de.ft.interitus.Var;
import de.ft.interitus.projecttypes.Addons.Addon;
import de.ft.interitus.utils.ArrayList;

public abstract class ProjectVar {

    public  ArrayList<Integer> Blockswitherrors = new ArrayList<>();
    //Anmerkung ganz wichtig hier alle beim clearen auf null setzen damit alle referenzierungen auf einen Block gelöscht werden damit der Speicherplatz freigegeben werden kann
    public boolean wires_allowed = true;  //Wires können blockiert werden
    public ArrayList<Block> blocks = new ArrayList<>(); //Liste aller erstellen Blöcke
    public ArrayList<Block> visible_blocks = new ArrayList<>(); //Liste aller Blöcke die sich im sichtbereich der Camera befinden

    public Vector2 cam_pos = new Vector2();
    public float zoom = 1;
    public Block marked_block = null; // Welcher Block ist makiert ?
    public Block duplicate_block_right = null; // Which Block shows the duplicate
    public Block duplicate_block_left = null; // Which Block shows the duplicate
    public Vector2 diff_save = new Vector2(); //Unterschied zwischen Blöckem die scih überllappen
    public Block moving_block = null; //Wird ein Block bewegt
    public boolean showleftdocker = false; //Wenn ein connector ausgewählt wird bieten sich die anderen Blöcke zum verbinden an
    public Block connetor_offerd_hoverd_block = null; //Der Block über den man fährt welcher gehovert wird
    public int vcs = VCS.NONE;
    public ProjectType projectType = null;
    public boolean changes = false; //Wurde etwas geändert seit dem letzten speichern
    private String filename = ""; //Der Name der aktuell geöffneten Datei
    public volatile String path = ""; // Wo ist die aktuell geöffnete Datei gespeichert
    public ArrayList<Thread> threads = new ArrayList<>();
    public DataWire moveingdatawire = null;
    public ArrayList<Addon> enabledAddons = new ArrayList<>();
    public Object projectSettings = null;
    public ArrayList<ProjectVariable> projectVariables = new ArrayList<>();
    public ArrayList<Tool> tools = new ArrayList<>();


    public ArrayList<DeviceConfiguration> deviceConfigurations = new ArrayList<>();

    public long programmingtime = 0;
    public long currentstarttime;

    public ProjectVar() {
        currentstarttime = System.currentTimeMillis();
        cam_pos.set(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);

    }

    public void setFilename(String filename) {
        this.filename = filename;


        try {
            UI.tabbar.getTabbs().get(Var.openprojects.indexOf(this)).getTabButton().setText(filename);
        }catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }

    public String getFilename() {
        return filename;
    }
}
