package view;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import controller.MouseHandler;
import model.Character;
import model.GameState;
import model.Pair;

public class Graphics
{
	SpriteBatch batch;
	GameState gamestate;
	MouseHandler mousehandler;
	
	int width = -1, height, widthInc, heightInc;
	
	Texture blackFloor = new Texture("../core/src/view/images/black/floor.png");
	Texture whiteFloor = new Texture("../core/src/view/images/white/floor.png");
	Texture redFloor = new Texture("../core/src/view/images/highlight.png");
	
	ArrayList<Pair<Integer, Integer>> possible = new ArrayList<Pair<Integer, Integer>>();
	
	Character selected;
	
	
	public Graphics(SpriteBatch batch)
	{
		this.batch = batch; 
		gamestate = new GameState();
		selected = null;
		mousehandler = new MouseHandler(this);
	    Gdx.input.setInputProcessor(mousehandler);
	}
	
	
	public void display()
	{		
		batch.begin();
			
		Character[][] map = gamestate.getMap().getMap();
		
		if (width == -1)
		{
			width = Gdx.graphics.getWidth();
			height = Gdx.graphics.getHeight();
			widthInc = width / map[0].length; // Increment of the width, aka image width.
			heightInc = height / map.length; // Increment of the height, aka image height.
			
//			System.out.println(width + ", " + height);
//			System.out.println("widthInc = " + widthInc + ", heightInc = " + heightInc);
		}
		
		
		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[i].length; j++)
			{
				if ((i+j) % 2 == 0)
				{
					batch.draw(blackFloor, j*widthInc, height - (i+1)*heightInc, widthInc, heightInc);
				}
				else
				{
					batch.draw(whiteFloor, j*widthInc, height - (i+1)*heightInc, widthInc, heightInc);
				}
				
				if (map[i][j].getTexture() != null)
				{
					batch.draw(map[i][j].getTexture(), j*widthInc, height - (i+1)*heightInc, widthInc, heightInc);
				}
			}
		}
		
		if (possible != null)
		{
			int x, y;
			for (int i = 0; i < possible.size(); i++)
			{
				x = possible.get(i).getFirst();
				y = possible.get(i).getSecond();
								
				batch.draw(redFloor, x*widthInc, height - (y+1)*heightInc, widthInc, heightInc);
				
				if (map[y][x].getTexture() != null)
				{
					batch.draw(map[y][x].getTexture(), x*widthInc, height - (y+1)*heightInc, widthInc, heightInc);
				}
			}
		}

		batch.end();
	}
	
	public void swapPlayer()
	{
		if (gamestate.player == 0)
		{
			gamestate.player = 1;
		}
		else
		{
			gamestate.player = 0;
		}
	}
	
	
	public void click(int x, int y)
	{
		Character piece = getPiece(x, y);
		
		if (piece.equals(selected))
		{
			selected = null;
			possible = null;
		}
		else if (selected == null || !possible.contains(piece.getPos()))
		{
			if (piece.getPlayer() != gamestate.player)
			{
				selected = null;
				possible = null;
				return;
			}
			
			selected = piece;					
			possible = gamestate.trimGetPossible(selected, selected.getPossible(gamestate.getMap()));
		}
		else
		{
			if (!piece.equals(selected))
			{
				gamestate.move(selected, piece);
				swapPlayer();
				gamestate.updateGameStatus();
				endGame();
			}
			
			selected = null; 
			possible = null;
		}
	}
	
	
	public Character getPiece(int x, int y)
	{
		return gamestate.getMap().getMap()[y/heightInc][x/widthInc];
	}
	
	public void endGame()
	{
		if (gamestate.gameOver == true)
		{
			String winner;
			if (gamestate.winner == 0)
				winner = "White Pieces";
			else
				winner = "Black Pieces";
			System.out.println("This game is finished. " + winner + " won!");
			
			System.exit(0);
		}
	}
	
}
