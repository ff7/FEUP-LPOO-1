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
		super(-1, x, y, '_');
	}
	
	@Override
	public ArrayList<Pair<Integer, Integer>> getPossible(Map map)
	{
		return null;
	}
	
	public void loadTexture()
	{
		texture = null;
	}
}
