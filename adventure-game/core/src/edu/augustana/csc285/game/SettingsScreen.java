package edu.augustana.csc285.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
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
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import edu.augustana.csc285.game.datamodel.Player;

public class SettingsScreen implements Screen {
	public static final Skin DEFAULT_SKIN = new Skin(Gdx.files.internal("skin/defaultSkin/flat-earth-ui.json"));
	public static final Skin BACK_BUTTON_SKIN = new Skin(Gdx.files.internal("skin/menuSkin/flat-earth-ui.json"));
	public static final int GAME_SCREEN_WIDTH = 800;
	public static final int GA5ME_SCREEN_HEIGHT = 480;
	private final int WIDTH_BUFFER = AdventureGame.GAME_SCREEN_WIDTH / 100;
	private final int HEIGHT_BUFFER = AdventureGame.GAME_SCREEN_HEIGHT / 100;
	private Stage stage;
	private OrthographicCamera camera;
	private AdventureGame game;
	private Texture img;
	private String text;
	private Texture backgroundImage;
	private boolean fromMenuScreen = false;

	// First constructor for regular slide screen settings
	public SettingsScreen(AdventureGame game) {
		this.game = game;
		setUpSettingsScreen();
	}

	// Second constructor to allow for back button to go back to main menu
	public SettingsScreen(AdventureGame game2, boolean fromMenuScreen) {
		this.fromMenuScreen = fromMenuScreen;
		this.game = game2;
		setUpSettingsScreen();
	}

	public void setUpSettingsScreen() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, AdventureGame.GAME_SCREEN_WIDTH, AdventureGame.GAME_SCREEN_HEIGHT);
		stage = new Stage(new ScreenViewport());
		backgroundImage = new Texture("GameData/background.jpg");
		BitmapFont titleFont = new BitmapFont(Gdx.files.internal("fonts/titleFont.fnt"), false);

		Table settingsTable = new Table();
		settingsTable.setPosition(AdventureGame.GAME_SCREEN_WIDTH / 2, (float) 0.4 * AdventureGame.GAME_SCREEN_WIDTH);

		Label screenTitle = new Label("Settings", new Label.LabelStyle(titleFont, Color.BLACK));
		settingsTable.add(screenTitle).pad(10).row();

		// Add music on button to table
		Button musicOnButton = new TextButton("Music On", BACK_BUTTON_SKIN, "default");
		musicOnButton.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.defaultMusic.play();
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

		});
		settingsTable.add(musicOnButton).width(175).height(45).pad(5).row();

		// Add music off button to table
		Button musicOffButton = new TextButton("Music Off", BACK_BUTTON_SKIN, "default");
		musicOffButton.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.defaultMusic.pause();
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		settingsTable.add(musicOffButton).width(175).height(45).pad(5).row();
		
		Button backButton = new TextButton("Back", BACK_BUTTON_SKIN);
		backButton.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if (fromMenuScreen) {
					game.setScreen(new MainMenuScreen(game, false));
				} else {
					game.setScreen(new SlideScreen(game));
					
				}
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

		});
		
		if (!fromMenuScreen) {
		// Add exit to menu button to table
		Button exitToMenuButton = new TextButton("Exit to Menu", BACK_BUTTON_SKIN, "default");
		exitToMenuButton.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new MainMenuScreen(game, true));
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		settingsTable.add(exitToMenuButton).width(200).height(50).pad(5).row();
		}
		
		backButton.setSize(130, 45);
		backButton.setPosition(1000, 650);
		stage.addActor(backButton);
		stage.addActor(settingsTable);
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
		// Go back to slide screen
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			game.setScreen(new SlideScreen(game));
			dispose();
		}

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
		img.dispose();
	}
}
