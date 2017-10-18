package edu.augustana.csc285.game;

import java.util.*;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.augustana.csc285.game.datamodel.Item;
import edu.augustana.csc285.game.datamodel.Player;
import edu.augustana.csc285.game.datamodel.Property;

public class PlayerStatScreen implements Screen {
	public static final Skin DEFAULT_SKIN = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));
	public static final int GAME_SCREEN_WIDTH = 800;
	public static final int GAME_SCREEN_HEIGHT = 480;
	private OrthographicCamera camera;
	private final AdventureGame game;
	private Stage stage;
	private Texture backgroundImage;
	private BitmapFont playerFont;
	private final int WIDTH_BUFFER = AdventureGame.GAME_SCREEN_WIDTH / 100;
	private final int HEIGHT_BUFFER = AdventureGame.GAME_SCREEN_HEIGHT / 100;


	public PlayerStatScreen(final AdventureGame game) {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, AdventureGame.GAME_SCREEN_WIDTH, AdventureGame.GAME_SCREEN_HEIGHT);
		this.game = game;
		stage = new Stage(new ScreenViewport());
		backgroundImage = new Texture("art/background.jpg");
		BitmapFont titleFont = new BitmapFont(Gdx.files.internal("fonts/TitleFont/bigTitle.fnt"), false);
		
		Map<String, Property> properties = game.manager.getPlayer().getProperties().getProperties();
		String propertyString = "You are: " + game.manager.getPlayer().getName() + "\n";
		propertyString += "Gender: " + game.manager.getPlayer().getGender() + "\n";
		
		for (Property property : properties.values()) {
			propertyString += property.getType().toString() + ": " + property.getQuantity() + "\n";
		}
		
		playerFont = new BitmapFont(Gdx.files.internal("fonts/DescriptionFont/DescriptionText.fnt"), false);
		Label label = new Label(propertyString, new Label.LabelStyle(playerFont, Color.BLACK));
		label.setWrap(true);
		ScrollPane scroll = new ScrollPane(label, DEFAULT_SKIN);
		scroll.setPosition(20, 20);
		scroll.setSize(300, 300);
		scroll.setScrollingDisabled(true, false);
		stage.addActor(scroll);

		Label screenTitle = new Label("Player Stats", new Label.LabelStyle(titleFont, Color.BLACK));
		screenTitle.setPosition(WIDTH_BUFFER, (float) 0.87 * AdventureGame.GAME_SCREEN_HEIGHT);
		stage.addActor(screenTitle);
		
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
		backButton.setPosition(660, 430);
		stage.addActor(backButton);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		// Draw background image
		game.batch.draw(backgroundImage, 0, 0, AdventureGame.GAME_SCREEN_WIDTH, AdventureGame.GAME_SCREEN_HEIGHT);
		game.batch.end();

		stage.act();
		stage.draw();
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
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