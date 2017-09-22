package edu.augustana.csc285.game;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import edu.augustana.csc285.game.datamodel.Slide;

public class SlideScreen implements Screen {
	private final int RIGHT_BUFFER = 5;
	private final int BUFFER = 20;
	private AdventureGame game;
	private Slide slide;
	private Texture image;
	private OrthographicCamera camera;
	
	public SlideScreen(AdventureGame game) {
		slide = new Slide("slide_000.png", "test", "hello", "", 0, null);
		this.game = game;
		this.image = new Texture("slideImages/" + slide.getImage());
		camera = new OrthographicCamera();
		camera.setToOrtho(false, AdventureGame.GAME_SCREEN_WIDTH, 
								AdventureGame.GAME_SCREEN_HEIGHT);
	}
	
	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		//Draws the image
		game.batch.draw(image, AdventureGame.GAME_SCREEN_WIDTH - image.getWidth() - RIGHT_BUFFER, 
				(AdventureGame.GAME_SCREEN_HEIGHT - image.getHeight()) / 2); // this centers the image height-wise 
		//Draws the url or image description
		game.font.draw(game.batch, slide.getUrl(), AdventureGame.GAME_SCREEN_WIDTH - image.getWidth() - RIGHT_BUFFER,
				AdventureGame.GAME_SCREEN_HEIGHT - image.getHeight() - BUFFER);
		//Draws the description or the directions for the current slide
		game.font.draw(game.batch, slide.getDesc() + "\n\nWhat would you like to do?", BUFFER, 350);
		//Draws the options
		for (int i = 0; i < slide.getNumOptions(); i++) {
			game.font.draw(game.batch, i + 1 + ". " + slide.getOption(i), BUFFER, 200 - BUFFER * i);
		}
		
		game.font.draw(game.batch, "What would you like to do? __", BUFFER, 200 - BUFFER * slide.getNumOptions() - 30);		
		game.batch.end();
	}
	
	@Override
	public void show() {
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
	public void dispose () {
		image.dispose();
	}

}


