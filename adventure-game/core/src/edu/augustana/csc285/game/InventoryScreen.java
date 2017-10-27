package edu.augustana.csc285.game;

import java.util.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import edu.augustana.csc285.game.datamodel.Item;
import edu.augustana.csc285.game.datamodel.Inventory;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * To create a Screen that shows the player's inventory
 * 
 * @author Dat Tran, Daniel Zwiener, Faisal Nawaz, Ryan Philp, Lars Harvey
 * 
 * 
 */
public class InventoryScreen implements Screen {
	public static final Skin DEFAULT_SKIN = new Skin(Gdx.files.internal("skin/defaultSkin/flat-earth-ui.json"));
	public static final Skin SCROLL_SKIN = new Skin(Gdx.files.internal("skin/Holo-dark-mdpi.json"));
	public static final Skin BACK_BUTTON_SKIN = new Skin(Gdx.files.internal("skin/menuSkin/flat-earth-ui.json"));

	private final int WIDTH_BUFFER = AdventureGame.GAME_SCREEN_WIDTH / 100;
	private final int HEIGHT_BUFFER = AdventureGame.GAME_SCREEN_HEIGHT / 100;
	private final AdventureGame game;
	private OrthographicCamera camera;
	private Stage stage;
	private Texture backgroundImage;

	public InventoryScreen(AdventureGame game) {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, AdventureGame.GAME_SCREEN_WIDTH, AdventureGame.GAME_SCREEN_HEIGHT);
		this.game = game;
		backgroundImage = new Texture("GameData/background.jpg");
		stage = new Stage(new ScreenViewport());
		BitmapFont inventoryFont = new BitmapFont(Gdx.files.internal("fonts/defaultFont.fnt"), false);
		BitmapFont titleFont = new BitmapFont(Gdx.files.internal("fonts/titleFont.fnt"), false);

		String inventory = "You have: \n" +
		game.manager.getPlayer().getInventory().getVisibleItemStringList();
		Label inventoryLabel = new Label(inventory, new
		Label.LabelStyle(inventoryFont, Color.BLACK));
		inventoryLabel.setWrap(true);
		Texture itemIcon = new Texture(Gdx.files.internal("GameData/icons/bible.jpg"));
		Image icon = new Image(itemIcon);
		Table inventoryTable = new Table();
		inventoryTable.setPosition(500, 500);
		inventoryTable.setSkin(DEFAULT_SKIN);
		
//		for (String str : game.manager.getPlayer().getInventory().getVisibleItemStringList()) {
//			Label label = new Label(str, DEFAULT_SKIN);
//			inventoryTable.add(label).row();
//			inventoryTable.add(icon).row();
//		}
		
		Label screenTitle = new Label("Inventory", new Label.LabelStyle(titleFont, Color.BLACK));
		screenTitle.setPosition((float) 0.4 * AdventureGame.GAME_SCREEN_WIDTH,
				(float) 0.8 * AdventureGame.GAME_SCREEN_HEIGHT);
		stage.addActor(screenTitle);

		ScrollPane scroll = new ScrollPane(inventoryLabel, SCROLL_SKIN);

//		ScrollPane scroll = new ScrollPane(inventoryTable, SCROLL_SKIN);
		scroll.setPosition((float) 0.45 * AdventureGame.GAME_SCREEN_WIDTH,
				(float) 0.3 * AdventureGame.GAME_SCREEN_HEIGHT);
		scroll.setSize(AdventureGame.GAME_SCREEN_HEIGHT - 200, 300);
		scroll.setScrollingDisabled(true, false);
		scroll.setFadeScrollBars(false);
		stage.addActor(scroll);

		Button backButton = new TextButton("Back", BACK_BUTTON_SKIN);
		backButton.addListener(new InputListener() {
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
		backButton.setSize(130, 45);
		backButton.setPosition(1000, 650);
		stage.addActor(backButton);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Go back to slide screen
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			game.setScreen(new SlideScreen(game));
			dispose();
		}
		game.batch.begin();
		// Draw background image
		game.batch.draw(backgroundImage, 0, 0, AdventureGame.GAME_SCREEN_WIDTH, AdventureGame.GAME_SCREEN_HEIGHT);

		game.batch.end();

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
		backgroundImage.dispose();
	}

}
