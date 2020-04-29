package de.ft.interitus.Block;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Frustum;
import com.badlogic.gdx.math.Vector2;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.ThreadManager;
import de.ft.interitus.data.user.changes.DataManager;
import de.ft.interitus.DisplayErrors;
import de.ft.interitus.loading.AssetLoader;
import de.ft.interitus.utils.CheckKollision;

/***
 *
 * @author Felix & Tim
 *
 *Hier werden die Blöcke erstellt die für die Programmmierung zuständig sind.
 * Nähere Informationen stehen in den Kommentaren oder im Github Wiki.
 *
 */

public class Block implements VisibleObjects{
    public boolean seted = true; //Ob der Block losgelassen wurde bzw ob der Block eine statische Position hat
    public boolean moved = false; // Ob der Block gerade mit der Maus bewegt wird
    Frustum camfr = ProgrammingSpace.cam.frustum; //getten der Camera werte um zu überprüfen ob der Block gerade sichtbar ist.
    private boolean marked = false; //Ob der Block gerade makiert ist
    private int x; //Die x Koordinate des Blocks
    private int y; //Die Y Koordinate des Blocks
    private int w; //Die Weite des Blocks
    private int h; //Die Höhe des Blocks
    private int index; //Der Index des Blocks (Der Gleiche wie im Array BlockVar.blocks)
    private boolean showdupulicate_rechts; //Ob das Duplicat rechts angezeigt werden soll d.h. ob der Block der gerade bewegt wird hier hin springen wird
    private boolean showdupulicate_links; //Ob das Duplicat links ...
    private int x_dup_rechts; // Die X Position des Duplicates
    private int x_dup_links; //Die Y Position des Duplicates  //Die Weite und Höhe ergeben sich aus der Block weite und Höhe
    private boolean moving = false; //Ob der Block gerade durch den Nutzer bewegt wird
    private BlockUpdate blockupdate; // Die Block update methode hier werden user actionen engegengenommen und verarbeitet
    private Block left = null; //Der rechte verbundene Block hier auf Null gesetzt, da zum erstell zeitpunkt noch kein Nachbar exsistiert
    private Block right = null; //Der linke verbundene Block hier auf Null gesetzt, da zum erstell zeitpunkt noch kein Nachbar exsistiert
    private Vector2 wireconnector_right =new Vector2(0,0); //Die rechte wire-Anschluss Position
    private Vector2 wireconnector_left =new Vector2(0,0); //Die linke wire-Anschluss Position
    private Wire wire_left = null; //linke verbundene Wire
    private Wire wire_right = null; //rechte verbunde Wire
    private BlockAttribute blockAttribute = new BlockAttribute();

    public Block(int index, int x, int y, int w, int h) { //Initzialisieren des Blocks
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.x_dup_rechts = this.x + this.w; //Duplicats positionen werden berechnet
        this.x_dup_links = this.x - this.w;//Duplicats positionen werden berechnet
        wireconnector_right.set(x+w,y+h/3);
        this.index = index;
        blockupdate = new BlockUpdate(this); //BlockUpdate Klasse wird initzilisieren

        if (this.isVisible()) { //Wenn der Block sichtbar ist...  //Das passiert deshalb weil nicht für nicht sichbare Blöcke ein Thread laufen muss
            blockupdate.start(); //...wird der updater gestartet
            BlockVar.visibleblocks.add(this); //und wird zum Array der sichtbaren Blöcke hinzugefüt
        } else {
            blockupdate.isrunning = false; //...wenn nicht dann nicht
        }
        ThreadManager.add(blockupdate, this); //Blockupdate wird zum Array der laufenden Threads hinzugefügt

    }


    @Override
    public boolean isVisible() {
        return camfr.boundsInFrustum(this.getX(), this.getY(), 0, this.getW(), this.getH(), 0); //Ist der Block im Camera bereich?
    }

    @Override
    public boolean amiablock() {
        return true;
    }

    @Override
    public boolean amiwirenode() {
        return false;
    }

    @Override
    public Block getblock() { //TODO bestimmen
        return this;
    }

    @Override
    public WireNode getwirenode() {
        return null;
    }

    @Override
    public float getX_entrance() {
        return getWireconnector_left().x;
    }

    @Override
    public float getY_entrance() {
        return getWireconnector_left().y;
    }

    @Override
    public float getW_entrance() {
        return 20;
    }

    @Override
    public float getH_entrance() {
        return 20;
    }

    @Override
    public float getX_exit() {
        return (int) this.getwireconnector_right().x;
    }

    @Override
    public float getY_exit() {
        return  (int) this.getwireconnector_right().y;
    }

    @Override
    public float getW_exit() {
        return 0;
    }

    @Override
    public float getH_exit() {
        return 0;
    }

