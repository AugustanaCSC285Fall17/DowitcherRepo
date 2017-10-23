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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import edu.augustana.csc285.game.datamodel.Option;
import edu.augustana.csc285.game.datamodel.Slide;

public class SlideScreen implements Screen {
	public static final HashSet<Integer> KEY_SET = new HashSet<Integer>(
			Arrays.asList(8, 9, 10, 11, 12, 13, 14, 15, 16)); // 1
	public static final Skin DEFAULT_SKIN = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));
	// 9
	private final int WIDTH_BUFFER = AdventureGame.GAME_SCREEN_WIDTH / 100;
	private final int HEIGHT_BUFFER = AdventureGame.GAME_SCREEN_HEIGHT / 100;
	private final AdventureGame game;
	private Slide slide;
	private ArrayList<Option> visibleOptions;
	private Texture image;
	private Button inventoryBtn;
	private Button playerStatBtn;
	private Button settingsBtn;
	private Texture backgroundImage;
	private OrthographicCamera camera;
	private Stage stage;
	private boolean popUp;
	private String rejectMessage;
	private BitmapFont descriptionFont;

	public SlideScreen(AdventureGame game, String rejectMessage) {
		this(game);
		this.popUp = true;
		this.rejectMessage = rejectMessage;
	}

	public SlideScreen(AdventureGame game) {
		// Giant to do note: Set up checking for multiple layout

		// Set up data fields
		this.popUp = false;
		this.game = game;
		slide = game.manager.getCurrentSlide();
		image = new Texture(Gdx.files.internal(slide.getImage()));
		backgroundImage = new Texture("art/background.jpg");
		descriptionFont = new BitmapFont(Gdx.files.internal("fonts/DescriptionFont/descriptionText.fnt"), false);

		inventoryBtn = this.addTextureRegion("icons/inventory.png", new InventoryScreen(game), 3);
		playerStatBtn = this.addTextureRegion("icons/player-stat.png", new PlayerStatScreen(game), 2);
		settingsBtn = this.addTextureRegion("icons/settings.jpg", new SettingsScreen(game), 1);

		visibleOptions = (ArrayList<Option>) slide.getVisibleOptions(game.manager.getPlayer());

		// Set up camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, AdventureGame.GAME_SCREEN_WIDTH, AdventureGame.GAME_SCREEN_HEIGHT);

		// Set up stage and table for buttons
		stage = new Stage(new ScreenViewport());
		stage.addActor(inventoryBtn);
		stage.addActor(playerStatBtn);
		stage.addActor(settingsBtn);

		Table buttonTable = new Table();

		// Figures out the biggest width of all the buttons and uses this width
		// to draw the hitboxes around all other buttons
		double biggestButtonWidth = 0;
		for (int i = 0; i < visibleOptions.size(); i++) {
			Option option = visibleOptions.get(i);
			String displayString = (i + 1) + ".  " + option.getDesc();
			GlyphLayout layout = new GlyphLayout(descriptionFont, displayString);
			if (layout.width > biggestButtonWidth) {
				biggestButtonWidth = layout.width;
			}

		}

		// Currently I cannot figure out how to draw all the buttons aligned to
		// the left.. Dat??
		buttonTable.setPosition((float) (AdventureGame.GAME_SCREEN_WIDTH / 2),
				(float) 0.19167 * AdventureGame.GAME_SCREEN_HEIGHT);

		// Create and add buttons for ActionChoices
		for (int i = 0; i < visibleOptions.size(); i++) {
			Option option = visibleOptions.get(i);
			String displayString = (i + 1) + ".  " + option.getDesc();
			TextButton button = new TextButton(displayString, DEFAULT_SKIN, "default");
			// button.getLabel().setWrap(true);
			// button.getLabel().setFontScale((float) 0.8);
			button.getLabel().setAlignment(Align.left);
			button.addListener(new InputListener() {
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					if (option.getNextSlideIndex() != "-1") {
						if (option.isFeasible(game.manager.getPlayer())) {
							game.manager.applyOption(option);
							game.setScreen(new SlideScreen(game));
							dispose();
						} else {
							game.setScreen(new SlideScreen(game, option.getRejectMessage()));
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

			buttonTable.add(button).width((float) biggestButtonWidth + 70)
					.height((float) 0.0555 * AdventureGame.GAME_SCREEN_HEIGHT).padTop(HEIGHT_BUFFER).row();

		}

		stage.addActor(buttonTable);

		Label description = new Label(slide.getDesc(), new Label.LabelStyle(descriptionFont, Color.BLACK));
		description.setWrap(true);
		ScrollPane scroll = new ScrollPane(description, DEFAULT_SKIN);
		scroll.setPosition((float) 0.45125 * AdventureGame.GAME_SCREEN_WIDTH,
				(float) 0.38 * AdventureGame.GAME_SCREEN_HEIGHT);
		scroll.setSize((float) 0.5 * AdventureGame.GAME_SCREEN_WIDTH, (float) 0.45 * AdventureGame.GAME_SCREEN_HEIGHT);
		scroll.setScrollingDisabled(true, false);
		stage.addActor(scroll);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Draw background image
		game.batch.begin();
		game.batch.draw(backgroundImage, 0, 0, AdventureGame.GAME_SCREEN_WIDTH, AdventureGame.GAME_SCREEN_HEIGHT);
		game.batch.end();

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
					if (option.getNextSlideIndex() != "-1") {
						if (option.isFeasible(game.manager.getPlayer())) {
							game.manager.applyOption(option);
							game.setScreen(new SlideScreen(game));
							dispose();
						} else {
							game.setScreen(new SlideScreen(game, option.getRejectMessage()));
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
		// Change to player stat screen
		if (Gdx.input.isKeyJustPressed(Keys.S)) {
			game.setScreen(new PlayerStatScreen(game));
			dispose();
		}

		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();

		// Draw the slide images
		game.batch.draw(image, WIDTH_BUFFER,
				Math.round(0.3583333333 * AdventureGame.GAME_SCREEN_HEIGHT * (image.getWidth() / image.getHeight())),

				Math.round(0.625 * AdventureGame.GAME_SCREEN_HEIGHT),
				Math.round(0.625 * AdventureGame.GAME_SCREEN_HEIGHT * (image.getWidth() / image.getHeight())));

		game.batch.end();
	}

	public Button addTextureRegion(String skinLocation, Screen screen, int locationInt) {
		Texture textureImage = new Texture(skinLocation);

		TextureRegion textureRegion = new TextureRegion(textureImage);
		TextureRegionDrawable textureRegionDrawable = new TextureRegionDrawable(textureRegion);
		Button button = new ImageButton(textureRegionDrawable);
		button.setSize(50, 50);
		button.setPosition(AdventureGame.GAME_SCREEN_WIDTH - (button.getWidth() + WIDTH_BUFFER) * locationInt,
				AdventureGame.GAME_SCREEN_HEIGHT - HEIGHT_BUFFER - button.getHeight());
		button.addListener(new InputListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(screen);
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
			new Dialog("You cannot do this", DEFAULT_SKIN) {

				{
					text(rejectMessage);
					button("Back", game);
				}

				@Override
				protected void result(final Object object) {
					game.setScreen(new SlideScreen(game));
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
		image.dispose();
		backgroundImage.dispose();
		stage.dispose();
	}

}
