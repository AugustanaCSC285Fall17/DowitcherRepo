package edu.augustana.csc285.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.augustana.csc285.game.datamodel.StoryManager;

public class AdventureGame extends Game {
	public static final int GAME_SCREEN_WIDTH = 800;
	public static final int GAME_SCREEN_HEIGHT = 480;
	private StoryManager manager;
	
	SpriteBatch batch;
	BitmapFont font;

	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		// line to declare the manager
		this.setScreen(new MainMenuScreen(this));
	}

	public void render() {
		super.render(); //important!
	}
	
	public void dispose() {
		batch.dispose();
		font.dispose();
	}

	public StoryManager getManager() {
		return manager;
	}
	
}
