package edu.augustana.csc285.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.*;


import java.util.ArrayList;
import edu.augustana.csc285.game.datamodel.Story;
/**
 * Allows users to select different stories to load.
 * Work in progress
 */
public class StorySelectionScreen implements Screen{

    private final AdventureGame game;
    private OrthographicCamera camera;
    private Stage stage;

    private FileHandle handle;
    private String[] fileNames;

    public StorySelectionScreen(AdventureGame game) {

        this.game = game;

        this.camera = new OrthographicCamera();
        camera.setToOrtho(false,AdventureGame.GAME_SCREEN_WIDTH,AdventureGame.GAME_SCREEN_HEIGHT);

        this.stage = new Stage(new ScreenViewport());


      //  ArrayList<FileHandle> storyFiles = Story
        this.fileNames = new String[storyFiles.size()];
       // for() {

        //}



    //}

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
