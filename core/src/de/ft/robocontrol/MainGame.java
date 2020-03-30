package de.ft.robocontrol;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import de.ft.robocontrol.Block.Block;
import de.ft.robocontrol.Block.BlockVar;
import de.ft.robocontrol.UI.UI;
import de.ft.robocontrol.data.programm.Data;
import de.ft.robocontrol.data.user.changes.DataManager;
import de.ft.robocontrol.roboconnection.SerialConnection;
import de.ft.robocontrol.utils.PositionSaver;

import java.awt.Component;

import static com.badlogic.gdx.Gdx.input;

public class MainGame extends ApplicationAdapter {
	ShapeRenderer shapeRenderer;
	public static SpriteBatch UIbatch;
	BitmapFont font;
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
		font = new BitmapFont();
		shapeRenderer=new ShapeRenderer();
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		viewport = new ScreenViewport(cam);
		batch = new SpriteBatch();
		UIbatch = new SpriteBatch();

		img_block=new Texture("block.png");
		img_mouseover=new Texture("block_mouseover.png");
		img_marked=new Texture("block_marked.png");



		logger = new Logger("MainLog", 0);

		Gdx.graphics.setTitle("New File");
		DataManager.filename = "New File";

//Achtung hier ist die Reihenfolge richtig
		Data.init();
		UI.init();


		ThreadManager.init();

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

Thread test = new Thread() {
	@Override
	public void run() {
		for(int i=0;i<12;i=i+1) {
			BlockVar.blocks.add(new Block(i, i * 150, 100, 150, 70));

			System.out.println(i);
		}
	}
};


test.start();

//BlockVar.blocks.get(0).setRight(BlockVar.blocks.get(1));


		cam.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);




		SerialConnection.serial();
	}




	@Override
	public void render () {


		try {

			//System.out.println("Blöcke "+BlockVar.blocks.size()+" Sichtbare "+ BlockVar.visibleblocks.size());
		//	System.out.println("cam Pos"+ cam.position);
			PositionSaver.save();


			//System.out.println(Var.mousepressedold);
			//System.out.println(BlockVar.blocks.get(1).getLeft());
			cam.update();

			//Gdx.gl.glClearColor(1,1,1, 1);
			if (Settings.darkmode) {
				Gdx.gl.glClearColor(1, 0, 0, 1);
			} else {
				Gdx.gl.glClearColor(0.54f, 0.533f, 0.51f, 1);
			}
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.setProjectionMatrix(cam.combined);


/*
		shapeRenderer.setProjectionMatrix(cam.combined);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.end();

*/

			if (input.isKeyJustPressed(Input.Keys.INSERT)) {

				BlockVar.blocks.add(new Block(BlockVar.blocks.size(), 100, 200, 150, 70));
				DataManager.change(BlockVar.blocks.get(BlockVar.blocks.size() - 1), true, false);
			}
			UI.updatedragui(shapeRenderer, true);

			if (!Var.isloading) {
				Block Temp = null;
				for (int i = 0; i < BlockVar.visibleblocks.size(); i = i + 1) {
					try {
						batch.begin();
					}catch (IllegalStateException e) {
						batch.end();
						batch.begin();
					}

					try {
						if (BlockVar.visibleblocks.get(i).isMarked()) {
							Temp = BlockVar.visibleblocks.get(i);
						} else {
							BlockVar.visibleblocks.get(i).draw(batch, shapeRenderer, font);
						}

						batch.end();
						if (BlockVar.visibleblocks.get(i).isMarked()) {


							if (input.isKeyJustPressed(Input.Keys.FORWARD_DEL)) {
								BlockVar.visibleblocks.get(i).delete();
							}


						}



						if (Temp != null) {

							batch.begin();
							Temp.draw(batch, shapeRenderer, font);
							batch.end();
						}
						//System.out.println(BlockVar.blocks.get(i).isMarked() + "  id: "+BlockVar.blocks.get(i).getIndex());
					}catch (Exception e) {

					}
				}
			}

			UI.updatedragui(shapeRenderer, false);
			UI.update();
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
			if (input.isKeyJustPressed(Input.Keys.LEFT)) {
				cam.position.set(cam.position.x -= 20, cam.position.y, 0);
			}

			if (input.isKeyJustPressed(Input.Keys.RIGHT)) {
				cam.position.set(cam.position.x += 20, cam.position.y, 0);
			}

			if (input.isKeyJustPressed(Input.Keys.UP)) {
				cam.position.set(cam.position.x, cam.position.y += 20, 0);
			}

			if (input.isKeyJustPressed(Input.Keys.DOWN)) {
				cam.position.set(cam.position.x, cam.position.y -= 20, 0);
			}

				if(input.isKeyJustPressed(Input.Keys.E)) {
					throw new NullPointerException("Test");
				}



		}catch (Exception e) {
			e.printStackTrace();
		}


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
