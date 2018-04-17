package chess.logic;

import java.util.ArrayList;

public class Knight extends Character
{
	int moveCount = 0;
	
	public Knight()
	{
		super('k');
	}
	
	public Knight(int player, int x, int y)
	{
		super(player, x, y, 'p');
	}

	public ArrayList<int[]> getPossible(Map map)
	{
		ArrayList<int[]> ret = new ArrayList<int[]>();
		int[] relativePos = new int[2]; // {x, y}
		int[] absolutePos = new int[2]; // {x, y}
				
		int xadd, yadd;
		
		for (int i = 0; i < 8; i++)
		{
			if (i == 0) //Up-Left
			{
				xadd = -1;
				yadd = 2;
			}
			else if (i == 1) //Up-Right
			{
				xadd = 1;
				yadd = 2;
			}
			else if (i == 2) //Right-Up
			{
				xadd = 2;
				yadd = 1;
			}
			else if (i == 3) //Right-Down
			{
				xadd = 2;
				yadd = -1;
			}
			else if (i == 4) //Down-Right
			{
				xadd = 1;
				yadd = -2;
			}
			else if (i == 5) //Down-Left
			{
				xadd = -1;
				yadd = -2;
			}
			else if (i == 6) //Left-Down
			{
				xadd = -2;
				yadd = -1;
			}
			else // Left-Up
			{
				xadd = -2;
				yadd = 1;
			}
			
			relativePos[0] = xadd;
			relativePos[1] = yadd;
			absolutePos = this.getMovePosition(relativePos);

						
			if (map.isWithinBounds(absolutePos) && (map.getMap()[absolutePos[1]][absolutePos[0]] instanceof Floor || map.getMap()[absolutePos[1]][absolutePos[0]].player != this.player))
			{
				ret.add(absolutePos);
			}
		}
		
		
		return ret;
	}
}
