package edu.augustana.csc285.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
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
	Skin menuSkin;
	Skin scrollSkin;
	Texture backgroundImage;
	Sound buttonPressed;
	BitmapFont descFont;
	int size;
	int volumeLevel;

	public void create() {
		Assets assets = new Assets();
		assets.load();

		// option 1
		assets.manager.finishLoading();
		defaultSkin = assets.manager.get(Assets.defaultSkin);
		scrollSkin = assets.manager.get(Assets.scrollSkin);
		defaultMusic = assets.manager.get(Assets.defaultMusic);
		buttonPressed = assets.manager.get(Assets.buttonPressed);
		backgroundImage = assets.manager.get(Assets.backgroundImage);

		descFont = new BitmapFont(Gdx.files.internal("fonts/defaultFont.fnt"), false);
		size = 3;
		volumeLevel = 3;

		FileHandle file = Gdx.files.internal("storyData/officialStory.json");
		Reader reader = file.reader("utf-8");
		BufferedReader br = new BufferedReader(reader);
		StringBuilder sb = new StringBuilder();
		String str = "";
		while (str != null) {
			try {
				sb.append(str + "\n");
				str = br.readLine();
			} catch (IOException e1) {
				str = null;
			}
		}
		story = Story.fromJSON(sb.toString());
		// story =
		// Story.fromJSON(Gdx.files.internal("storyData/officialStory.json").readString("UTF-8"));

		this.initializeManager();
		// buttonPressed =
		// Gdx.audio.newSound(Gdx.files.internal("music/sound/button_press.wav"));
		// defaultSkin = new
		// Skin(Gdx.files.internal("skin/defaultSkin/cloud-form-ui.json"));
		// scrollSkin = new
		// Skin(Gdx.files.internal("skin/Holo-dark-mdpi.json"));
		// backgroundImage = new Texture("image/icon/other/background.jpg");
		// defaultMusic =
		// Gdx.audio.newMusic(Gdx.files.internal("music/background/theme.mp3"));
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("fonts/defaultFont.fnt"), false);

		defaultMusic.setLooping(true);
		defaultMusic.play();
		defaultMusic.setVolume(volumeLevel * 0.2f);
		this.setScreen(new MainMenuScreen(this, false));
	}

	public void setDescFont(int size) {
		if (size != this.size) {
			this.size = size;
			descFont = new BitmapFont(Gdx.files.internal("fonts/descriptionFont/size" + size + "/fontSize" + size + ".fnt"), false);
		}
	}

	public void setVolume(int volumeLevel) {
		if (volumeLevel != this.volumeLevel) {
			this.volumeLevel = volumeLevel;
			defaultMusic.setVolume(volumeLevel * 0.2f);
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
