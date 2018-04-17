package chess.logic;

import java.util.ArrayList;

public class Pawn extends Character
{
	int moveCount = 0;
	
	public Pawn()
	{
		super('p');
	}
	
	public Pawn(int player, int x, int y)
	{
		super(player, x, y, 'p');
	}

	@Override
	public ArrayList<int[]> getPossible(Map map)
	{
		ArrayList<int[]> ret = new ArrayList<int[]>();
		int[] foo = new int[2]; // {x, y}
		
		foo[0] = 0;
		foo[1] = 1;
		foo = this.getMovePosition(foo[0], foo[1]);
		
		if (map.getMap()[foo[0]][foo[1]] instanceof Floor && map.isWithinBounds(foo[0], foo[1]))
		{
			ret.add(foo);
			
			if (moveCount == 0)
			{	
				foo[1] = 2;
				foo = this.getMovePosition(foo[0], foo[1]);
				
				if (map.getMap()[foo[0]][foo[1]] instanceof Floor && map.isWithinBounds(foo[0], foo[1]))
					ret.add(foo);
			}
		}

		
		foo[0] = 1;
		foo[1] = 1;
		foo = this.getMovePosition(foo[0], foo[1]);
		
		if (!(map.getMap()[foo[0]][foo[1]] instanceof Floor) && map.isWithinBounds(foo[0], foo[1]))
			ret.add(foo);
		
		foo[0] = -1;
		foo = this.getMovePosition(foo[0], foo[1]);
		
		if (!(map.getMap()[foo[0]][foo[1]] instanceof Floor) && map.isWithinBounds(foo[0], foo[1]))
			ret.add(foo);
		
		
		return ret;
	}
}
