package edu.augustana.csc285.game.desktop;

import java.util.ArrayList;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import edu.augustana.csc285.game.AdventureGame;
import edu.augustana.csc285.game.datamodel.Option;
import edu.augustana.csc285.game.datamodel.Slide;


public class DesktopLauncher {
	private static Slide slide;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Adventure Game";
		config.width = AdventureGame.GAME_SCREEN_WIDTH;
		config.height = AdventureGame.GAME_SCREEN_HEIGHT;
		new LwjglApplication(new AdventureGame(), config);
	}
}
