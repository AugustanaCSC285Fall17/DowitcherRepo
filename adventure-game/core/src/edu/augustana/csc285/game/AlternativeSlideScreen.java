package edu.augustana.csc285.game;

import java.awt.Font;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import edu.augustana.csc285.game.datamodel.*;

public class AlternativeSlideScreen implements Screen {
	private final int RIGHT_BUFFER = 5;
	private final int BUFFER = 20;
	private final AdventureGame game;
	private OrthographicCamera camera;
	private StoryManager manager;
	private Slide slide;
	private Texture image;
	private String desc;
	List<String> options;
	private String url;
	private String music;

	/**
	 * 
	 * @param game: the final AdventureGame to store reference to StoryManager
	 * Note: All fields in slides can potentially be null so needed to check before displaying
	 */
	public AlternativeSlideScreen(AdventureGame game) {
		this.game = game;
		this.manager = game.getManager();
		this.slide = manager.getCurrentSlide();
		this.image = new Texture(Gdx.files.internal(slide.getImage()));
		this.desc = slide.getDesc();
		this.options = slide.getOptionStringList();
		this.url = slide.getUrl();
		this.music = slide.getMusic();
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
		game.font.draw(game.batch,"\n\nWhat would you like to do?", BUFFER, 350);
		
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


