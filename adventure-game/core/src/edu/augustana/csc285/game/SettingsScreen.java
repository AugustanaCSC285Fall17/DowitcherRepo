package edu.augustana.csc285.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import edu.augustana.csc285.game.datamodel.Player;

public class SettingsScreen implements Screen {
	public static final Skin DEFAULT_SKIN = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));
	public static final int GAME_SCREEN_WIDTH = 800;
	public static final int GAME_SCREEN_HEIGHT = 480;
	private final int BUFFER = 5;
	private Viewport viewport;
	private Stage stage;
	private Player player;
	private OrthographicCamera camera;
	private AdventureGame game;
	private Texture img;
	
	
	public SettingsScreen(AdventureGame game) {
		this.game = game;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, AdventureGame.GAME_SCREEN_WIDTH, 
								AdventureGame.GAME_SCREEN_HEIGHT);
		
		Label.LabelStyle font = new Label.LabelStyle();
		Table indextable = new Table();
		indextable.setPosition(450, 150);
		indextable.top();
		indextable.setFillParent(true);
	    
		
		Label settingsLabel = new Label ("Settings Screen", font);
		indextable.add(settingsLabel);
		
		String button1 = "Sound On";
		String button2 = "Sound Off";
		TextButton buttonOn = new TextButton(button1, DEFAULT_SKIN);
		button1.getLabel().setAlignment(Align.left);
		TextButton buttonOff = new TextButton(button1, DEFAULT_SKIN);
		button2.getLabel().setAlignment(Align.right);
		
		indextable.add(button1).width(200).height(30).pad(BUFFER).row();
		indextable.add(button2).width(200).height(30).pad(BUFFER*BUFFER).row();
		
		
		stage.addActor(indextable);
		
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
