package edu.augustana.csc285.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import edu.augustana.csc285.game.datamodel.Option;
import edu.augustana.csc285.game.datamodel.Slide;

public class SlideScreen implements Screen {
	public static final HashSet<Integer> KEY_SET = new HashSet<Integer>(
			Arrays.asList(8, 9, 10, 11, 12, 13, 14, 15, 16)); // 1
	private final int WIDTH_BUFFER = AdventureGame.GAME_SCREEN_WIDTH / 100;
	private final int HEIGHT_BUFFER = AdventureGame.GAME_SCREEN_HEIGHT / 100;
	private AdventureGame game;

	private Slide slide;
	private ArrayList<Option> visibleOptions;
	private Texture image;
	private OrthographicCamera camera;
	private Stage stage;
	private boolean popUp;
	private String rejectMessage;
	private int layout;
	private String desc;

	public SlideScreen(AdventureGame game, String rejectMessage) {
		this(game);
		this.popUp = true;
		this.rejectMessage = rejectMessage;
	}

	public SlideScreen(AdventureGame game) {
		BitmapFont defaultFont = new BitmapFont(Gdx.files.internal("fonts/defaultFont.fnt"), false);
		BitmapFont titleFont = new BitmapFont(Gdx.files.internal("fonts/menuTitle.fnt"), false);

		// Set up data fields
		this.game = game;

		slide = game.manager.getCurrentSlide();

		if (slide.getImage() != null && slide.getImage() != "") {
			image = new Texture(Gdx.files.internal("image/slide/" + slide.getImage()));
		}
		if (slide.getDesc() != null && slide.getDesc() != "") {
			desc = slide.getDesc();
		}
		// NOTE:
		// layout 0: regular slide
		// layout 1: description and no image slide
		// layout 2: image and no description slide
		if (image != null) {
			if (desc != null) {
				layout = 0;
			} else {
				layout = 2;
			}
		} else {
			layout = 1;
		}

		Button inventoryBtn = this.addTextureRegion("image/icon/other/inventory.png", new InventoryScreen(game), 2);
		Button settingsBtn = this.addTextureRegion("image/icon/other/settings.png", new SettingsScreen(game), 1);

		visibleOptions = slide.getVisibleOptions(game.manager.getPlayer());

		// Set up camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, AdventureGame.GAME_SCREEN_WIDTH, AdventureGame.GAME_SCREEN_HEIGHT);

		// Set up stage and table for buttons
		stage = new Stage(new ScreenViewport());
		stage.addActor(inventoryBtn);
		stage.addActor(settingsBtn);

		Table buttonTable = new Table();

		// Figures out the biggest width of all the buttons and uses this width
		// to draw the hitboxes around all other buttons
		double biggestButtonWidth = 0;
		for (int i = 0; i < visibleOptions.size(); i++) {
			Option option = visibleOptions.get(i);
			String displayString = (i + 1) + ".  " + option.getDesc();
			GlyphLayout layout = new GlyphLayout(defaultFont, displayString);
			if (layout.width > biggestButtonWidth) {
				biggestButtonWidth = layout.width;
			}
		}
		// Create and add buttons for ActionChoices
		// Put buttons in middle if layout 1
		if (layout == 0) {
			buttonTable.setPosition((float) 0.755 * AdventureGame.GAME_SCREEN_WIDTH,
					(float) 0.20 * AdventureGame.GAME_SCREEN_HEIGHT);
		} else if (layout == 1) {
			buttonTable.setPosition((float) 0.5 * AdventureGame.GAME_SCREEN_WIDTH,
					(float) 0.20 * AdventureGame.GAME_SCREEN_HEIGHT);
		} else if (layout == 2) {
			buttonTable.setPosition((float) 0.755 * AdventureGame.GAME_SCREEN_WIDTH,
					(float) 0.50 * AdventureGame.GAME_SCREEN_HEIGHT);
		}

		for (int i = 0; i < visibleOptions.size(); i++) {
			Option option = visibleOptions.get(i);
			String displayString = (i + 1) + ".  " + option.getDesc();
			TextButton button = new TextButton(displayString, game.defaultSkin, "default");

			// button.getLabel().setAlignment(Align.left);
			button.addListener(new InputListener() {
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					if (option.getNextSlideIndex() != "0") {
						if (option.isFeasible(game.manager.getPlayer())) {
							game.manager.applyOption(option);
							game.setScreen(new SlideScreen(game));

						} else {
							game.setScreen(new SlideScreen(game,option.getRejectMessage()));
						}
					} else {
						game.setScreen(new EndScreen(game));

					}
					dispose();
				}

				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;
				}
			});

