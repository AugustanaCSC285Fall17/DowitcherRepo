package edu.augustana.csc285.game;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import edu.augustana.csc285.game.datamodel.Option;
import edu.augustana.csc285.game.datamodel.Slide;

public class SlideScreen implements Screen {
	public static final Set<Integer> KEY_SET = new HashSet<Integer>(Arrays.asList(8, 9, 10, 11, 12, 13, 14, 15, 16)); // 1
	public static final Skin DEFAULT_SKIN = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));
	// 9
	private final int WIDTH_BUFFER = AdventureGame.GAME_SCREEN_WIDTH / 100;
	private final int HEIGHT_BUFFER = AdventureGame.GAME_SCREEN_HEIGHT / 100;
	private final AdventureGame game;
	private Slide slide;
	private List<Option> visibleOptions;
	private Texture image;
	private OrthographicCamera camera;
	private int input;
	private int numOptions;
	private Stage stage;

	public SlideScreen(AdventureGame game) {
		// Set up data fields
		this.game = game;
		slide = game.manager.getCurrentSlide();
		image = new Texture(Gdx.files.internal(slide.getImage()));
		visibleOptions = slide.getVisibleOptions(game.manager.getPlayer());
		// Set up camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, AdventureGame.GAME_SCREEN_WIDTH, AdventureGame.GAME_SCREEN_HEIGHT);

		// Set up stage and table for buttons
		stage = new Stage(new ScreenViewport());
		Table buttonTable = new Table();

		buttonTable.setPosition((6 * AdventureGame.GAME_SCREEN_WIDTH) / 16 + WIDTH_BUFFER,
				AdventureGame.GAME_SCREEN_HEIGHT - ((6 * AdventureGame.GAME_SCREEN_HEIGHT) / 9) - (HEIGHT_BUFFER * 12)
						- AdventureGame.GAME_SCREEN_HEIGHT / 24);

		// Create and add buttons for ActionChoices
		for (int i = 0; i < visibleOptions.size(); i++) {
			Option option = visibleOptions.get(i);
			String displayString = (i + 1) + ".  " + option.getDesc();
			TextButton button = new TextButton(displayString, DEFAULT_SKIN);
			button.getLabel().setWrap(true);
			button.getLabel().setFontScale((float) 0.8);
			button.getLabel().setAlignment(Align.left);
			button.addListener(new InputListener() {
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					if (option.getNextSlideIndex() != -1) {
						if (option.isFeasible(game.manager.getPlayer())) {
							game.manager.applyOption(option);
							game.setScreen(new SlideScreen(game));
							dispose();
						} else {
							System.out.println(option.getRejectMessage());
							// To do note: handle exception when option is not
							// feasible
						}
					} else {
						game.setScreen(new EndScreen(game));
						dispose();
					}
				}

				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;
				}
			});
			// for now not measuring the width of the option string but assume
			// they are the same

			buttonTable.add(button).width((6 * AdventureGame.GAME_SCREEN_WIDTH) / 8)
					.height(AdventureGame.GAME_SCREEN_HEIGHT / 18).padTop(HEIGHT_BUFFER).row();

		}

		stage.addActor(buttonTable);

		Label description = new Label(slide.getDesc(), DEFAULT_SKIN);
		description.setWrap(true);
		ScrollPane scroll = new ScrollPane(description, DEFAULT_SKIN);
		scroll.setPosition((WIDTH_BUFFER * 2) + (23 * AdventureGame.GAME_SCREEN_HEIGHT) / 32,
				AdventureGame.GAME_SCREEN_HEIGHT - (HEIGHT_BUFFER * 2) - (AdventureGame.GAME_SCREEN_HEIGHT / 10)
						- ((3 * AdventureGame.GAME_SCREEN_HEIGHT) / 8));
		scroll.setSize(AdventureGame.GAME_SCREEN_WIDTH / 2, AdventureGame.GAME_SCREEN_HEIGHT / 3);
		scroll.setScrollingDisabled(true, false);
		stage.addActor(scroll);

		// create buttons for top right of screen (Inventory, Player Stats,
		// Settings)

		TextButton inventoryButton = new TextButton("Inventory", DEFAULT_SKIN);
		inventoryButton.setSize(120, 35);
		inventoryButton.addListener(new InputListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new InventoryScreen(game));
			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});

		TextButton playerStatButton = new TextButton("Stats", DEFAULT_SKIN);
		playerStatButton.setSize(120, 35);
		playerStatButton.addListener(new InputListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new PlayerStatScreen(game));
			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});

		TextButton settingsButton = new TextButton("Music", DEFAULT_SKIN);
		settingsButton.setSize(120, 35);
		settingsButton.addListener(new InputListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if(game.testMusic.isPlaying()) {
					game.testMusic.pause();
				}
				else {
					game.testMusic.play();
				}
			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});

		inventoryButton.setPosition(AdventureGame.GAME_SCREEN_WIDTH - inventoryButton.getWidth() * 3 - WIDTH_BUFFER * 3,
				AdventureGame.GAME_SCREEN_HEIGHT - inventoryButton.getHeight() - HEIGHT_BUFFER);
		playerStatButton.setPosition(
				AdventureGame.GAME_SCREEN_WIDTH - inventoryButton.getWidth() * 2 - WIDTH_BUFFER * 2,
				AdventureGame.GAME_SCREEN_HEIGHT - inventoryButton.getHeight() - HEIGHT_BUFFER);
		settingsButton.setPosition(AdventureGame.GAME_SCREEN_WIDTH - inventoryButton.getWidth() - (WIDTH_BUFFER),
				AdventureGame.GAME_SCREEN_HEIGHT - settingsButton.getHeight() - HEIGHT_BUFFER);

		stage.addActor(inventoryButton);
		stage.addActor(playerStatButton);
		stage.addActor(settingsButton);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// tell the camera to update its matrices.
		camera.update();
		stage.act();
		stage.draw();

		// Draw ActionChoices
		for (int temp : KEY_SET) {
			if (Gdx.input.isKeyJustPressed(temp)) {
				int index = temp - 8;
				if (index < visibleOptions.size()) {
					Option option = visibleOptions.get(index);
					if (option.getNextSlideIndex() != -1) {
						if (option.isFeasible(game.manager.getPlayer())) {
							game.manager.applyOption(option);
							game.setScreen(new SlideScreen(game));
							dispose();
						} else {
							System.out.println(option.getRejectMessage());
							// To do note: handle exception when option is not
							// feasible
						}
					} else {
						game.setScreen(new EndScreen(game));
						dispose();
					}
				}
			}
		}

		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		// game.batch.draw(region, x, y, originX, originY, width, height,
		// scaleX, scaleY, rotation);

		game.batch.draw(image, WIDTH_BUFFER, AdventureGame.GAME_SCREEN_HEIGHT - WIDTH_BUFFER
				- (5 * AdventureGame.GAME_SCREEN_HEIGHT) / 8 * (image.getWidth() / image.getHeight()),

				(5 * AdventureGame.GAME_SCREEN_HEIGHT) / 8,
				(5 * AdventureGame.GAME_SCREEN_HEIGHT) / 8 * (image.getWidth() / image.getHeight()));
		// game.batch.draw(region, x, y, originX, originY, width, height,
		// scaleX, scaleY, rotation);
		game.batch.end();
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
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
		image.dispose();
		stage.dispose();
	}

}
