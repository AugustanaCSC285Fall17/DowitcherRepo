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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
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
		
		String inventory = "You have: \n" + game.manager.getPlayer().getInventory().getVisibleItemString();
		Label inventoryLabel = new Label(inventory, new Label.LabelStyle(inventoryFont, Color.BLACK));
		inventoryLabel.setWrap(true);

		Label screenTitle = new Label("Inventory", new Label.LabelStyle(titleFont, Color.BLACK));
		screenTitle.setPosition((float)0.4 * AdventureGame.GAME_SCREEN_WIDTH, (float) 0.8 * AdventureGame.GAME_SCREEN_HEIGHT);
		stage.addActor(screenTitle);

		ScrollPane scroll = new ScrollPane(inventoryLabel, SCROLL_SKIN);
		scroll.setPosition((float) 0.45 * AdventureGame.GAME_SCREEN_WIDTH, (float) 0.3 * AdventureGame.GAME_SCREEN_HEIGHT);
		scroll.setSize(AdventureGame.GAME_SCREEN_HEIGHT - 200, 300);
		scroll.setScrollingDisabled(true, false);
		scroll.setFadeScrollBars(false);
		stage.addActor(scroll);
		
		
		Button backButton = new TextButton("Back", DEFAULT_SKIN);
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
		backButton.setSize(120, 35);
		backButton.setPosition((float) 0.825 * AdventureGame.GAME_SCREEN_WIDTH, (float) 0.896 * AdventureGame.GAME_SCREEN_HEIGHT);
		stage.addActor(backButton);
	}

	/*
	 * Label description = new Label("Test description", DEFAULT_SKIN);
	 * description.setWrap(true); ScrollPane scroll2 = new
	 * ScrollPane(description, DEFAULT_SKIN); scroll.setPosition((WIDTH_BUFFER *
	 * 2) + (23 * AdventureGame.GAME_SCREEN_HEIGHT) / 32,
	 * AdventureGame.GAME_SCREEN_HEIGHT - (HEIGHT_BUFFER * 2) -
	 * (AdventureGame.GAME_SCREEN_HEIGHT / 10) - ((3 *
	 * AdventureGame.GAME_SCREEN_HEIGHT) / 8));
	 * scroll2.setSize(AdventureGame.GAME_SCREEN_WIDTH / 2,
	 * AdventureGame.GAME_SCREEN_HEIGHT / 3); scroll2.setScrollingDisabled(true,
	 * false); stage.addActor(scroll2);
	 * 
	 * Table table = new Table(); Table container = new Table();
	 * container.setBounds(10, 10, 400, 400); ScrollPane scroll3 = new
	 * ScrollPane(table); container.add(scroll); container.row();
	 * container.debug(); table.debug(); for (Item item :
	 * inventoryCollection.values()) { Label label2 = new Label(item.getName() +
	 * " x " + item.getQuantity(), DEFAULT_SKIN);
	 * label2.setAlignment(Align.right); label2.setWrap(true);
	 * table.add(label2); table.row(); } stage.addActor(container); }
	 */

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
		
		for (int i = 0; i < 4; i++) {
			Texture itemIcon = new Texture(Gdx.files.internal("GameData/icons/bible.jpg"));
			game.batch.draw(itemIcon, (float) 0.3575 * AdventureGame.GAME_SCREEN_WIDTH, (float) (0.6 * AdventureGame.GAME_SCREEN_HEIGHT - i * (0.1 * AdventureGame.GAME_SCREEN_HEIGHT)), 40, 40);
		}
		
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
