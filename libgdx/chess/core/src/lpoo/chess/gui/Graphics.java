package lpoo.chess.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lpoo.chess.logic.GameState;
import lpoo.chess.logic.Character;

public class Graphics
{
	SpriteBatch batch;
	GameState gamestate;
	
	
	public Graphics(SpriteBatch batch)
	{
		this.batch = batch; 
		gamestate = new GameState();
	}
	
	
	public void display()
	{		
		batch.begin();
			
		Character[][] map = gamestate.getMap().getMap();
		
		int width = 0;
		int height = 0;
		int widthInc = 70; //Gdx.graphics.getWidth()/map[0].length; // Increment of the width, aka image width.
		int heightInc = 70; //Gdx.graphics.getHeight()/map.length; // Increment of the height, aka image height.
		
		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[i].length; j++)
			{
				if (map[i][j].getTexture() != null)
					batch.draw(map[i][j].getTexture(), j*widthInc, (map.length - 2)*heightInc - i*heightInc, widthInc, heightInc);
			}
		}		

		
//		batch.draw(bishop.getTexture(), 0, 0, bishop.getTexture().getWidth()/4, bishop.getTexture().getHeight()/4);
		batch.end();
	}
	
}
