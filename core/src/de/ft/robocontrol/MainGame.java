package de.ft.robocontrol;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainGame extends ApplicationAdapter {
	ShapeRenderer shapeRenderer;
	SpriteBatch batch;
	Texture img;
 public static Block[] blocks = new Block[10];
	public static OrthographicCamera cam;
	public static Viewport viewport;

 	//BlockUpdate bu[] = new BlockUpdate[0];

	@Override
	public void create () {
		shapeRenderer=new ShapeRenderer();
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		viewport = new ScreenViewport(cam);
		batch = new SpriteBatch();

		/*
		for(int i=0;i < blocks.length;i=i+1){
			System.out.println("eine runde"+i);
			blocks[i]=new Block(i,i*100,100,100,30);
			bu[i]=new BlockUpdate(blocks[i]);
			bu[i].start();
			//blocks[i].setWH(100,30);
			//blocks[i].setPosition(i*blocks[i].getW(),100);

		}
		 */
int i=0;
		blocks[i]=new Block(i,i*100,100,100,30);



		img = new Texture("badlogic.jpg");
		cam.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);


	}



	@Override
	public void render () {
		cam.update();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(cam.combined);
		shapeRenderer.setProjectionMatrix(cam.combined);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.rect(blocks[0].getX(),blocks[0].getY(),blocks[0].getW(),blocks[0].getH());
		shapeRenderer.end();
		batch.begin();

		batch.end();

	}


	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

		blocks[0].delete();

		viewport.update(width, height);
	}


	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
