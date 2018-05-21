package model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import view.Graphics;
import view.mainMenuGraphics;

public class Chess extends Game {
	SpriteBatch batch;

	@Override
	public void create () {
		
		batch = new SpriteBatch();
		this.setScreen(new mainMenuGraphics(this));
		//this.setScreen(new Graphics(this));
	}


	
	@Override
	public void render () {
		super.render();
	}
	
	
	@Override
	public void dispose () {
		batch.dispose();
		
	}

	@Override
	public void resize(int width, int height){
	}

	public SpriteBatch getBatch()
	{
		return this.batch;
	}


}
