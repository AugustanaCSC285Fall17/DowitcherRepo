package edu.augustana.csc285.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class MainMenuScreen extends ScreenAdapter implements Screen {

	private final AdventureGame game;
	private Stage stage;
	OrthographicCamera camera;

	public MainMenuScreen(final AdventureGame game) {
		this.game = game;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, AdventureGame.GAME_SCREEN_WIDTH, 
								AdventureGame.GAME_SCREEN_HEIGHT);
		stage = new Stage(new ScreenViewport());
		
		// Section to create a button 
		Skin skin = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));
		Button button = new TextButton("SlideButton", skin, "default");
		button.setSize(50, 50);
		button.setPosition(50, 50);
		button.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new SlideScreen(game));
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

		});
		stage.addActor(button);
		// End of section to create a button
	}
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.4f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		game.font.draw(game.batch, "Welcome to Adventure!!! ", 100, 150);
		game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
		game.batch.end();
//		if (Gdx.input.isTouched()) {
//			game.setScreen(new SlideScreen(game));
//			dispose();
//		}
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
	}
}

	
