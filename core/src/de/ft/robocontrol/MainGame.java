package de.ft.robocontrol;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.ft.robocontrol.Block.Block;
import de.ft.robocontrol.Block.BlockVar;
import de.ft.robocontrol.UI.UI;
import de.ft.robocontrol.data.programm.Data;
import de.ft.robocontrol.data.programm.DataManager;
import de.ft.robocontrol.utils.PositionSaver;

import java.awt.Component;

import java.util.ArrayList;

import static com.badlogic.gdx.Gdx.input;

public class MainGame extends ApplicationAdapter {
	ShapeRenderer shapeRenderer;
	public static SpriteBatch batch;
	public static Texture img_block;
	public static Texture img_mouseover;
	public static Texture img_marked;

	

	public static OrthographicCamera cam;
	public static Viewport viewport;
	public static Component saver;



	//BlockUpdate bu[] = new BlockUpdate[0];
public static Logger logger;

	@Override
	public void create () {
		shapeRenderer=new ShapeRenderer();
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		viewport = new ScreenViewport(cam);
		batch = new SpriteBatch();

		img_block=new Texture("block.png");
		img_mouseover=new Texture("block_mouseover.png");
		img_marked=new Texture("block_marked.png");



		logger = new Logger("MainLog", 0);

		Gdx.graphics.setTitle("New File");
		DataManager.filename = "New File";

//Achtung hier ist die Reihenfolge richtig
		Data.init();
		UI.init();



		/*
		for(int i=0;i < BlockVar.blocks.length;i=i+1){
			System.out.println("eine runde"+i);
			BlockVar.blocks[i]=new Block(i,i*100,100,100,30);
			bu[i]=new BlockUpdate(BlockVar.blocks[i]);
			bu[i].start();
			//BlockVar.blocks[i].setWH(100,30);
			//BlockVar.blocks[i].setPosition(i*BlockVar.blocks[i].getW(),100);

		}
		 */


for(int i=0;i<1;i=i+1) {
	BlockVar.blocks.add(new Block(i, i * 150, 100, 150, 70));


}
//BlockVar.blocks.get(0).setRight(BlockVar.blocks.get(1));


		cam.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);






	}




	@Override
	public void render () {


		PositionSaver.save();




		//System.out.println(Var.mousepressedold);
		//System.out.println(BlockVar.blocks.get(1).getLeft());
		cam.update();

		//Gdx.gl.glClearColor(1,1,1, 1);
		Gdx.gl.glClearColor(1,0,0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(cam.combined);


/*
		shapeRenderer.setProjectionMatrix(cam.combined);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.end();

*/

		if(input.isKeyJustPressed(Input.Keys.INSERT)){
			BlockVar.blocks.add(new Block(BlockVar.blocks.size(), 100, 200, 150, 70));
			DataManager.change();
		}
		UI.updatedragui(shapeRenderer);

if(!Var.isloading) {
	Block Temp = null;
	for (int i = 0; i < BlockVar.blocks.size(); i = i + 1) {
		batch.begin();


		if (BlockVar.blocks.get(i).isMarked()) {
			Temp = BlockVar.blocks.get(i);
		} else {
			BlockVar.blocks.get(i).draw(batch,shapeRenderer);
		}

		batch.end();
		if (BlockVar.blocks.get(i).isMarked()) {


			if (input.isKeyJustPressed(Input.Keys.FORWARD_DEL)) {
				BlockVar.blocks.get(i).delete();
			}
			if(input.isKeyJustPressed(Input.Keys.SPACE)) {
				cam.position.set(cam.position.x=+5,cam.position.y+=5, 0);
			}


		}


		if (Temp != null) {

			batch.begin();
			Temp.draw(batch,shapeRenderer);
			batch.end();
		}
		//System.out.println(BlockVar.blocks.get(i).isMarked() + "  id: "+BlockVar.blocks.get(i).getIndex());

	}
}
		/*

		for(int b=0;b<BlockVar.blocks.size();b=b+1) {
			Block block = BlockVar.blocks.get(b);
			if(!BlockVar.blocks.get(b).blockupdate.toggle) {
				batch.draw(img_block, block.getX(), block.getY(), block.getW(), block.getH());
			}else{
				batch.draw(img_selected, block.getX(), block.getY(), block.getW(), block.getH());
			}

		}
		*/

		UI.update();


	}


	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

		//BlockVar.blocks[0].delete();
		UI.updateView(width, height);
		viewport.update(width, height);


	}



	@Override
	public void dispose () {
		
		for(int i = 0;i<BlockVar.blocks.size();i++) {
			BlockVar.blocks.get(i).delete();
		}
		
		BlockVar.blocks.clear();


		
		Data.close();
		batch.dispose();
		img_block.dispose();
		Gdx.app.exit();
		System.exit(0);
	}
}