    public BlockUpdate getBlockupdate() {
        return blockupdate; //Gibt den Blockupdater zurück
    }

    public int getX_dup_rechts() {
        return x_dup_rechts; //Gibt die X Position des rechten duplicates zurück
    }

    public int getX_dup_links() {
        return x_dup_links;  //Gibt die X Position des linken duplicates zurück
    }

    public boolean isMoving() {
        return moving; //Gibt zurück ob der Block gerade in Bewegung ist
    }

    public void setMoving(boolean moving) {
        this.moving = moving; //Gibt an das der Block gerade in Bewegung ist
    }

    public boolean isShowdupulicate_rechts() {
        return showdupulicate_rechts; //Wird das rechte duplicat angezeigt?
    }

    public void setShowdupulicate_rechts(boolean showdupulicate_rechts) {
        this.showdupulicate_rechts = showdupulicate_rechts; //Das Rechte duplicat wird angzeigt oder nicht
    }

    public boolean isShowdupulicate_links() {
        return showdupulicate_links; //Wird das linke duplicat angezeigt?
    }

    public void setShowdupulicate_links(boolean showdupulicate_links) {
        this.showdupulicate_links = showdupulicate_links; //Das linke Duplicat Status soll geändert werden
    }

    public boolean isMarked() {
        return marked; //Ist der Block von User makiert worden?
    }

    public void setMarked(boolean marked) {

        this.marked = marked; //Soll der Block makiert sein?
    }

    @Override
    public void setWire_left(Wire wire_left) {
        this.wire_left = wire_left;
    }
    @Override
    public void setWire_right(Wire wire_right) {
        this.wire_right = wire_right;
    }

    public Wire getWire_left() {
        return wire_left;
    }

    public Wire getWire_right() {
        return wire_right;
    }

    public Block getLeft() {
        return left; //Gibt den linken VERBUNDENEN Nachbar zurück
    }

    public void setLeft(Block left) { //Setzt einen neuen linken nachbar


        if (this.left != left) { //Wenn der linke Nachbar nicht der schon der Gleiche Block ist (Sonst tritt hier ein OverFlow auf siehe set Right)
            this.left = left;      //Block wird als Nachbar aufgenommen
        }
        if (left != null) { //Wenn der Block nicht null ist...
            if (left.getRight() != this) { //Und der wenn der Rechte Nachbar vom linken Block nicht man selbst ist
                left.setRight(this); //Wird auch diese Verbindung neu gesetzt (Um Nachbarsetzten zu erleichtern (Aus der Schlussfolgerung das der Rechte Nachbar vom linken Nachbar man selbst ist) )
            }
        }

    }

    public Block getRight() {
        return right; //Rück gabe des rechts VERBUNDENEN Nachbars
    }

    public void setRight(Block right) {

        if (this.right != right) { //Wenn der rechte Nachbar nicht der schon der Gleiche Block ist (Sonst tritt hier ein OverFlow auf siehe set Left)
            this.right = right;//Block wird als Nachbar aufgenommen
        }
        if (right != null) {//Wenn der Block nicht null ist..
            if (right.getLeft() != this) {//Und der wenn der linke Nachbar vom rechten Block nicht man selbst ist
                right.setLeft(this);//Wird auch diese Verbindung neu gesetzt (Um Nachbarsetzten zu erleichtern (Aus der Schlussfolgerung das der linke Nachbar vom rechten Nachbar man selbst ist))
            }
        }

    }

    public int getX() {
        return x; //Rückgabe der X Position des eigenen Blockes
    }

    public void setX(int x) {
        this.x = x; //Die X Position wird geupdated
        this.x_dup_rechts = this.x + this.w; //Im gleichen Zug werden auch die beiden Duplikate auf ihre neue Poisition gesetzt
        this.x_dup_links = this.x - this.w;
    }

    public int getY() {
        return y; //Rückgabe der Y Position des eigenen Blockes
    }

    public void setY(int y) {
        this.y = y; //Setzen der Y Position

        //Hinweis die Y Position der Duplikate muss nicht gesetzt werden da die die geleichen wie der Block selbst haben

    }

    public int getH() {
        return h; //Die höhe wird ausgegeben
    }

    public int getW() {
        return w; //Die Weite wird ausgegeben
    }

    public void setPosition(int x, int y) { //X und Y werden aufeinmal gesetzt
        this.x = x;
        this.y = y;
        this.x_dup_rechts = this.x + this.w;
        this.x_dup_links = this.x - this.w;
    }


    public void setWH(int w, int h) { //Höhe und Weite werden auf einmal gesetzt
        this.w = w;
        this.h = h;
    }

    public int getIndex() { //Der Index wird ausgegeben
        return index;
    }

