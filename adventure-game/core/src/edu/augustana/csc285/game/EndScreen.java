package edu.augustana.csc285.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class EndScreen implements Screen {
	public static final Skin DEFAULT_SKIN  = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));

    private AdventureGame game;
    private OrthographicCamera camera;
	private Stage stage;

    public EndScreen(AdventureGame game) {
    	
        this.game = game;
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false,AdventureGame.GAME_SCREEN_WIDTH,
                AdventureGame.GAME_SCREEN_HEIGHT);
        
        Label testLabel = new Label("Your Score:....... Final TEXT/Info about the centre", DEFAULT_SKIN);
		testLabel.setWrap(true);
		ScrollPane pane = new ScrollPane(testLabel, DEFAULT_SKIN);
		pane.setPosition(400, 250);
		pane.setSize(200, 1500);
		stage.addActor(pane);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.font.draw(game.batch, "GAME OVER", 400,50);
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
