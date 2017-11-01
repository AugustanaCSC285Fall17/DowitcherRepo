package edu.augustana.csc285.game;

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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/*
 * To create a CreditsScreen.
 * @author faisal, Lars Harvey  
 */
public class CreditsScreen implements Screen {
	public static final Skin DEFAULT_SKIN = new Skin(Gdx.files.internal("skin/defaultSkin/cloud-form-ui.json"));
	public static final Skin BACK_BUTTON_SKIN = new Skin(Gdx.files.internal("skin/menuSkin/cloud-form-ui.json"));
	private final AdventureGame game;
	private OrthographicCamera camera;
	private Texture backgroundImage;
	private Stage stage;

	public CreditsScreen(AdventureGame game) {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, AdventureGame.GAME_SCREEN_WIDTH, AdventureGame.GAME_SCREEN_HEIGHT);
		this.game = game;
		BitmapFont creditsFont = new BitmapFont(Gdx.files.internal("fonts/defaultFont.fnt"), false);
		stage = new Stage(new ScreenViewport());
		backgroundImage = new Texture("GameData/background.jpg");
		
		Label creditsTitle = new Label("Credits", new Label.LabelStyle(new BitmapFont(Gdx.files.internal("fonts/titleFont.fnt"), false), Color.BLACK));
		
		String credits = "Game designed by Dr. Forrest Stonedahl's Software Development CSC 285 students, Dat Tran, Daniel Zweiner, Lars Harvey, Faisal Nawaz, and Ryan Philp as well as Dr. Brian Leech's history students, Abigail Buchanan, Brooks Fielder, and Katie Laschanzky for the for the Swenson Swedish Immigration Research Center at Augustana College in Rock Island, Illinois, 2017. ";
		// Wrap text so it doesn't go off the screen using support class
		SupportMethod sm = new SupportMethod();
		credits = sm.wrapString(credits, 80);
		Label creditsLabel = new Label(credits, new Label.LabelStyle(creditsFont, Color.BLACK));
		Table creditsTable = new Table();
		creditsTable.setPosition(AdventureGame.GAME_SCREEN_WIDTH / 2, (float) 0.4 * AdventureGame.GAME_SCREEN_WIDTH);
		creditsTable.add(creditsTitle).pad(10).row();
		creditsTable.add(creditsLabel).pad(10).row();
		
		stage.addActor(creditsTable);

	
		Button backButton = new TextButton("Back", BACK_BUTTON_SKIN);
		backButton.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new MainMenuScreen(game, false));
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
		game.batch.begin();
		
		// Draw background image
		game.batch.draw(backgroundImage, 0, 0, AdventureGame.GAME_SCREEN_WIDTH, AdventureGame.GAME_SCREEN_HEIGHT);
		game.batch.end();
		
		camera.update();
		stage.act();
		stage.draw();
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

