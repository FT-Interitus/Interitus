/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.projecttypes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import de.ft.interitus.Block.Block;
import de.ft.interitus.Block.Wire;
import de.ft.interitus.Block.WireNode;
import de.ft.interitus.UI.ManualConfig.DeviceConfiguration;
import de.ft.interitus.utils.ArrayList;

public abstract class ProjectVar {

    public static ArrayList<Integer> Blockwitherrors = new ArrayList<>();
    //Anmerkung ganz wichtig hier alle beim clearen auf null setzen damit alle referenzierungen auf einen Block gelöscht werden damit der Speicherplatz freigegeben werden kann
    public boolean wirezulassung = true;  //Wires können blockiert werden
    public ArrayList<Block> blocks = new ArrayList<Block>(); //Liste aller erstellen Blöcke
    public ArrayList<Block> visibleblocks = new ArrayList<Block>(); //Liste aller Blöcke die sich im sichtbereich der Camera befinden
    public ArrayList<Wire> wires = new ArrayList<>(); //Liste aller Wires die gesetzt werden
    public ArrayList<Wire> visiblewires = new ArrayList<>(); //Liste aller Sichtbaren Wires
    public Wire movingwires = null; //Liste aller Wires die bewegt werden //Die erste ist die "master moving wire"
    public ArrayList<WireNode> wireNodes = new ArrayList<>(); //Liste aller gesetzer Nodes
    public ArrayList<WireNode> visibleWireNodes = new ArrayList<>(); //Liste aller sichtbaren Nodes
    public Vector2 cam_pos = new Vector2();
    public float zoom = 1;
    //TODO Für multi select hier eventuel ArrayList
    public Block markedblock = null; // Welcher Block ist makiert ?
    public Vector2 unterschiedsave = new Vector2(); //Unterschied zwischen Blöckem die scih überllappen
    public boolean ismoving = false; //Wird ein Block bewegt
    public boolean marked = false; //Ist gerade ein Block makiert?
    public ArrayList<Block> uberlapptmitmarkedblock = new ArrayList<Block>(); //Mit welchen Blöcken überlappt der Aktuell ausgewählte block ?
    public ArrayList<Block> showduplicat = new ArrayList<Block>(); //Bei welchen Blöcken wir das Duplicat angezeigt?
    public Block biggestblock = null; // Welcher ist der Block der die größten überlappungen mit dem bewegten Block hat
    public Block blockmitdergrostenuberlappungmitmarkiertemblock = null; //Welcher Block hat die größte überlappungsfläche mit dem makierten?
    public boolean showleftdocker = false; //Wenn ein connector ausgewählt wird bieten sich die anderen Blöcke zum verbinden an
    public Block connetor_offerd_hoverd_block = null; //Der Block über den man fährt welcher gehovert wird
    public Block wire_beginn = null;  //um bei verbindungen über eine Node trotzdem noch den Nachbar erfahren zu können
    public Wire mousehoveredwire = null;
    public Block ismarkedbyother = null; // Gibt an welcher Block bei einem anderen Client makiert ist
    public boolean removeblock = false; //Wenn ein Block über die ablage Fläche gehalten wird
    public int vcs = VCS.NONE;
    public ProjectTypes projectType = null;
    public boolean changes = false; //Wurde etwas geändert seit dem letzten speichern
    public String filename = ""; //Der Name der aktuell geöffneten Datei
    public volatile String path = ""; // Wo ist die aktuell geöffnete Datei gespeichert
    public ArrayList<Thread> threads = new ArrayList<>();
    public ArrayList<Object> requestobj = new ArrayList<>();

    public ArrayList<DeviceConfiguration> deviceConfigurations = new ArrayList<>();

    public long programmingtime = 0;
    public long currentstarttime = 0;

    public ProjectVar() {
        currentstarttime = System.currentTimeMillis();
        cam_pos.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

    }
}
