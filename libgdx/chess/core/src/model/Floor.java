package model;

import java.util.ArrayList;

/**
 *
 * Represents a Floor.
 *
 */
public class Floor extends Character
{
	int moveCount = 0;

	/**
	 *
	 * Constructs a Floor.
	 *
	 */
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

	/**
	 *
	 * Returns the possible movements of a specific character
	 *
	 * @param map Game Map.
	 *
	 * @return a pair with position after a move.
	 */
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
