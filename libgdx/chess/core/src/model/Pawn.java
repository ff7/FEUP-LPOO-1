package model;

import java.util.ArrayList;

/**
 *
 * Represents a Pawn.
 *
 */
public class Pawn extends Character
{
	int moveCount = 0;

	/**
	 *
	 * Constructs a Pawn.
	 *
	 */
	public Pawn()
	{
		super('p');
//		super.setTexture("src/lpoo/chess/gui/images/pawn.png");
	}
	
	public Pawn(int player, int x, int y)
	{
		super(player, x, y, 'p');
//		super.setTexture("src/lpoo/chess/gui/images/pawn.png");
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
		ArrayList<Pair<Integer, Integer>> ret = new ArrayList<Pair<Integer, Integer>>();
		Pair<Integer, Integer> relativePos = new Pair<Integer, Integer>();
		Pair<Integer, Integer> absolutePos = new Pair<Integer, Integer>();
		
		
		relativePos.setFirst(0);
		relativePos.setSecond(1);
		absolutePos = this.getMovePosition(relativePos);
		
		if (map.isWithinBounds(absolutePos) && map.getMap()[absolutePos.getSecond()][absolutePos.getFirst()] instanceof Floor)
		{
			ret.add(absolutePos);
			
			if (moveCount == 0)
			{	
				relativePos.setSecond(2);
				absolutePos = this.getMovePosition(relativePos);
				
				if (map.isWithinBounds(absolutePos) && map.getMap()[absolutePos.getSecond()][absolutePos.getFirst()] instanceof Floor)
					ret.add(absolutePos);
			}
		}

		//Capture
		relativePos.setFirst(1);
		relativePos.setSecond(1);
		absolutePos = this.getMovePosition(relativePos);
		
		if (map.isWithinBounds(absolutePos) && !(map.getMap()[absolutePos.getSecond()][absolutePos.getFirst()] instanceof Floor) && map.getMap()[absolutePos.getSecond()][absolutePos.getFirst()].getPlayer() != this.player)
			ret.add(absolutePos);
		
		relativePos.setFirst(-1);
		absolutePos = this.getMovePosition(relativePos);
		
		if (map.isWithinBounds(absolutePos) && !(map.getMap()[absolutePos.getSecond()][absolutePos.getFirst()] instanceof Floor) && map.getMap()[absolutePos.getSecond()][absolutePos.getFirst()].getPlayer() != this.player)
			ret.add(absolutePos);
		
		
		return ret;
	}

	/**
	 *
	 *  Loads the corresponding character texture
	 */
	public void loadTexture()
	{
		filename = new Pawn().filename;

		if (player == 0)//White
		{
			filename += "white/";
		}
		else			//Black
		{
			filename += "black/";
		}
		
		super.setTexture(filename + "pawn.png");
	}
}
