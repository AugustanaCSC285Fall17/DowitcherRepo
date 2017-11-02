package edu.augustana.csc285.game;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {
	public AssetManager manager = new AssetManager();
	public static final AssetDescriptor<Texture> backgroundImage = new AssetDescriptor<Texture>(
			"image/icon/other/background.jpg", Texture.class);
	public static final AssetDescriptor<Skin> defaultSkin = new AssetDescriptor<Skin>(
			"skin/defaultSkin/cloud-form-ui.json", Skin.class);
	public static final AssetDescriptor<Skin> scrollSkin = new AssetDescriptor<Skin>("skin/Holo-dark-mdpi.json",
			Skin.class);
	public static final AssetDescriptor<Music> defaultMusic = new AssetDescriptor<Music>("music/background/theme.mp3",
			Music.class);
	public static final AssetDescriptor<Sound> buttonPressed = new AssetDescriptor<Sound>(
			"music/sound/button_press.wav", Sound.class);

	public void load() {
		manager.load(backgroundImage);
		manager.load(defaultSkin);
		manager.load(scrollSkin);
		manager.load(defaultMusic);
		manager.load(buttonPressed);
	}

	public void dispose() {
		manager.dispose();
	}
}
