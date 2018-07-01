package model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import view.Graphics;
import view.mainMenuGraphics;


/**
 * Game class, basically the highest scope of the program. Keeps the opponentType and the stockfishPath to be accessible to every menu.
 * 
 */
public class Chess extends Game {
	SpriteBatch batch;
	int opponentType = 0; // 0 = Single Player, 1 = Same device, 2 = Other Device
	String stockfishPath = null;
	public int width;
	public int height;


	@Override
	public void create () {

		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		batch = new SpriteBatch();
		this.setScreen(new mainMenuGraphics(this));
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


	public void setOpponentType(int opponentType)
	{
		this.opponentType = opponentType;
	}

	public void setStockfishPath(String stockfishPath)
	{
		this.stockfishPath = stockfishPath;
	}


	public SpriteBatch getBatch()
	{
		return this.batch;
	}

	public int getOpponentType()
	{
		return opponentType;
	}

	public String getStockfishPath()
	{
		return stockfishPath;
	}

}
