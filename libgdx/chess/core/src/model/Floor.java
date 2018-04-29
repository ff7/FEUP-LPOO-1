package model;

import java.util.ArrayList;

public class Floor extends Character
{
	int moveCount = 0;
	
	public Floor()
	{
		super('_');
		this.setPlayer(-1);
	}
	
	public Floor(int x, int y)
	{
		this();
		setPos(new Pair<Integer, Integer>(x, y));
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
