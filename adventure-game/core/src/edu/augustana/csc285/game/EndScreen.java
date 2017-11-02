package edu.augustana.csc285.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class EndScreen implements Screen {
	private final AdventureGame game;
	private OrthographicCamera camera;
	private Stage stage;
	private Texture logo;
	private BitmapFont gameOver;

	public EndScreen(AdventureGame game) {
		this.game = game;
		gameOver = new BitmapFont(Gdx.files.internal("fonts/titleFont.fnt"), false);
		stage = new Stage(new ScreenViewport());
		this.camera = new OrthographicCamera();
		camera.setToOrtho(false, AdventureGame.GAME_SCREEN_WIDTH, AdventureGame.GAME_SCREEN_HEIGHT);
		logo = new Texture("GameData/swensonlogo.png");

		Table buttonTable = new Table();
		buttonTable.setPosition(AdventureGame.GAME_SCREEN_WIDTH / 2, (float) 0.5 * AdventureGame.GAME_SCREEN_HEIGHT);

		TextButton playAgainButton = new TextButton("Play Again", game.defaultSkin, "default");
		playAgainButton.addListener(new InputListener() {

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.initializeManager();
				game.setScreen(new MainMenuScreen(game, false));
				dispose();
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

		});
		buttonTable.add(playAgainButton).width(170).height(50).pad(5).row();
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
		game.batch.draw(game.backgroundImage, 0, 0, AdventureGame.GAME_SCREEN_WIDTH, AdventureGame.GAME_SCREEN_HEIGHT);
		game.batch.draw(logo, (float) 0.28 * AdventureGame.GAME_SCREEN_WIDTH,
				(float) 0.8 * AdventureGame.GAME_SCREEN_HEIGHT, (float) 0.5 * logo.getWidth(),
				(float) 0.5 * logo.getHeight());
		gameOver.draw(game.batch, "GAME OVER", (float) 0.40 * AdventureGame.GAME_SCREEN_WIDTH,
				(float) (AdventureGame.GAME_SCREEN_HEIGHT * 0.72));
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
	}

}
