package edu.augustana.csc285.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class EndScreen implements Screen {
	public static final Skin DEFAULT_SKIN = new Skin(Gdx.files.internal("skin/defaultSkin/flat-earth-ui.json"));
	private final AdventureGame game;
	private OrthographicCamera camera;
	private Stage stage;
	private Texture logo;
	private Texture backgroundImage;

	public EndScreen(AdventureGame game) {

		this.game = game;
		stage = new Stage(new ScreenViewport());
		this.camera = new OrthographicCamera();
		camera.setToOrtho(false, AdventureGame.GAME_SCREEN_WIDTH, AdventureGame.GAME_SCREEN_HEIGHT);
		logo = new Texture("GameData/swensonlogo.png");
		backgroundImage = new Texture("GameData/background.jpg");

		Table buttonTable = new Table();
		buttonTable.setPosition(400, 200);

		TextButton playAgainButton = new TextButton("Play Again", DEFAULT_SKIN, "default");
		playAgainButton.addListener(new InputListener() {

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.initializeManager();
				game.setScreen(new MainMenuScreen(game));
				dispose();
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

		});
		buttonTable.add(playAgainButton).width(150).height(30).pad(5).row();

		TextButton exitButton = new TextButton("Exit", DEFAULT_SKIN, "default");
		exitButton.setSize(50, 50);
		exitButton.setPosition(50, 50);
		exitButton.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.exit();
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		buttonTable.add(exitButton).width(150).height(30).pad(5).row();
		stage.addActor(buttonTable);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		game.batch.draw(backgroundImage, 0, 0, AdventureGame.GAME_SCREEN_WIDTH, AdventureGame.GAME_SCREEN_HEIGHT);
		game.batch.draw(logo, (float) 0.22 * AdventureGame.GAME_SCREEN_WIDTH,
				(float) 0.78 * AdventureGame.GAME_SCREEN_HEIGHT, logo.getWidth() / 2, logo.getHeight() / 2);
		game.font.draw(game.batch, "GAME OVER", 400, 50);
		game.batch.end();
		stage.act();
		stage.draw();

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
		backgroundImage.dispose();
	}

}
