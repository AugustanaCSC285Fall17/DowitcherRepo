package edu.augustana.csc285.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import edu.augustana.csc285.game.datamodel.Option;
import edu.augustana.csc285.game.datamodel.Slide;
import edu.augustana.csc285.game.datamodel.Story;
import edu.augustana.csc285.game.datamodel.StoryManager;

public class AdventureGame extends Game {
	public static final int GAME_SCREEN_WIDTH = 800;
	public static final int GAME_SCREEN_HEIGHT = 480;
	StoryManager manager;
	SpriteBatch batch;
	BitmapFont font;
	public void create() {
		// Note to get a string representing story
//		String jsonData = "";
//		Story story = Story.fromJSON(jsonData);
		Story story = new Story(0);
		Slide slide0 = new Slide("slideImages/test_image.png", "Slide 0",0);
		slide0.addOption(new Option("Go to 1",1));
		slide0.addOption(new Option("Go to 2",2));
		story.addSlide(slide0);
		Slide slide1 = new Slide("slideImages/test_image2.png", "Slide 1",1);
		slide1.addOption(new Option("Go to 0",0));
		slide1.addOption(new Option("Go to 2",2));
		story.addSlide(slide1);
		Slide slide2 = new Slide("slideImages/test_image2.png", "Slide 1",2);
		slide1.addOption(new Option("Go to 0",0));
		slide1.addOption(new Option("Go to 1",1));
		story.addSlide(slide1);
		
		
		manager = new StoryManager(story,"default_name",story.getStartingSlideIndex());
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));
	}

	public void render() {
		super.render(); //important!
	}
	
	public void dispose() {
		batch.dispose();
		font.dispose();
	}

	
}
