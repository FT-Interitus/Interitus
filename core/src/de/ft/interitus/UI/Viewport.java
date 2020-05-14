package de.ft.interitus.UI;

import com.badlogic.gdx.Input;
import de.ft.interitus.ProgrammingSpace;

import static com.badlogic.gdx.Gdx.input;

public class Viewport {

    public static void update() {
        if (input.isKeyJustPressed(Input.Keys.LEFT)) {
            ProgrammingSpace.cam.position.set(ProgrammingSpace.cam.position.x -= 20, ProgrammingSpace.cam.position.y, 0);
        }

        if (input.isKeyJustPressed(Input.Keys.RIGHT)) {
            ProgrammingSpace.cam.position.set(ProgrammingSpace.cam.position.x += 20, ProgrammingSpace.cam.position.y, 0);
        }

        if (input.isKeyJustPressed(Input.Keys.UP)) {
            ProgrammingSpace.cam.position.set(ProgrammingSpace.cam.position.x,ProgrammingSpace.cam.position.y += 20, 0);
        }

        if (input.isKeyJustPressed(Input.Keys.DOWN)) {
            ProgrammingSpace.cam.position.set(ProgrammingSpace.cam.position.x, ProgrammingSpace.cam.position.y -= 20, 0);
        }
    }
}
