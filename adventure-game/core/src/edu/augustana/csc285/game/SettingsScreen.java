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
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import edu.augustana.csc285.game.datamodel.Player;

public class SettingsScreen implements Screen {
	private Stage stage;
	private OrthographicCamera camera;
	private AdventureGame game;
	private boolean fromMenuScreen;
	private int size;

	// Second constructor to allow for back button to go back to main menu
	public SettingsScreen(AdventureGame game, boolean fromMenuScreen) {
		size = game.size;
		this.game = game;
		this.fromMenuScreen = fromMenuScreen;
		setUpSettingsScreen();
	}

	// First constructor for regular slide screen settings
	public SettingsScreen(AdventureGame game) {
		this(game, false);
	}

	public void setUpSettingsScreen() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, AdventureGame.GAME_SCREEN_WIDTH, AdventureGame.GAME_SCREEN_HEIGHT);
		stage = new Stage(new ScreenViewport());
		BitmapFont titleFont = new BitmapFont(Gdx.files.internal("fonts/titleFont.fnt"), false);

		Table settingsTable = new Table();
		settingsTable.setPosition((float) (AdventureGame.GAME_SCREEN_WIDTH / 2),
				(float) 0.4 * AdventureGame.GAME_SCREEN_WIDTH);

		Label screenTitle = new Label("Settings", new Label.LabelStyle(titleFont, Color.BLACK));
		settingsTable.row();
		settingsTable.add(screenTitle).pad(10).colspan(3).center();

		String str = "";
		// Add music on button to table
		if (game.defaultMusic.isPlaying()) {
			str = "Music Off";
		} else {
			str = "Music On";
		}
		Button musicButton = new TextButton(str, game.defaultSkin, "default");
		musicButton.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.buttonPressed.play();
				if (game.defaultMusic.isPlaying()) {
					game.defaultMusic.pause();
				} else {
					game.defaultMusic.play();
				}
				game.setScreen(new SettingsScreen(game, fromMenuScreen));
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

		});

		Button backButton = new TextButton("Back", game.defaultSkin);
		backButton.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if (fromMenuScreen) {
					game.setScreen(new MainMenuScreen(game, false));
				} else {
					game.setScreen(new SlideScreen(game));
				}
				dispose();
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

		});

		Button decreaseButton = new TextButton("Decrease", game.defaultSkin, "default");
		decreaseButton.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.buttonPressed.play();
				if (size > 1) {
					size--;
				}
				game.setDescFont(size);
				game.setScreen(new SettingsScreen(game, fromMenuScreen));
				dispose();

			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

		});
		Button increaseButton = new TextButton("Increase", game.defaultSkin, "default");
		increaseButton.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.buttonPressed.play();
				if (size < 5) {
					size++;
				}
				game.setDescFont(size);
				game.setScreen(new SettingsScreen(game, fromMenuScreen));
				dispose();
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

		});
		settingsTable.center().bottom();
		Label sizeLabel = new Label("" + game.size, game.defaultSkin);
		settingsTable.row();
		settingsTable.add(musicButton).width(175).height(45).pad(5).colspan(3).center();
		settingsTable.row();
		settingsTable.add(decreaseButton).width(175).height(45).pad(5);
		settingsTable.add(sizeLabel);
		settingsTable.add(increaseButton).width(175).height(45).pad(5);
		settingsTable.row();

		if (!fromMenuScreen) {
			// Add exit to menu button to table
			Button exitToMenuButton = new TextButton("Exit to Menu", game.defaultSkin, "default");
			exitToMenuButton.addListener(new InputListener() {
				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					game.buttonPressed.play();
					game.setScreen(new MainMenuScreen(game, false));
					game.initializeManager();
					dispose();
				}

				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;
				}
			});
			settingsTable.add(exitToMenuButton).width(200).height(50).pad(5).colspan(3).center();
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
		game.batch.draw(game.backgroundImage, 0, 0, AdventureGame.GAME_SCREEN_WIDTH, AdventureGame.GAME_SCREEN_HEIGHT);
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
		stage.dispose();
	}
}
