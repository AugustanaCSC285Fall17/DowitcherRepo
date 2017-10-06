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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import edu.augustana.csc285.game.datamodel.Inventory;
import edu.augustana.csc285.game.datamodel.Item;
import edu.augustana.csc285.game.datamodel.Player;

/**
 * To create a Screen that shows the player's inventory
 * 
 * @author Dat Tran, Daniel Zwiener, Faisal Nawaz, Ryan Philp
 * 
 * 
 */
public class InventoryScreen implements Screen {
	public static final Skin DEFAULT_SKIN = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));
	private final int WIDTH_BUFFER = AdventureGame.GAME_SCREEN_WIDTH / 100;
	private final int HEIGHT_BUFFER = AdventureGame.GAME_SCREEN_HEIGHT / 100;
	private final AdventureGame game;
	private OrthographicCamera camera;
	private Stage stage;

	public InventoryScreen(AdventureGame game) {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, AdventureGame.GAME_SCREEN_WIDTH, AdventureGame.GAME_SCREEN_HEIGHT);
		this.game = game;
		stage = new Stage(new ScreenViewport());
		HashMap<String, Item> inventoryCollection = game.manager.getPlayer().getInventory().getCollection();
		String inventory = "You have: \n";
		for (Item item : inventoryCollection.values()) {
			inventory += item.getName() + " x " + item.getQuantity() + "\n";
		}
		Label label = new Label(inventory,DEFAULT_SKIN);
		label.setWrap(true);
		ScrollPane scroll = new ScrollPane(label, DEFAULT_SKIN);
		scroll.setPosition(20,20);
		scroll.setSize(300,300);
		scroll.setScrollingDisabled(true, false);
		stage.addActor(scroll);

		Button button = new TextButton("Back", DEFAULT_SKIN);
		button.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new SlideScreen(game));
				dispose();
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

		});
		button.setSize(120, 35);
		button.setPosition(660, 430);
		stage.addActor(button);
		
		
//
//		Label description = new Label(slide.getDesc(), DEFAULT_SKIN);
//		description.setWrap(true);
//		ScrollPane scroll = new ScrollPane(description, DEFAULT_SKIN);
//		scroll.setPosition((WIDTH_BUFFER * 2) + (23 * AdventureGame.GAME_SCREEN_HEIGHT) / 32, 
//						AdventureGame.GAME_SCREEN_HEIGHT - (HEIGHT_BUFFER * 2) - (AdventureGame.GAME_SCREEN_HEIGHT / 10) - ((3 * AdventureGame.GAME_SCREEN_HEIGHT) / 8));
//		scroll.setSize(AdventureGame.GAME_SCREEN_WIDTH / 2, AdventureGame.GAME_SCREEN_HEIGHT / 3);
//		scroll.setScrollingDisabled(true, false);
//		stage.addActor(scroll);
//
//		Table table = new Table();
//		Table container = new Table();
//		container.setBounds(10, 10, 400, 400);
//		ScrollPane scroll = new ScrollPane(table);
//		container.add(scroll);
//		container.row();
//		container.debug();
//		table.debug();
//		for (Item item : inventoryCollection.values()) {
//			Label label = new Label(item.getName() + " x " + item.getQuantity(), DEFAULT_SKIN);
//			label.setAlignment(Align.right);
//			label.setWrap(true);
//			table.add(label);
//			table.row();
//		}
//		stage.addActor(container);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		stage.act();
		stage.draw();
		game.batch.begin();
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		// camera.
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
