package lpoo.chess.logic;

import java.util.ArrayList;

public class Floor extends Character
{
	int moveCount = 0;
	
	public Floor()
	{
		super('_');
	}
	
	public Floor(int x, int y)
	{
		this();
		
		super.x = x;
		super.y = y;
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
		return new ArrayList<int[]>();
	}
	
	public void loadTexture()
	{
		
	}
}
