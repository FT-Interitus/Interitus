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
import de.ft.robocontrol.utils.PositionSaver;

import java.util.ArrayList;

public class MainGame extends ApplicationAdapter {
	ShapeRenderer shapeRenderer;
	public static SpriteBatch batch;
	public static Texture img_block;
	public static Texture img_selected;

	public static ArrayList<Block> blocks = new ArrayList<Block>();

	public static OrthographicCamera cam;
	public static Viewport viewport;

 	//BlockUpdate bu[] = new BlockUpdate[0];

	@Override
	public void create () {
		shapeRenderer=new ShapeRenderer();
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		viewport = new ScreenViewport(cam);
		batch = new SpriteBatch();

		img_block=new Texture("block.png");
		img_selected=new Texture("block_selected.png");

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


for(int i=0;i<3;i=i+1) {
	blocks.add(new Block(i, i * 150, 100, 150, 30));
}
blocks.get(0).setRight(blocks.get(1));


		cam.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);


	}



	@Override
	public void render () {

		PositionSaver.save();


		System.out.println(Var.mousepressedold);
		//System.out.println(blocks.get(1).getLeft());
		cam.update();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(cam.combined);

/*
		shapeRenderer.setProjectionMatrix(cam.combined);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.end();

*/




		for(int i=0;i<blocks.size();i=i+1){
			batch.begin();
			blocks.get(i).draw(batch);
			batch.end();
			if(blocks.get(i).isMarked()){
				shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
				shapeRenderer.ellipse(blocks.get(i).getX(),blocks.get(i).getY(),10,10);
				shapeRenderer.end();
			}
			System.out.println(blocks.get(i).isMarked() + "  id: "+blocks.get(i).getIndex());
		}
		/*

		for(int b=0;b<blocks.size();b=b+1) {
			Block block = blocks.get(b);
			if(!blocks.get(b).blockupdate.toggle) {
				batch.draw(img_block, block.getX(), block.getY(), block.getW(), block.getH());
			}else{
				batch.draw(img_selected, block.getX(), block.getY(), block.getW(), block.getH());
			}

		}
		*/



	}


	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

		//blocks[0].delete();

		viewport.update(width, height);
	}


	@Override
	public void dispose () {
		batch.dispose();
		img_block.dispose();
	}
}
