package chess.logic;

import java.util.ArrayList;

import com.sun.xml.internal.ws.message.RelatesToHeader;

public class Rook extends Character
{
	int moveCount = 0;
	
	public Rook()
	{
		super('R');
	}
	
	public Rook(int player, int x, int y)
	{
		super(player, x, y, 'p');
	}

	public ArrayList<int[]> getPossible()
	{
		ArrayList<int[]> ret = new ArrayList<int[]>();
		int[] foo = new int[2];
		
		foo[0] = 0;
		foo[1] = 1;
		ret.add(foo);
		
		if (moveCount == 0)
		{	
			foo[1] = 2;
			ret.add(foo);
		}
		else
		{
			
		}
		
		return null;
	}
	
	@Override
	public ArrayList<int[]> getPossible(Map map)
	{
		ArrayList<int[]> ret = new ArrayList<int[]>();
		int[] relativePos = new int[2]; // {x, y}
		int[] absolutePos = new int[2]; // {x, y}
				
		int xadd, yadd;
		
		for (int i = 0; i < 4; i++)
		{
			if (i == 0) //Up
			{
				xadd = 0;
				yadd = 1;
			}
			else if (i == 1) //Left
			{
				xadd = -1;
				yadd = 0;
			}
			else if (i == 2) //Down
			{
				xadd = 0;
				yadd = -1;
			}
			else //Right
			{
				xadd = 1;
				yadd = 0;
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
