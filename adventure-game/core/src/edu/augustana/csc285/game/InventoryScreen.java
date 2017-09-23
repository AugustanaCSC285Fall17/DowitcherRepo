package edu.augustana.csc285.game;

import java.util.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.augustana.csc285.game.datamodel.Item;
import edu.augustana.csc285.game.datamodel.Player;


/**
 * To create a Screen that shows the player's inventory
 * 
 * @author Daniel Zwiener, Faisal Nawaz, Ryan Philp
 * 
 * 
 */
public class InventoryScreen implements Screen{

	public static final int GAME_SCREEN_WIDTH = 800;
	public static final int GAME_SCREEN_HEIGHT = 480;
	
	private Player player;
	private OrthographicCamera camera;
	private AdventureGame game;
	public SpriteBatch batch;
	public BitmapFont font;
	
	public InventoryScreen(AdventureGame game, Player player) {
		this.player = player;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT);
		this.game = game;
		
		batch = new SpriteBatch();
		font = new BitmapFont();
		
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		

		
		game.batch.begin();
		game.font.draw(batch, "This is the inventory", 50, 50);
		Map<Integer, Item> map = player.getInventory().getCollection();
		Set<Integer> set = map.keySet();
		
		ArrayList<Item> items = new ArrayList<>();
		for(int index : set) {
			items.add(map.get(index));
		}
		Texture image;
		Iterator<Integer> itr = set.iterator();
		for(int i = 0; i < 3; i++) {
			for(int j = 0; i < 4; j++) {
				if(itr.hasNext()) {
					//Draw that item
					image = new Texture (map.get(itr.next()).getImage());
				} else {
					//Draw a blank box
					image = new Texture("Box.jpg");
				}
				game.batch.draw(image, 25 * j, 25 * i);
			}
		}
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		//camera.
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
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}
