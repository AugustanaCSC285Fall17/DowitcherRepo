package edu.augustana.csc285.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import edu.augustana.csc285.game.datamodel.*;

public class AdventureGame extends Game {
	public static final int GAME_SCREEN_WIDTH = 800;
	public static final int GAME_SCREEN_HEIGHT = 480;
	StoryManager manager;
	SpriteBatch batch;
	BitmapFont font;
	Music testMusic;

	public void create() {
		// Define Items
		Story story = Story.fromJSON(Gdx.files.internal("storyData/testStory.json").readString());
		manager = new StoryManager(story, "Unknown", story.getStartingSlideIndex());
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("fonts/testFont.fnt"), false);
		this.setScreen(new MainMenuScreen(this));
		testMusic = Gdx.audio.newMusic(Gdx.files.internal("theme.mp3"));
		testMusic.setLooping(true);
		testMusic.play();

	}

	public void render() {
		super.render(); // important!
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}
