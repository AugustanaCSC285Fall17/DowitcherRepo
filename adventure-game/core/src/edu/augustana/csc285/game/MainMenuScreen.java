package edu.augustana.csc285.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

//import edu.augustana.csc285.game.datamodel.Notification;

public class MainMenuScreen extends ScreenAdapter implements Screen {
	public static final Skin MENU_SKIN = new Skin(Gdx.files.internal("skin/menuSkin/flat-earth-ui.json"));
	private final AdventureGame game;
	private String introduction;
	private Stage stage;
	private GlyphLayout layout;
	private Texture logo;
	private Texture backgroundImage;
	private BitmapFont titleFont;
	OrthographicCamera camera;
	
	public MainMenuScreen(final AdventureGame game) {
		this.game = game;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, AdventureGame.GAME_SCREEN_WIDTH, AdventureGame.GAME_SCREEN_HEIGHT);
		stage = new Stage(new ScreenViewport());
		logo = new Texture("GameData/swensonlogo.png");
		backgroundImage = new Texture("GameData/background.jpg");
		titleFont = new BitmapFont(Gdx.files.internal("fonts/titleFont.fnt"), false);
		
		Table buttonTable = new Table();
		buttonTable.setPosition((float) 0.5 * AdventureGame.GAME_SCREEN_WIDTH, (float) 0.45 * AdventureGame.GAME_SCREEN_HEIGHT);

		TextButton newGameButton = new TextButton("New Game", MENU_SKIN, "default");
		newGameButton.addListener(new InputListener() {

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
		buttonTable.add(newGameButton).width(170).height(50).pad(5).row();

		TextButton creditButton = new TextButton("Credit", MENU_SKIN, "default");
		creditButton.setSize(180, 70);
		creditButton.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new CreditsScreen(game));
				dispose();
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

		});
		buttonTable.add(creditButton).width(180).height(50).pad(5).row();

		TextButton settingsButton = new TextButton("Settings", MENU_SKIN, "default");
		boolean fromMenuScreen = true;
		settingsButton.addListener(new InputListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new SettingsScreen(game, fromMenuScreen));
				dispose();

			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});

		buttonTable.add(settingsButton).width(170).height(50).pad(5).row();

		TextButton exitButton = new TextButton("Exit", MENU_SKIN, "default");
		exitButton.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new EndScreen(game));
				//Gdx.app.exit();
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

		});
		buttonTable.add(exitButton).width(170).height(50).pad(5).row();

		stage.addActor(buttonTable);
		introduction = "Welcome to Swedish Settler Trail";
		layout = new GlyphLayout(titleFont, introduction);
		float textWidth = layout.width;
		float textHeight = layout.height;
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
		
		stage.act();
		stage.draw();
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		game.batch.draw(logo, (float) 0.32 * AdventureGame.GAME_SCREEN_WIDTH, (float) 0.8 * AdventureGame.GAME_SCREEN_HEIGHT, (float) 0.5 * logo.getWidth(), (float) 0.5 * logo.getHeight());
		titleFont.draw(game.batch, introduction, (float) 0.27 * AdventureGame.GAME_SCREEN_WIDTH, 
				(float) (AdventureGame.GAME_SCREEN_HEIGHT * 0.72));
		game.batch.end();

	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		stage.dispose();
		logo.dispose();
	}

}
