package edu.augustana.csc285.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import edu.augustana.csc285.game.datamodel.ConditionOperation;
import edu.augustana.csc285.game.datamodel.Effect;
import edu.augustana.csc285.game.datamodel.EffectOperation;
import edu.augustana.csc285.game.datamodel.GameData;
import edu.augustana.csc285.game.datamodel.Gender;
import edu.augustana.csc285.game.datamodel.GenderCondition;
import edu.augustana.csc285.game.datamodel.GenderEffect;
import edu.augustana.csc285.game.datamodel.Item;
import edu.augustana.csc285.game.datamodel.ItemCondition;
import edu.augustana.csc285.game.datamodel.ItemEffect;
import edu.augustana.csc285.game.datamodel.NameEffect;
import edu.augustana.csc285.game.datamodel.Option;
import edu.augustana.csc285.game.datamodel.Player;
import edu.augustana.csc285.game.datamodel.Property;
import edu.augustana.csc285.game.datamodel.PropertyCondition;
import edu.augustana.csc285.game.datamodel.PropertyEffect;
import edu.augustana.csc285.game.datamodel.PropertyType;
import edu.augustana.csc285.game.datamodel.Slide;
import edu.augustana.csc285.game.datamodel.Story;
import edu.augustana.csc285.game.datamodel.StoryManager;

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
		font = new BitmapFont();
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
