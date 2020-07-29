package de.ft.interitus.Block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import de.ft.interitus.ProgrammingSpace;
import de.ft.interitus.UI.UI;
import de.ft.interitus.UI.UIElements.check.Check;
import de.ft.interitus.UI.UIVar;
import de.ft.interitus.UI.tappedbar.TapItem;
import de.ft.interitus.Var;
import de.ft.interitus.projecttypes.ProjectManager;
import de.ft.interitus.projecttypes.BlockTypes.PlatformSpecificBlock;


public class TapBarBlockItem implements TapItem {
    int x;
    int y;
    int w = 50;
    int h = 60;
    Texture img;
    PlatformSpecificBlock psb;
    Check check = new Check();

    public TapBarBlockItem(PlatformSpecificBlock psb, Texture img) {
        this.img = img;
        this.psb = psb;
    }

    @Override
    public void draw() {

        if (!Var.isdialogeopend) {
            if (check.isJustPressedNormal(x, y, w, h)) {

                Block tempblock = ProjectManager.getActProjectVar().projectType.getBlockGenerator().generateBlock(ProjectManager.getActProjectVar().blocks.size(), (int) ProgrammingSpace.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).x, (int) ProgrammingSpace.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).y, psb.getWidth(), UIVar.BlockHeight, psb, ProjectManager.getActProjectVar().projectType.getBlockUpdateGenerator(), ProjectManager.getActProjectVar().projectType.getBlocktoSaveGenerator());

                ProjectManager.getActProjectVar().markedblock.add(tempblock);
                ProjectManager.getActProjectVar().marked = true;

                ProjectManager.getActProjectVar().blocks.add(tempblock);


                tempblock.setMarked(true);
                tempblock.setMoving(true);

                ProjectManager.getActProjectVar().unterschiedsave.add(new Vector2(psb.getWidth() / 2, UIVar.BlockHeight / 2));
            }
        }

        UI.UIbatch.begin();
        UI.UIbatch.draw(img, this.x, this.y, this.w, this.h);
        UI.UIbatch.end();
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getW() {
        return w;
    }

    @Override
    public void setW(int w) {
        this.w = w;
    }

    @Override
    public int getH() {
        return h;
    }

    @Override
    public void setH(int h) {
        this.h = h;
    }


}
