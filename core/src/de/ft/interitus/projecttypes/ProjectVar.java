/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.DataWire;
import de.ft.interitus.Block.Wire;
import de.ft.interitus.Block.WireNode;
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
    public ArrayList<Wire> wires = new ArrayList<>(); //Liste aller Wires die gesetzt werden
    public ArrayList<Wire> visible_wires = new ArrayList<>(); //Liste aller Sichtbaren Wires
    public Wire moving_wires = null; //Liste aller Wires die bewegt werden //Die erste ist die "master moving wire"
    public ArrayList<WireNode> wireNodes = new ArrayList<>(); //Liste aller gesetzer Nodes
    public ArrayList<WireNode> visibleWireNodes = new ArrayList<>(); //Liste aller sichtbaren Nodes
    public Vector2 cam_pos = new Vector2();
    public float zoom = 1;
    public Block marked_block = null; // Welcher Block ist makiert ?
    public Vector2 diff_save = new Vector2(); //Unterschied zwischen Blöckem die scih überllappen
    public boolean ismoving = false; //Wird ein Block bewegt
    public ArrayList<Block> marked_block_overlapping = new ArrayList<>(); //Mit welchen Blöcken überlappt der Aktuell ausgewählte block ?
    public ArrayList<Block> showduplicat = new ArrayList<>(); //Bei welchen Blöcken wir das Duplicat angezeigt?
    public Block biggestblock = null; // Welcher ist der Block der die größten überlappungen mit dem bewegten Block hat
    public Block jumping_block = null; //Welcher Block hat die größte überlappungsfläche mit dem makierten?
    public boolean showleftdocker = false; //Wenn ein connector ausgewählt wird bieten sich die anderen Blöcke zum verbinden an
    public Block connetor_offerd_hoverd_block = null; //Der Block über den man fährt welcher gehovert wird
    public Block wire_beginn = null;  //um bei verbindungen über eine Node trotzdem noch den Nachbar erfahren zu können
    public Wire mousehoveredwire = null;
    public boolean removeblock = false; //Wenn ein Block über die ablage Fläche gehalten wird
    public int vcs = VCS.NONE;
    public ProjectType projectType = null;
    public boolean changes = false; //Wurde etwas geändert seit dem letzten speichern
    private String filename = ""; //Der Name der aktuell geöffneten Datei
    public volatile String path = ""; // Wo ist die aktuell geöffnete Datei gespeichert
    public ArrayList<Thread> threads = new ArrayList<>();
    public ArrayList<Object> requestobj = new ArrayList<>();
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