			buttonTable.add(button).width((float) biggestButtonWidth + 50)
					.height((float) 0.0555 * AdventureGame.GAME_SCREEN_HEIGHT).padTop(HEIGHT_BUFFER).row();

		}
		stage.addActor(buttonTable);

		// Set up slide title
		Label slideTitle = new Label(this.slide.getTitle(), new Label.LabelStyle(titleFont, Color.BLACK));

		GlyphLayout titleLength = new GlyphLayout(defaultFont, slideTitle.toString());

		slideTitle.setPosition((float) ((0.5 * AdventureGame.GAME_SCREEN_WIDTH) - titleLength.width),

				(float) 0.90 * AdventureGame.GAME_SCREEN_HEIGHT);
		stage.addActor(slideTitle);

		if (layout == 0 || layout == 1) {
			// Set up the scroll pane with slide description
			Label description = new Label(slide.getDesc(), new Label.LabelStyle(game.descFont, Color.BLACK));
			description.setWrap(true);
			ScrollPane scroll = new ScrollPane(description, game.scrollSkin);
			scroll.setScrollingDisabled(true, false);
			scroll.setFadeScrollBars(false);
			scroll.setScrollbarsOnTop(true);
			if (layout == 0) {
				scroll.setPosition((float) 0.54 * AdventureGame.GAME_SCREEN_WIDTH,
						(float) 0.35 * AdventureGame.GAME_SCREEN_HEIGHT);
				scroll.setSize((float) 0.45 * AdventureGame.GAME_SCREEN_WIDTH,
						(float) 0.50 * AdventureGame.GAME_SCREEN_HEIGHT);
			} else if (layout == 1) {
				scroll.setPosition((float) 0.2 * AdventureGame.GAME_SCREEN_WIDTH,
						(float) 0.35 * AdventureGame.GAME_SCREEN_HEIGHT);
				scroll.setSize((float) 0.65 * AdventureGame.GAME_SCREEN_WIDTH,
						(float) 0.50 * AdventureGame.GAME_SCREEN_HEIGHT);
			}
			stage.addActor(scroll);
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// tell the camera to update its matrices.
		camera.update();
		// Draw background image
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		game.batch.draw(game.backgroundImage, 0, 0, AdventureGame.GAME_SCREEN_WIDTH, AdventureGame.GAME_SCREEN_HEIGHT);

		// Draw the slide image
		if (layout == 0) {
			game.batch.draw(image, WIDTH_BUFFER, WIDTH_BUFFER, 600, 600);
		} else if (layout == 2) {
			game.batch.draw(image, WIDTH_BUFFER, 60, 600, 600);
		}

		game.batch.end();

		stage.act();
		stage.draw();
		// Draw ActionChoices
		for (int temp : KEY_SET) {
			if (Gdx.input.isKeyJustPressed(temp)) {
				int index = temp - 8;
				if (index < visibleOptions.size()) {
					Option option = visibleOptions.get(index);
					if (option.getNextSlideIndex() != "-1") {
						if (option.isFeasible(game.manager.getPlayer())) {
							game.manager.applyOption(option);
							game.setScreen(new SlideScreen(game));
							dispose();
						} else {
							game.setScreen(new SlideScreen(game, option.getRejectMessage()));
							dispose();
						}
					} else {
						game.setScreen(new EndScreen(game));
						dispose();
					}
				}
			}
		}
		// Change to inventory screen
		if (Gdx.input.isKeyJustPressed(Keys.I)) {
			game.setScreen(new InventoryScreen(game));
			dispose();
		}
		if (Gdx.input.isKeyJustPressed(Keys.S)) {
			game.setScreen(new SettingsScreen(game, true,false));
			dispose();
		}

	}

	public Button addTextureRegion(String textureLocation, Screen screen, int locationInt) {
		Texture textureImage = new Texture(textureLocation);
		TextureRegion textureRegion = new TextureRegion(textureImage);
		TextureRegionDrawable textureRegionDrawable = new TextureRegionDrawable(textureRegion);
		Button button = new ImageButton(textureRegionDrawable);
		button.setSize(75, 75);
		button.setPosition(AdventureGame.GAME_SCREEN_WIDTH - (button.getWidth() + WIDTH_BUFFER) * locationInt,
				AdventureGame.GAME_SCREEN_HEIGHT - HEIGHT_BUFFER - button.getHeight());
		button.addListener(new InputListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(screen);
				dispose();
			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		return button;
	}


	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		if (popUp) {
			new Dialog("", game.defaultSkin) {

				{
					text(rejectMessage);
					button("Back", game);
				}

				@Override
				protected void result(final Object object) {
					game.setScreen(new SlideScreen(game));
					dispose();
				}
			}.show(stage);
		}
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
		if (image != null) {
			image.dispose();
		}
		stage.dispose();
	}

}
