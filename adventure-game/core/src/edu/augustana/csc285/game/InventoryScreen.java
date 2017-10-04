package edu.augustana.csc285.game;

import java.util.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import edu.augustana.csc285.game.datamodel.Inventory;
import edu.augustana.csc285.game.datamodel.Item;
import edu.augustana.csc285.game.datamodel.Player;


/**
 * To create a Screen that shows the player's inventory
 * 
 * @author Daniel Zwiener, Faisal Nawaz, Ryan Philp
 * 
 * 
 */
public class InventoryScreen implements Screen{

	private final AdventureGame game;
	private OrthographicCamera camera;
	private Stage stage;
	
	public InventoryScreen(AdventureGame game) {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, AdventureGame.GAME_SCREEN_WIDTH, AdventureGame.GAME_SCREEN_HEIGHT);
		this.game = game;
		Map<Integer,Item> inventoryCollection = game.manager.getPlayer().getInventory().getCollection();
		
		Stage stage = new Stage(new ScreenViewport());
		Table table = new Table();
		Table container = new Table();
		ScrollPane scroll = new ScrollPane(table);
	    container.add(scroll).width(500f).height(500f);
	    container.row();
	    
	    LabelStyle style = new LabelStyle(game.font,Color.WHITE);
		
		for(Item item:inventoryCollection.values()) {
			Label label = new Label(item.getName() + " x " + item.getQuantity(),style);
			table.add(label);
			table.row();
			label.setAlignment(Align.left);
		}
		stage.addActor(container);
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
		game.batch.begin();
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		//camera.
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
	
}
