package edu.augustana.csc285.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class EndScreen implements Screen {
	public static final Skin DEFAULT_SKIN = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));
	private final AdventureGame game;
	private OrthographicCamera camera;
	private Texture backgroundImage;
	private Stage stage;
	private BitmapFont titleFont;
	

	private String endgame;

	private GlyphLayout layout;

	public EndScreen(AdventureGame game) {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, AdventureGame.GAME_SCREEN_WIDTH, AdventureGame.GAME_SCREEN_HEIGHT);
		this.game = game;
		
		stage = new Stage(new ScreenViewport());
		backgroundImage = new Texture("GameData/background.jpg");
		

		camera = new OrthographicCamera();
		camera.setToOrtho(false, AdventureGame.GAME_SCREEN_WIDTH, AdventureGame.GAME_SCREEN_HEIGHT);
		stage = new Stage(new ScreenViewport());
		backgroundImage = new Texture("GameData/background.jpg");
		titleFont = new BitmapFont(Gdx.files.internal("fonts/TitleFont/title.fnt"), false);
		BitmapFont mediumTitleFont = new BitmapFont(Gdx.files.internal("fonts/TitleFont/mediumTitle.fnt"), false);

		
		Table buttonTable = new Table();
		buttonTable.setPosition(400, 250);
		
		TextButton playAgainButton = new TextButton("Play Again", DEFAULT_SKIN, "default");
		playAgainButton.setSize(50, 50);
		playAgainButton.setPosition(50, 50);
	    playAgainButton.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new MainMenuScreen(game));
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		buttonTable.add(playAgainButton).width(150).height(30).pad(5).row();
		
		
		
		
		TextButton exitButton = new TextButton("Quit Game", DEFAULT_SKIN, "default");
		exitButton.setSize(50, 50);
		exitButton.setPosition(50, 50);
		exitButton.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.exit();
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		buttonTable.add(exitButton).width(150).height(30).pad(5).row();

		stage.addActor(buttonTable);
		endgame = "GAME OVER!";
		layout = new GlyphLayout(titleFont, endgame);
	}

    @Override
    public void show() {
    	Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(backgroundImage, 0, 0, AdventureGame.GAME_SCREEN_WIDTH, AdventureGame.GAME_SCREEN_HEIGHT);
		game.batch.end();
		
		camera.update();
		stage.act();
		stage.draw();
		game.batch.begin();
		titleFont.draw(game.batch, endgame, (float) 0.39875 * AdventureGame.GAME_SCREEN_WIDTH, 
				(float) (AdventureGame.GAME_SCREEN_HEIGHT * 0.72));
		game.batch.end();
		
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
    public void dispose() {

    }
}
