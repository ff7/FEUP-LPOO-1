package chess.logic;

import java.util.ArrayList;

public class Queen extends Character
{
	int moveCount = 0;
	
	public Queen()
	{
		super('Q');
	}
	
	public Queen(int player, int x, int y)
	{
		super(player, x, y, 'p');
	}

	public ArrayList<int[]> getPossible(Map map)
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
