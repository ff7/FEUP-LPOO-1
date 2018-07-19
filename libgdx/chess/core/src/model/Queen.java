package model;

import java.util.ArrayList;


/**
 *
 * Represents a Queen.
 *
 */
public class Queen extends Character
{
	/**
	 *
	 * Constructs a Queen.
	 *
	 */
	public Queen()
	{
		super('Q');
	}
	
	public Queen(int player, int x, int y)
	{
		super(player, x, y, 'Q');
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
				
		int xadd, yadd;
		
		for (int i = 0; i < 8; i++)
		{
			if (i == 0) //Up
			{
				xadd = 0;
				yadd = 1;
			}
			else if (i == 1) //Up-Right
			{
				xadd = 1;
				yadd = 1;
			}
			else if (i == 2) //Right
			{
				xadd = 1;
				yadd = 0;
			}
			else if (i == 3) //Down-Right
			{
				xadd = 1;
				yadd = -1;
			}
			else if (i == 4) //Down
			{
				xadd = 0;
				yadd = -1;
			}
			else if (i == 5) //Down-Left
			{
				xadd = -1;
				yadd = -1;
			}
			else if (i == 6) //Left
			{
				xadd = -1;
				yadd = 0;
			}
			else // Up-Left
			{
				xadd = -1;
				yadd = 1;
			}
			
			relativePos.setFirst(0);
			relativePos.setSecond(0);
			 
			while (true)
			{
				relativePos.setFirst(relativePos.getFirst() + xadd);
				relativePos.setSecond(relativePos.getSecond() + yadd);
				absolutePos = getMovePosition(relativePos);
				
				if (!map.isWithinBounds(absolutePos))
					break;
						
				if (map.getMap()[absolutePos.getSecond()][absolutePos.getFirst()] instanceof Floor)
				{
					ret.add(absolutePos);
				}
				else
				{
					if (map.getMap()[absolutePos.getSecond()][absolutePos.getFirst()].player != this.player)
					{
						ret.add(absolutePos);
					}
					
					break;
				}
			}
		}
		
		
		return ret;
	}

	/**
	 *
	 *  Loads the corresponding character texture
	 */
	public void loadTexture()
	{
		filename = new Queen().filename;

		if (player == 0)//White
		{
			filename += "white/";
		}
		else			//Black
		{
			filename += "black/";
		}
		
		super.setTexture(filename + "queen.png");
	}
}
