package de.ft.interitus.Block;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class BlockVar {


    //Anmerkung ganz wichtig hier alle beim clearen auf null setzen damit alle referenzierungen auf einen Block gelöscht werden damit der Speicherplatz freigegeben werden kann

    public static ArrayList<Block> blocks = new ArrayList<Block>(); //Liste aller erstellen Blöcke
    public static ArrayList<Block> visibleblocks = new ArrayList<Block>(); //Liste aller Blöcke die sich im sichtbereich der Camera befinden
    public static ArrayList<Wire> wires = new ArrayList<>(); //Liste aller Wires die gesetzt werden
    public static ArrayList<Wire> visiblewires = new ArrayList<>(); //Liste aller Sichtbaren Wires
    public static Wire movingwires = null; //Liste aller Wires die bewegt werden //Die erste ist die "master moving wire"
    public static ArrayList<WireNode> wireNodes = new ArrayList<>(); //Liste aller gesetzer Nodes
    public static ArrayList<WireNode> visibleWireNodes = new ArrayList<>(); //Liste aller sichtbaren Nodes

    //TODO Für multi select hier eventuel ArrayList
    public static Block markedblock = null; // Welcher Block ist makiert ?

    public static Vector2 mousepressedold = new Vector2(1, 1); //Wo war die Maus als das letzte mal eine Taste gedrückt wurde

    public static Vector2 unterschiedsave = new Vector2(); //Unterschied zwischen Blöckem die scih überllappen

    public static boolean ismoving = false; //Wird ein Block bewegt

    public static boolean marked = false; //Ist gerade ein Block makiert?

    public static ArrayList<Block> uberlapptmitmarkedblock = new ArrayList<Block>(); //Mit welchen Blöcken überlappt der Aktuell ausgewählte block ?

    public static ArrayList<Block> showduplicat = new ArrayList<Block>(); //Bei welchen Blöcken wir das Duplicat angezeigt?

    public static Block biggestblock = null; // Welcher ist der Block der die größten überlappungen mit dem bewegten Block hat

    public static Block blockmitdergrostenuberlappungmitmarkiertemblock = null; //Welcher Block hat die größte überlappungsfläche mit dem makierten?

    public static boolean showleftdocker = false; //Wenn ein connector ausgewählt wird bieten sich die anderen Blöcke zum verbinden an

    public static Block connetor_offerd_hoverd_block = null; //Der Block über den man fährt welcher gehovert wird
    public static Block wire_beginn = null;  //um bei verbindungen über eine Node trotzdem noch den Nachbar erfahren zu können

    public static Wire mousehoveredwire = null;
}
