package edu.augustana.csc285.game;

import java.awt.Color;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.augustana.csc285.game.datamodel.Player;

public class PlayerStatScreen implements Screen {
	public static final Skin DEFAULT_SKIN = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));
	public static final int GAME_SCREEN_WIDTH = 800;
	public static final int GAME_SCREEN_HEIGHT = 480;
	private Viewport viewport;
	private Stage stage;
	private Player player;
	private OrthographicCamera camera;
	private AdventureGame game;

	public PlayerStatScreen(final AdventureGame game) {
		this.game = game;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, AdventureGame.GAME_SCREEN_WIDTH, 
								AdventureGame.GAME_SCREEN_HEIGHT);
		Label.LabelStyle font = new Label.LabelStyle();
		Table table = new Table();
		table.top();
		table.setFillParent(true);
	    
		
		Label playerStatLabel = new Label ("Player Statistics!", font);
		table.add(playerStatLabel);
		stage.addActor(table);
		
		//table.center();
		//table.add("Players'")
		
		
	}
@Override
public void render (float delta) {
	Gdx.gl.glClearColor(0, 0, 0, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	stage.draw();
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
	//img.dispose();
}
}