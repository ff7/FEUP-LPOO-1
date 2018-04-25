package lpoo.chess;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lpoo.chess.gui.Graphics;
import lpoo.chess.gui.MyMouseHandler;

public class Chess extends ApplicationAdapter {
	SpriteBatch batch;
		
	Graphics graphics;
	
	@Override
	public void create () {
		
		batch = new SpriteBatch();

	    graphics = new Graphics(batch);
	    Gdx.input.setInputProcessor(graphics);
	}

	
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		graphics.display();		
		
	}
	
	
	@Override
	public void dispose () {
		batch.dispose();
		
	}
}
