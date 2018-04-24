package lpoo.chess;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lpoo.chess.gui.Graphics;

public class Chess extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img, img2;
	
	private BitmapFont font;
	
	Graphics graphics;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		
		img = new Texture("badlogic.jpg");
		img2 = new Texture("player.png");
		
		font = new BitmapFont();
	    font.setColor(Color.RED);
	    
	    graphics = new Graphics(batch);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.draw(img2, 400, 400);
//		font.draw(batch, "Hortaliças", 10, 10);
		
		
		
		batch.end();
		
		graphics.display();
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
