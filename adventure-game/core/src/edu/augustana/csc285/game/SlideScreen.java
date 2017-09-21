package edu.augustana.csc285.game;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import edu.augustana.csc285.game.datamodel.Option;
import edu.augustana.csc285.game.datamodel.Slide;

public class SlideScreen implements Screen {
	private final int RIGHT_BUFFER = 5;
	private final int BUFFER = 20;
	private AdventureGame game;
	private Slide slide;
	private Texture image;
	private OrthographicCamera camera;
	private String desc;
	private String directions; 
	ArrayList<String> options;
	
	public SlideScreen(AdventureGame game) {
		this.game = game;
		this.image = new Texture("slideImages/Swedish-Fish-Wrapper-Small.jpg");
		this.desc = new String("This is the description of the image.");
		this.directions = new String("You arrive in Sweden and are handed a large package of strange looking fish.");
		this.options = new ArrayList<String>(
				Arrays.asList("Eat the strange fish.", "Attempt to trade them with nearby shop owner.", "Throw them away.", "Travel to Narnia."));
		camera = new OrthographicCamera();
		camera.setToOrtho(false, AdventureGame.GAME_SCREEN_WIDTH, 
								AdventureGame.GAME_SCREEN_HEIGHT);
	}
	
	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		game.batch.draw(image, AdventureGame.GAME_SCREEN_WIDTH - image.getWidth() - RIGHT_BUFFER, 
						(AdventureGame.GAME_SCREEN_HEIGHT - image.getHeight()) / 2); // this centers the image height-wise
		game.font.draw(game.batch, directions + "\n\nWhat would you like to do?", BUFFER, 350);
		
		game.font.draw(game.batch, desc, AdventureGame.GAME_SCREEN_WIDTH - image.getWidth() - RIGHT_BUFFER,
						AdventureGame.GAME_SCREEN_HEIGHT - image.getHeight() - BUFFER);
		
		for (int i = 0; i < options.size(); i++) {
			game.font.draw(game.batch, i + 1 + ". " + options.get(i), BUFFER, 200 - BUFFER * i);
		}
		
		game.font.draw(game.batch, "What would you like to do? __", BUFFER, 200 - BUFFER * options.size() - 30);		
		game.batch.end();
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose () {
		image.dispose();
	}

}


