package lpoo.chess.logic;

import java.util.ArrayList;

public class GameState
{		
	public int gameStatus = 0; // -1 = game over, 0 = normal, 1 = win

	protected Map map; 

	
	public GameState()
	{ 
		map = new Map();
		
		getMap().getMap()[3][4].setPlayer(0);	//Knight
		getMap().getMap()[5][1].setPlayer(0);	//Queen
		getMap().getMap()[4][2].setPlayer(0);	//King
		getMap().getMap()[2][2].setPlayer(1);	//Knight
		getMap().getMap()[5][5].setPlayer(1);	//Pawn
		getMap().getMap()[1][3].setPlayer(1);	//Bishop
		getMap().getMap()[4][3].setPlayer(0);	//Pawn
		getMap().getMap()[3][0].setPlayer(1);	//Knight
		getMap().getMap()[3][5].setPlayer(1);	//King
		getMap().getMap()[6][0].setPlayer(1);	//Rook
		getMap().getMap()[6][2].setPlayer(0);	//Pawn
		getMap().getMap()[6][4].setPlayer(0);	//Pawn
		getMap().getMap()[4][6].setPlayer(0);	//Bishop
		
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