    public void setIndex(int index) { //Der Index wird gesetzt WARNUNG nur aufrufen in Kombination mit einer BlockVar.blocks Array aktion
        this.index = index;
    }

    public void delete(boolean complete) { //Der Block soll gelöscht werden (complete beduetet das alle Blöcke gelöscht werden sollen)
        BlockVar.markedblock = null; //Der Makierte Block wird auf null gesetzt da nur ein makierter block gelöscht werden kann //Anmerkung falls das ganze Programm gelöscht wird spielt das sowieso keine Rolle
        BlockVar.marked = false; //Ob ein Block makiert ist wird auf false gesetzt da nur ein makierter Block gelöscht werden kann
        BlockVar.ismoving = false; // Ob ein Block bewegt wird, wird auf false gesetzt da wenn ein Block bewegt und gelöscht wird kann es nur der bewegte Block sein


        if(this.getWire_left()!=null)  {
            this.getWire_left().setRight_connection(null);
        }

        if(this.getWire_right()!=null)  {
            this.getWire_right().setLeft_connection(null);
        }

        final int temp = this.getIndex(); // Der Index des Blocks wird in eine Temp Variable verlegt, da er später nochmal gebraucht wird
        DataManager.change(this, false, true); // Ein Block Abbild wird erstellt um ein eventuelles Rückgänig machen
        this.setIndex(-1); //Der Index wird auf -1 gesetzt dann merkt der BlockUpdater das der laufenden Timer beendet werden soll
        if (left != null) { //Wenn ein linker Nachbar exsistiert
            left.setRight(null); //wird dem linken Nachbar gesagt das er keinen Rechten Nachbar mehr hat

            try {
                left.setWire_right(null); //Die Wire des nachbar block wird gelöscht damit die Wire kein zweites mal gelöscht wird (passiert nur bei ganz vielen wire nodes)
            }catch (NullPointerException e) {
                //Falls es gar keine Wire gab
            }
        }

        if (right != null) { // wenn ein Rechter nachbar exsitiert
            right.setLeft(null); // wird dem rechten Nachbar gesagt das er keinen linken nachbar mehr hat

            try {
                right.setWire_left(null); //Die Wire des nachbar block wird gelöscht damit die Wire kein zweites mal gelöscht wird (passiert nur bei ganz vielen wire nodes)
            }catch (NullPointerException e) {
                //Falls es gar keine Wire gab
            }
        }


        left = null; //Die Referenzierung zum linken Nachbar wird gelöscht
        right = null; //Die Referenzierung zum rechten Nachbar wird gelöscht

        if (ThreadManager.threads.indexOf(this.blockupdate) != -1) { //Überprüfen ob Thread überhaupt läuft
            ThreadManager.threads.remove(this.blockupdate); //Wenn ja wird er aus dem Array der Threads entfernt
        }


        try {

            blockupdate.time.cancel(); //Der Blockupdate timer wird beendet
            blockupdate.interrupt(); // Der Thread wird interrupted
            blockupdate.block = null; //Die referenzierung des Threads BLocks wird getrennt
        } catch (Exception e) {
            DisplayErrors.error = e;
            e.printStackTrace();

        }

        if (!complete) { //das trifft nur nicht zu wenn das ganze programm gecleart wird
            BlockVar.blocks.remove(this); //Der block wird aus dem Blocks Array entfernt
            BlockVar.visibleblocks.remove(this); //Der block wird aus dem Visible Blocks Array entfernt


            Thread calcnew = new Thread() { //Da dies relativ lange dauert dauert in einem eigenen Thread
                @Override
                public void run() {
                    for (int i = temp; i < BlockVar.blocks.size(); i++) { //Durch alle Indexe des Block Arrays wird durchgegangen alle die einen größeren Index haben //Durch die Temp variable kann der alte Index des Blocks hier wieder verwendet werden
                        try {
                            BlockVar.blocks.get(i).setIndex(BlockVar.blocks.get(i).getIndex() - 1); //Alle anderen Blöcke werden um einen Index verschoben
                        }catch (Exception e) {
                            DisplayErrors.error = e;
                            e.printStackTrace(); //Hier können Fehler aufreten wenn die Blöcke in der zwischen Zeit gelööscht worden sind //TODO mit 1000000 Blöcke ausprobieren ob hier die Indexe richtig sich verhalten wenn man 2 blöcke löscht bevor das hier abgschlossen ist
                        }

                    }
                }
            };

            calcnew.start(); //Der Thread wird gestarted


            try {
                BlockVar.blocks.remove(this); //Der Block entfernet sich selbst aus dem Blocks Array
            } catch (Exception e) {

            }
        }

    }


    public boolean getmousecollision() {
        return blockupdate.toggle;
    }

