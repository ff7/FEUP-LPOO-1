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
}
