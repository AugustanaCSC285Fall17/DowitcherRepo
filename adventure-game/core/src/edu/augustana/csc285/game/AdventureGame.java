package edu.augustana.csc285.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import edu.augustana.csc285.game.datamodel.*;

public class AdventureGame extends Game {
	public static final int GAME_SCREEN_WIDTH = 1200;
	public static final int GAME_SCREEN_HEIGHT = 720;
	Story story;
	StoryManager manager;
	SpriteBatch batch;
	BitmapFont font;
	Music defaultMusic;
	Skin defaultSkin;
	Skin scrollSkin;
	Texture backgroundImage;
	Sound buttonPressed;
	BitmapFont descFont;
	int size;

	public void create() {
		descFont = new BitmapFont(Gdx.files.internal("fonts/defaultFont.fnt"), false);
		size = 3;
		buttonPressed = Gdx.audio.newSound(Gdx.files.internal("music/sound/button_press.wav"));
		story = Story.fromJSON(Gdx.files.internal("storyData/officialStory.json").readString("UTF-8"));
		this.initializeManager();
		defaultSkin = new Skin(Gdx.files.internal("skin/defaultSkin/cloud-form-ui.json"));
		scrollSkin = new Skin(Gdx.files.internal("skin/Holo-dark-mdpi.json"));
		backgroundImage = new Texture("image/icon/other/background.jpg");

		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("fonts/defaultFont.fnt"), false);
		defaultMusic = Gdx.audio.newMusic(Gdx.files.internal("theme.mp3"));
		defaultMusic.setLooping(true);
		defaultMusic.play();
		this.setScreen(new MainMenuScreen(this, false));
	}

	public void setDescFont(int size) {
		if (size != this.size) {
			this.size = size;
			descFont = new BitmapFont(Gdx.files.internal("fonts/defaultFont.fnt"), false);
		}
	}

	public void initializeManager() {
		manager = new StoryManager(story, "", story.getStartingSlideIndex());
	}

	public void render() {
		super.render(); // important!
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
		defaultMusic.dispose();
		defaultSkin.dispose();
		scrollSkin.dispose();
		backgroundImage.dispose();
		buttonPressed.dispose();
	}
}