    public void draw(SpriteBatch batch, ShapeRenderer shape, BitmapFont font) {
        batch.setColor(1,1,1,1);




        if (!this.blockupdate.toggle) {
            batch.draw(AssetLoader.img_block, this.getX(), this.getY(), this.getW(), this.getH()); // Block ohne das er makiert ist
        } else {
            batch.draw(AssetLoader.img_block_mouseover, this.getX(), this.getY(), this.getW(), this.getH()); // Block wenn er makiert ist
        }

        if (this.isMarked()) {
            batch.draw(AssetLoader.img_marked, this.getX(), this.getY(), this.getW(), this.getH()); // Wenn der Block makiert ist
        }
        if (BlockVar.biggestblock == this) {
            if (this.isShowdupulicate_rechts()) {
                batch.setColor(1, 1, 1, 0.5f);
                batch.draw(AssetLoader.img_block, this.x_dup_rechts, this.y, this.getW(), this.getH()); //Wenn der Block die größte überlappung hat wird er als show duplicat angezigt
                batch.setColor(1, 1, 1, 1);
            }

            if (this.isShowdupulicate_links()) {
                batch.setColor(1, 1, 1, 0.5f);
                batch.draw(AssetLoader.img_block, this.x_dup_links, this.y, this.getW(), this.getH()); //das gleiche für links
                batch.setColor(1, 1, 1, 1);
            }
        }

        if (this.getLeft() != null) { //Verbindungs marke ob der Nachbar verbunden ist
            batch.end();
            shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.setColor(1f, 0.4f, 0.4f, 0.4f);
            shape.ellipse(this.getX() - 6, this.getY() + this.getH() / 2 - 6, 12, 12);  //TODO nicht relativ
            shape.end();
            batch.begin();
        }

        if (this.getRight() != null) {//Verbindungs marke ob der Nachbar verbunden ist
            batch.end();
            shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.setColor(1f, 0.4f, 0.4f, 0.4f);
            shape.ellipse(this.getX() - 6 + this.getW(), this.getY() + this.getH() / 2 - 6, 12, 12);
            shape.end();
            batch.begin();
        }
        if (this == BlockVar.biggestblock) { //DEBUG Wer ist der größte Block?
            batch.end();
            shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.rect(x, y, 20, 20);
            shape.end();
            batch.begin();
        }

        if(!this.blockupdate.isIsconnectorclicked()&&BlockVar.showleftdocker&&this.getLeft()==null) {
            batch.draw(AssetLoader.connector_offerd,getWireconnector_left().x,getWireconnector_left().y,20,20);
        }

        if(this.getRight() ==null) {
            batch.draw(AssetLoader.connector, getwireconnector_right().x, getwireconnector_right().y, 20, 20);
        }

        font.draw(batch, "index:  " + this.getIndex(), this.getX() + 30, this.getY() + 30); //DEBUG Block Index auf dem Block anzeigen
    }

    public Vector2 getwireconnector_right() {
        wireconnector_right.set(x+w-15,y+h/3);
     return wireconnector_right;
    }

    public Vector2 getWireconnector_left() {
        wireconnector_left.set(x-5,y+h/3);
        return wireconnector_left;
    }

    public int getDublicatmarkedblockuberlappungsflache() {
        int flaeche = 0;
        if (this.isShowdupulicate_rechts()) { //Für rechts

            try {

                flaeche = (CheckKollision.flache(this.getX_dup_rechts(), this.getY(), this.getW(), this.getH(), BlockVar.markedblock.getX(), BlockVar.markedblock.getY())); //Fläsche mit der die Blöcke überlappen um zu brechenen an welchen Block der Block springen wird

            } catch (NullPointerException ignored) {

            }


        }


        if (this.isShowdupulicate_links()) { //Für links
            try {

                flaeche = (CheckKollision.flache(this.getX_dup_links(), this.getY(), this.getW(), this.getH(), BlockVar.markedblock.getX(), BlockVar.markedblock.getY()));//Fläsche mit der die Blöcke überlappen um zu brechenen an welchen Block der Block springen wird

            } catch (NullPointerException ignored) {

            }

        }
        return flaeche;
    }

    public int getBlockMarkedblockuberlappungsflache() {
        int flaeche = 0;
        try {
            flaeche = CheckKollision.flache(this.getX(), this.getY(), this.getW(), this.getH(), BlockVar.markedblock.getX(), BlockVar.markedblock.getY()); //Fläche des Markierten Blocks
        } catch (NullPointerException e) {
        }
        return flaeche;
    }

    public Thread allowedRestart() { //WARNUNG diese Methode darf nur von ThreadMananger aufgerufen werden
        blockupdate = new BlockUpdate(this); //Wenn der Block wieder in den Sichtbereich Rückt
        blockupdate.start(); //             ...wird der update Thread gestarted

        return blockupdate;
    }




}
