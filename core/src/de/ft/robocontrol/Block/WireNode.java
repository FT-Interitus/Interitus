package de.ft.robocontrol.Block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Frustum;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import de.ft.robocontrol.ProgrammingSpace;
import de.ft.robocontrol.input.check.Check;
import de.ft.robocontrol.loading.AssetLoader;
import de.ft.robocontrol.utils.CheckKollision;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class WireNode implements VisibleObjects {
    private Wire wire_left;
    private Wire wire_right;
    private int x;
    private int y;
    private int w;
    private int h;
    private boolean gemerkt=false;
    private Vector2 gemerktvector=new Vector2(0,0);
    private Frustum frustum;
    public WireNode(Wire wiresleft,int x,int y,int w,int h) { //Wenn eine neue Node gesetzt wird

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.wire_left = wiresleft;

    }

    public WireNode(Wire wire_left,Wire wire_right) {

    }

    public void draw() {
      //Hier Thread für Maus bewegen
        if(CheckKollision.checkmousewithobject(x-5,y-5,h,w,(int)ProgrammingSpace.cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).x,(int)ProgrammingSpace.cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).y)&&Gdx.input.isButtonPressed(0)) {
            //TODO hier darf felix die die X und Y pos auf die MausPosition verlagern
                System.out.println("drinnen");
            if(!gemerkt){
                gemerktvector.set(Gdx.input.getX()-x,Gdx.input.getY()-y);
           System.out.println("gemerktvector: : :   "+ gemerktvector);
           gemerkt=true;
            }


        }

        if(!Gdx.input.isButtonPressed(0)){gemerkt=false;}

        if(gemerkt){
            x=(int)(Gdx.input.getX()+gemerktvector.x);
            y=(int)(Gdx.input.getY()+gemerktvector.y);
        }


       boolean temp = false;
       if(!ProgrammingSpace.batch.isDrawing()) {
           ProgrammingSpace.batch.begin();
           temp = true;
       }


       ProgrammingSpace.batch.draw(AssetLoader.wire_node,x-5,y-5,w,h);


       if(temp) {
           ProgrammingSpace.batch.end();
       }

      if(wire_left==null||wire_right==null) {
          BlockVar.wireNodes.remove(this);
          BlockVar.visibleWireNodes.remove(this);

          if(wire_right!=null) {
              wire_right.setLeft_connection(null);
          }
          if(wire_left!=null) {
              wire_left.setRight_connection(null);


          }

      }

    }

    public Wire getWire_right() {
        return wire_right;
    }

    public Wire getWire_left() {
        return wire_left;
    }

    @Override
    public void setWire_right(Wire wire_right) {
        this.wire_right = wire_right;
    }

    @Override
    public void setWire_left(Wire wire_left) {
        this.wire_left = wire_left;
    }

    @Override
    public boolean isVisible() {
        frustum = ProgrammingSpace.cam.frustum;
       return frustum.boundsInFrustum(x,y,0,w,h,0);

    }

    @Override
    public boolean amiablock() {
        return false;
    }

    @Override
    public boolean amiwirenode() {
        return true;
    }

    @Override
    public Block getblock() {
        return null;
    }

    @Override
    public WireNode getwirenode() {
        return this;
    }

    @Override
    public float getX_entrance() {
        return x;
    }

    @Override
    public float getY_entrance() {
        return y;
    }

    @Override
    public float getW_entrance() {
        return w;
    }

    @Override
    public float getH_entrance() {
        return h;
    }

    @Override
    public float getX_exit() {
        return x;
    }

    @Override
    public float getY_exit() {
        return y;
    }

    @Override
    public float getW_exit() {
        return w;
    }

    @Override
    public float getH_exit() {
        return h;
    }




}
