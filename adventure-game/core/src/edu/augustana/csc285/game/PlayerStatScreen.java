package edu.augustana.csc285.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class PlayerStatScreen implements Screen {
	public static final int GAME_SCREEN_WIDTH = 800;
	public static final int GAME_SCREEN_HEIGHT = 480;
	
	private Player player;
	private OrthographicCamera camera;
	private AdventureGame game;

	public PlayerStatScreen(final AdventureGame game) {
		this.game = game;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, AdventureGame.GAME_SCREEN_WIDTH, 
								AdventureGame.GAME_SCREEN_HEIGHT);
		Skin skin = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));
		//still to be completed
	}
@Override
public void render (float delta) {
	Gdx.gl.glClearColor(0, 0, 0, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
	img.dispose();
}
}