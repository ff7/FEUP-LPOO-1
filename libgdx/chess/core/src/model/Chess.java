package model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import view.Graphics;
import view.mainMenuGraphics;


/**
 * Game class, basically the highest scope of the program. Keeps the opponentType and the stockfishPath to be accessible to every menu.
 * 
 */
public class Chess extends Game
{
	private SpriteBatch batch;
	private int opponentType = -1; // 0 = Single Player, 1 = Same device, 2 = Other Device
	private String stockfishPath = null;
	public int width;
	public int height;

	public Viewport fitPort, stretchPort;


	@Override
	public void create ()
	{
		batch = new SpriteBatch();

		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		fitPort = new FitViewport(500, 500);
		stretchPort = new StretchViewport(500, 500);

		setScreen(new mainMenuGraphics(this));
	}

	
	@Override
	public void render ()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		super.render();
	}

	@Override
	public void resize(int w, int h)
	{
		if (screen instanceof Graphics)
			fitPort.update(w, h);
		else
			stretchPort.update(w, h);
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


	@Override
	public void dispose () {
		batch.dispose();
	}

}
