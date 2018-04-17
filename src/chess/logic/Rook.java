package chess.logic;

import java.util.ArrayList;

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
		int[] foo = new int[2]; // {x, y}
		
		foo[0] = 1;
		foo[1] = 0;
		foo = this.getMovePosition(foo);
		
		int xadd, yadd;
		
		for (int i = 0; i < 4; i++)
		{
			if (i == 0)
			{
				xadd = 0;
				yadd = -1;
			}
			else if (i == 1)
			{
				xadd = -1;
				yadd = 0;
			}
			else if (i == 2)
			{
				xadd = 0;
				yadd = 1;
			}
			else
			{
				xadd = 1;
				yadd = 0;
			}
			
			for(; map.isWithinBounds(foo); foo[0] += xadd, foo[1] += yadd, foo = this.getMovePosition(foo))
			{
				if (map.getMap()[foo[0]][foo[1]] instanceof Floor)
				{
					ret.add(foo);
				}
				else
				{
					if (map.getMap()[foo[0]][foo[1]].player != this.player)
					{
						ret.add(foo);
					}
					
					break;
				}
			}
			
		}
		
		
		return ret;
	}
}
