package edu.augustana.csc285.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class LibGdxUtility {
	public static final String DEFAULT_SKIN_STRING = "skin/glassy-ui.json";

	public static Button createChangeScreenButton(AdventureGame game,Screen screenTo, String desc, String skinString, int x, int y,
			int height, int width) {
		Skin skin = new Skin(Gdx.files.internal(DEFAULT_SKIN_STRING));
		if (skinString != null && skinString != "") {
			skin = new Skin(Gdx.files.internal(skinString));
		}
		Button button = new TextButton(desc, skin, "small");
		button.setSize(width, height);
		button.setPosition(x, y);
		button.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(screenTo);
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

		});
		return button;
	}
}
