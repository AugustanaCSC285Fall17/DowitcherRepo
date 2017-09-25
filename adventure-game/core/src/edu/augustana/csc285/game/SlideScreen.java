package edu.augustana.csc285.game;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import edu.augustana.csc285.game.datamodel.Slide;

public class SlideScreen implements Screen {
	private final int LEFT_BUFFER = 5;
	private final int BUFFER = 20;
	private AdventureGame game;
	private Slide slide;
	private Texture image;
	private List<String> options;
	private OrthographicCamera camera;
	
	public SlideScreen(AdventureGame game) {
		slide = new Slide("test_image", "Directions:", "test url", "", 0, null);
		//can test for different image with changing test_image to slide_000
		this.game = game;
		options = new ArrayList<String>();
		for (int i = 0; i <= 3; i++) {
			options.add("Go west");
		}
		this.image = new Texture("slideImages/" + slide.getImage() + ".png");
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
		game.batch.draw(image, BUFFER, 
				(AdventureGame.GAME_SCREEN_HEIGHT - image.getHeight()) / 2); // this centers the image height-wise 
		//Draws the url or image description
		game.font.draw(game.batch, slide.getUrl(), BUFFER,
				AdventureGame.GAME_SCREEN_HEIGHT / 2 - image.getHeight() / 2 - 5);
		//Draws the description or the directions for the current slide
		game.font.draw(game.batch, slide.getDesc() + "\n\nWhat would you like to do?", image.getWidth() + 75, 350);
		//Draws the options
		for (int i = 0; i <= 3; i++) {
			game.font.draw(game.batch, i + 1 + ". " + options.get(i), image.getWidth() + 75, 250 - BUFFER * i);
		}
		
		game.font.draw(game.batch, "What would you like to do? __", image.getWidth() + 75, 250 - BUFFER * 3 - 30);		
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


