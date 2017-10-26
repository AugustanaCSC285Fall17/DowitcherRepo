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
	public static final int GAME_SCREEN_WIDTH = 1200;
	public static final int GAME_SCREEN_HEIGHT = 720;
	StoryManager manager;
	SpriteBatch batch;
	BitmapFont font;
	Music defaultMusic;

	public void create() {
		// Define Items
		Story story = Story.fromJSON(Gdx.files.internal("storyData/testStory.json").readString());
		manager = new StoryManager(story, "Unknown", story.getStartingSlideIndex());
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("fonts/defaultFont.fnt"), false);
		this.setScreen(new MainMenuScreen(this));
		
		defaultMusic = Gdx.audio.newMusic(Gdx.files.internal("theme.mp3"));
		defaultMusic.setLooping(true);
		defaultMusic.play();
	}

	public void render() {
		super.render(); // important!
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}
