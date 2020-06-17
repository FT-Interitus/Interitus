package de.ft.interitus.UI.UIElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.ft.interitus.UI.UIElements.check.Check;
import de.ft.interitus.UI.UIElements.check.InputManager;

public class TextField {

    private static final GlyphLayout glyphLayout = new GlyphLayout();
    SpriteBatch b = new SpriteBatch();
    BitmapFont font = new BitmapFont();
    Texture background;
    Texture Backgroundactive;
    Check check = new Check();
    Texture curser;
    private final int x;
    private final int y;
    private final int w;
    private final int h;
    private boolean active = false;
    private String input = "default";
    private final int begrenzung = -1;
    private boolean cursorstate = true;
    private long cursersave;
    private int TextAnordnung = 0;

    public TextField(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        background = new Texture("TextFieldBackground.png");
        Backgroundactive = new Texture("TextFieldBackgroundActive.png");
        curser = new Texture("curser.png");


        InputManager.addProcessor(new InputAdapter() {
            @Override
            public boolean keyTyped(char key) {
                if (active) {

                    if (input.length() <= begrenzung || begrenzung == -1) {
                        input = input + key;
                    }
                }
                return false;
            }

            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {

                return false;
            }
        });
        InputManager.updateMultiplexer();
    }


    private void active() {
        if (check.isjustPressed(x, y, w, h)) {
            active = !active;
        }
        if (!check.isMouseover(x, y, w, h) && Gdx.input.isButtonPressed(0)) {
            active = false;
        }
    }

    public void draw() {
        active();


        if (active) {

            if (Gdx.input.isKeyPressed(Input.Keys.DEL) && input.length() > 0) { //TODO zu schnell löschen

                input = input.substring(0, input.length() - 1);
            }
        }


        b.begin();
        if (active) {
            b.draw(Backgroundactive, x, y, w, h);

        } else {
            b.draw(background, x, y, w, h);

        }
        //  font.draw(b, input, x+5, y + font.getLineHeight());


        switch (TextAnordnung) {
            case 0:
                glyphLayout.setText(font, input);
                font.draw(b, glyphLayout, x + 5, y + glyphLayout.height + h / 2 - glyphLayout.height / 2);


                if (active && cursorstate) {
                    b.draw(curser, x + glyphLayout.width + 7, y + 4, 2, 17);
                }

                break;
            case 1:
                glyphLayout.setText(font, input);
                font.draw(b, glyphLayout, x + w / 2 - glyphLayout.width / 2, y + glyphLayout.height + h / 2 - glyphLayout.height / 2);


                if (active && cursorstate) {
                    b.draw(curser, x + w / 2 + glyphLayout.width / 2 + 2, y + 4, 2, 17);
                }
                break;
        }

        b.end();


        if (System.currentTimeMillis() > cursersave) {
            cursorstate = !cursorstate;
            cursersave = System.currentTimeMillis() + 500;
        }


    }

    public int getTextAnordnung() {
        return TextAnordnung;
    }

    public void setTextAnordnung(int textAnordnung) {
        TextAnordnung = textAnordnung;
    }
}
