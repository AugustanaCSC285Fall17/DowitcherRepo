package edu.augustana.csc285.game;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import edu.augustana.csc285.game.datamodel.Option;
import edu.augustana.csc285.game.datamodel.Slide;
import edu.augustana.csc285.game.datamodel.StoryManager;

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
	private TextButton inventoryButton;
	private TextButton optionsButton;
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
		buttonTable.setPosition((3*AdventureGame.GAME_SCREEN_WIDTH)/16 + WIDTH_BUFFER  , 
				AdventureGame.GAME_SCREEN_HEIGHT - ((5 * AdventureGame.GAME_SCREEN_HEIGHT) / 8) - (HEIGHT_BUFFER * 4) - AdventureGame.GAME_SCREEN_HEIGHT / 24);

		// Create and add buttons for ActionChoices
		for (int i = 0; i < visibleOptions.size(); i++) {
			Option option = visibleOptions.get(i);
			String displayString = (i + 1) + ".  " + option.getDesc();
			TextButton button = new TextButton(displayString, DEFAULT_SKIN);
			button.getLabel().setWrap(true);
			button.getLabel().setAlignment(Align.left);
			button.addListener(new InputListener() {
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					if (option.getNextSlideIndex() != -1) {
						if (option.isFeasible(game.manager.getPlayer())) {
							game.manager.applyOption(option);
						} else {
							System.out.println(option.getRejectMessage());
							// To do note: handle exception when option is not
							// feasible
						}
						game.setScreen(new SlideScreen(game));
					} else {
						game.setScreen(new EndScreen(game));
					}
				}

				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					return true;
				}
			});
			// for now not measuring the width of the option string but assume
			// they are the same
			buttonTable.add(button).width((3*AdventureGame.GAME_SCREEN_WIDTH) /8).height(AdventureGame.GAME_SCREEN_HEIGHT / 24).padTop(HEIGHT_BUFFER).row();
		}

		stage.addActor(buttonTable);
		Label testLabel = new Label(
				"This is a very very long string This is a very very long string This is a very very long string This is a very very long string This is a very very long string This is a very very long string This is a very very long string This is a very very long string This is a very very long string This is a very very long string This is a very very long string This is a very very long string This is a very very long string This is a very very long string This is a very very long string This is a very very long string This is a very very long string This is a very very long string This is a very very long string This is a very very long string This is a very very long string This is a very very long string This is a very very long string This is a very very long string",
				DEFAULT_SKIN);
		testLabel.setWrap(true);
		ScrollPane scroll = new ScrollPane(testLabel, DEFAULT_SKIN);
		scroll.setPosition((WIDTH_BUFFER * 2) + (23 * AdventureGame.GAME_SCREEN_HEIGHT) / 32, 
						AdventureGame.GAME_SCREEN_HEIGHT - (HEIGHT_BUFFER * 2) - (AdventureGame.GAME_SCREEN_HEIGHT / 10) - ((3 * AdventureGame.GAME_SCREEN_HEIGHT) / 8));
		scroll.setSize(AdventureGame.GAME_SCREEN_WIDTH / 2, AdventureGame.GAME_SCREEN_HEIGHT / 3);
		scroll.setScrollingDisabled(true, false);
		stage.addActor(scroll);

		// instantiate buttons for Options and Inventory
		optionsButton = new TextButton("Options", DEFAULT_SKIN);
		optionsButton.setSize(100, 35);

		inventoryButton = new TextButton("Inventory", DEFAULT_SKIN);
		inventoryButton.setSize(105, 35);

		optionsButton.setPosition(
				AdventureGame.GAME_SCREEN_WIDTH - inventoryButton.getWidth() - optionsButton.getWidth() - (WIDTH_BUFFER * 2),
				AdventureGame.GAME_SCREEN_HEIGHT - optionsButton.getHeight() - HEIGHT_BUFFER);
		inventoryButton.setPosition(AdventureGame.GAME_SCREEN_WIDTH - inventoryButton.getWidth() - WIDTH_BUFFER,
				AdventureGame.GAME_SCREEN_HEIGHT - inventoryButton.getHeight() - HEIGHT_BUFFER);

		stage.addActor(optionsButton);
		stage.addActor(inventoryButton);

		// Enable player to press key

		// slide = new Slide("test_image", "You appear to have stumbled upon a
		// testing slide!", "test url", "", 0);
		// // can test for different image with changing test_image to
		// // test_image2
		// numOptions = 4; // eventually change to
		// // slide.getOptionStringList().size();
		//
		// // temporary instantiation of options (for now)
		// for (int i = 0; i < numOptions; i++) {
		// options.add("Go west");
		// }
		//
		// this.image = new Texture("slideImages/" + slide.getImage() + ".png");
		// continueMessage = "";
		//
		// }
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
						} else {
							System.out.println(option.getRejectMessage());
							// To do note: handle exception when option is not
							// feasible
						}
						game.setScreen(new SlideScreen(game));
					} else {
						game.setScreen(new EndScreen(game));
					}
				}
			}
		}

		Texture testImage = new Texture("slideImages/Slide2.jpg");
		game.batch.begin();
		// game.batch.draw(region, x, y, originX, originY, width, height,
		// scaleX, scaleY, rotation);
		game.batch.draw(testImage, WIDTH_BUFFER,
				AdventureGame.GAME_SCREEN_HEIGHT - WIDTH_BUFFER - (5 * AdventureGame.GAME_SCREEN_HEIGHT) / 8 * (testImage.getWidth() / testImage.getHeight()),
				(5 * AdventureGame.GAME_SCREEN_HEIGHT) / 8,
				(5 * AdventureGame.GAME_SCREEN_HEIGHT) / 8 * (testImage.getWidth() / testImage.getHeight()));
		// game.batch.draw(region, x, y, originX, originY, width, height,
		// scaleX, scaleY, rotation);
		game.batch.end();

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		game.batch.setProjectionMatrix(camera.combined);
		//
		// if (image.getHeight() >= image.getWidth()) {
		// drawScreenForPortraitImage();
		// } else {
		// drawScreenForPortraitImage();
		// }

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
	//
	// public void drawScreenForPortraitImage() {
	// game.batch.begin();
	// // Draws the image
	// game.batch.draw(image, BUFFER, (AdventureGame.GAME_SCREEN_HEIGHT -
	// image.getHeight()) / 2); // this
	// // centers
	// // the
	// // image
	// // height-wise
	// // Draws the url or image description
	// game.font.draw(game.batch, slide.getUrl(), BUFFER,
	// AdventureGame.GAME_SCREEN_HEIGHT / 2 - image.getHeight() / 2 - 5);
	// // Draws the description or the directions for the current slide
	// game.font.draw(game.batch, slide.getDesc() + "\n\nYou may:",
	// image.getWidth() + 75, 350);
	// // Draws the options and checks user input
	// for (int i = 0; i < numOptions; i++) {
	// game.font.draw(game.batch, i + 1 + ". " + options.get(i),
	// image.getWidth() + 100, 290 - BUFFER * i);
	//
	// }
	// // Draws the prompt and includes user input when user enters a number
	// if (input > 0 && input <= numOptions) {
	// game.font.draw(game.batch, "What is your choice? " + input + "",
	// image.getWidth() + 75,
	// 290 - BUFFER * numOptions - 30);
	// } else { // Draw without input
	// game.font.draw(game.batch, "What is your choice? __", image.getWidth() +
	// 75,
	// 290 - BUFFER * numOptions - 30);
	//
	// }
	// // Draws the continue message after player presses a number
	// game.font.draw(game.batch, continueMessage, 590, 30);
	//
	// checkUserInput();
	//
	// game.batch.end();
	// }
	//
	// public void drawScreenForLandscapeImage() {
	//
	// }
	//
	// // Checks user input
	// public void checkUserInput() {
	//
	// // Continue to next slide when space is pressed
	// if (Gdx.input.isKeyPressed(Keys.SPACE)) {
	// if (input > 0 && input <= numOptions) {
	// game.setScreen(new SlideScreen(game));
	// dispose();
	// }
	// } else {
	// continueMessage = "Press SPACE BAR to continue";
	// if (Gdx.input.isKeyPressed(Keys.NUM_1)) {
	// input = 1;
	// } else if (Gdx.input.isKeyPressed(Keys.NUM_2)) {
	// input = 2;
	// } else if (Gdx.input.isKeyPressed(Keys.NUM_3)) {
	// input = 3;
	// } else if (Gdx.input.isKeyPressed(Keys.NUM_4)) {
	// input = 4;
	// }
	// }
	// }
}
