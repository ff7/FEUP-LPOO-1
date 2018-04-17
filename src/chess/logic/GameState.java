package chess.logic;

import java.util.ArrayList;
import java.util.Random;

public class GameState
{		
	public int gameStatus = 0; // -1 = game over, 0 = normal, 1 = win

	protected Map map = new Map(); 

	
	public GameState()
	{ 

	}

	
	public void update(int player, int x1, int y1, int x2, int y2)
	{
		if (gameStatus != 0)
			return;

	}
	
	public boolean move(Character ch, int x, int y)
	{
		if (ch.player == 0)
		{
			y *= -1;
		}
		else
		{
			x *= -1;
		}
		
		ArrayList<int[]> possible = ch.getPossible(this.map);
		
		for (int i = 0; i < possible.size(); i++)
		{
			if (possible.get(i)[0] == ch.getX()+x && possible.get(i)[1] == ch.getY()+y)
			{
				ch.move(ch.getX()+x, ch.getY()+y);
				return true;
			}
		}
				
		return false;
	}
	
	public Map getMap()
	{
		return map;
	}

}