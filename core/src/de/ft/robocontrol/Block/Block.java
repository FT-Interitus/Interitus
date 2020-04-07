package de.ft.robocontrol.Block;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Frustum;
import de.ft.robocontrol.ProgrammingSpace;
import de.ft.robocontrol.ThreadManager;
import de.ft.robocontrol.data.user.changes.DataManager;
import de.ft.robocontrol.loading.AssetLoader;
import de.ft.robocontrol.utils.CheckKollision;


/***
 *
 * @author Felix & Tim
 *
 *Hier werden die Blöcke erstellt die für die Programmmierung zuständig sind
 *
 */


public class Block {
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

    public Block(int index, int x, int y, int w, int h) { //Initzialisieren des Blocks
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.x_dup_rechts = this.x + this.w; //Duplicats positionen werden berechnet
        this.x_dup_links = this.x - this.w;//Duplicats positionen werden berechnet

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

    public boolean isVisible() {
        return camfr.boundsInFrustum(this.getX(), this.getY(), 0, this.getW(), this.getH(), 0); //Ist der Block im Camera bereich?
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

    public void delete(boolean complete) { //Der Block soll gelöscht werden
        BlockVar.markedblock = null; //Der Makierte Block wird auf null gesetzt da nur ein makierter block gelöscht werden kann //Anmerkung falls das ganze Programm gelöscht wird spielt das sowieso keine Rolle
        BlockVar.marked = false; //Ob ein Block makiert ist wird auf false gesetzt da nur ein makierter Block gelöscht werden kann
        BlockVar.ismoving = false; // Ob ein Block bewegt wird, wird auf false gesetzt da wenn ein Block bewegt und gelöscht wird kann es nur der bewegte Block sein


        final int temp = this.getIndex(); // Der Index des Blocks wird in eine Temp Variable verlegt, da er später nochmal gebraucht wird
        DataManager.change(this, false, true); // Ein Block Abbild wird erstellt um ein eventuelles Rückgänig machen
        this.setIndex(-1); //Der Index wird auf -1 gesetzt dann merkt der BlockUpdater das der laufenden Timer beendet werden soll
        if (left != null) { //Wenn ein linker Nachbar exsistiert
            left.setRight(null); //wird dem linken Nachbar gesagt das er keinen Rechten Nachbar mehr hat
        }

        if (right != null) { // wenn ein Rechter nachbar exsitiert
            right.setLeft(null); // wird dem rechten Nachbar gesagt das er keinen linken nachbar mehr hat
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
           ProgrammingSpace.logger.severe(e.getMessage()); //Über den Logger werden eventuelle Exeptions ausgegeben

        }

        if (!complete) { //das trifft nur nicht zu wenn das ganze programm gecleart wird
            BlockVar.blocks.remove(this); //Der block wird aus dem Blocks Array entfernt
            BlockVar.visibleblocks.remove(this); //Der block wird aus dem Visible Blocks Array entfernt


            Thread calcnew = new Thread() { //Da dies relativ lange dauert dauert in einem eigenen Thread
                @Override
                public void run() {
                    for (int i = temp; i < BlockVar.blocks.size(); i++) { //Durch alle Indexe des Block Arrays wird durchgegangen alle die einen größeren Index haben
                        try {
                            BlockVar.blocks.get(i).setIndex(BlockVar.blocks.get(i).getIndex() - 1); //Alle anderen Blöcke werden um einen Index verschoben
                        }catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            };

            calcnew.start(); //Der Thread wird gestarted


            try {
                BlockVar.blocks.remove(this);
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
            batch.draw(AssetLoader.img_block, this.getX(), this.getY(), this.getW(), this.getH());
        } else {
            batch.draw(AssetLoader.img_block_mouseover, this.getX(), this.getY(), this.getW(), this.getH());
        }

        if (this.isMarked()) {
            batch.draw(AssetLoader.img_marked, this.getX(), this.getY(), this.getW(), this.getH());
        }
        if (BlockVar.biggestblock == this) {
            if (this.isShowdupulicate_rechts()) {
                batch.setColor(1, 1, 1, 0.5f);
                batch.draw(AssetLoader.img_block, this.x_dup_rechts, this.y, this.getW(), this.getH());
                batch.setColor(1, 1, 1, 1);
            }

            if (this.isShowdupulicate_links()) {
                batch.setColor(1, 1, 1, 0.5f);
                batch.draw(AssetLoader.img_block, this.x_dup_links, this.y, this.getW(), this.getH());
                batch.setColor(1, 1, 1, 1);
            }
        }

        if (this.getLeft() != null) {
            batch.end();
            shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.setColor(1f, 0.4f, 0.4f, 0.4f);
            shape.ellipse(this.getX() - 6, this.getY() + this.getH() / 2 - 6, 12, 12);
            shape.end();
            batch.begin();
        }

        if (this.getRight() != null) {
            batch.end();
            shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.setColor(1f, 0.4f, 0.4f, 0.4f);
            shape.ellipse(this.getX() - 6 + this.getW(), this.getY() + this.getH() / 2 - 6, 12, 12);
            shape.end();
            batch.begin();
        }
        if (this == BlockVar.biggestblock) {
            batch.end();
            shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.rect(x, y, 20, 20);
            shape.end();
            batch.begin();
        }


        font.draw(batch, "index:  " + this.getIndex(), this.getX() + 30, this.getY() + 30);
    }


    public int getDublicatmarkedblockuberlappungsflache() {
        int flaeche = 0;
        if (this.isShowdupulicate_rechts()) {

            try {

                flaeche = (CheckKollision.flache(this.getX_dup_rechts(), this.getY(), this.getW(), this.getH(), BlockVar.markedblock.getX(), BlockVar.markedblock.getY()));

            } catch (NullPointerException ignored) {

            }


        }


        if (this.isShowdupulicate_links()) {
            try {

                flaeche = (CheckKollision.flache(this.getX_dup_links(), this.getY(), this.getW(), this.getH(), BlockVar.markedblock.getX(), BlockVar.markedblock.getY()));

            } catch (NullPointerException ignored) {

            }

        }
        return flaeche;
    }

    public int getBlockMarkedblockuberlappungsflache() {
        int flaeche = 0;
        try {
            flaeche = CheckKollision.flache(this.getX(), this.getY(), this.getW(), this.getH(), BlockVar.markedblock.getX(), BlockVar.markedblock.getY());
        } catch (NullPointerException e) {
        }
        return flaeche;
    }

    public Thread allowedRestart() {
        blockupdate = new BlockUpdate(this);
        blockupdate.start();

        return blockupdate;
    }


}
