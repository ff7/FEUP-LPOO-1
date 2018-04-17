package chess.logic;

import java.util.ArrayList;

public class Bishop extends Character
{
	int moveCount = 0;
	
	public Bishop()
	{
		super('B');
	}
	
	public Bishop(int player, int x, int y)
	{
		super(player, x, y, 'p');
	}

	public ArrayList<int[]> getPossible(Map map)
	{
		ArrayList<int[]> ret = new ArrayList<int[]>();
		int[] relativePos = new int[2]; // {x, y}
		int[] absolutePos = new int[2]; // {x, y}
				
		int xadd, yadd;
		
		for (int i = 0; i < 4; i++)
		{
			if (i == 0) //Up-Right
			{
				xadd = 1;
				yadd = 1;
			}
			else if (i == 1) //Up-Left
			{
				xadd = -1;
				yadd = 1;
			}
			else if (i == 2) //Down-Right
			{
				xadd = 1;
				yadd = -1;
			}
			else //Down-Left
			{
				xadd = -1;
				yadd = -1;
			}
			
			relativePos[0] = 0;
			relativePos[1] = 0;
			 
			while (true)
			{
				relativePos[0] += xadd;
				relativePos[1] += yadd;
				absolutePos = this.getMovePosition(relativePos);
				
				if (!map.isWithinBounds(absolutePos))
					break;
						
				if (map.getMap()[absolutePos[1]][absolutePos[0]] instanceof Floor)
				{
					ret.add(absolutePos);
				}
				else
				{
					if (map.getMap()[absolutePos[1]][absolutePos[0]].player != this.player)
					{
						ret.add(absolutePos);
					}
					
					break;
				}
			}
			
		}
		
		
		return ret;
	}
}
