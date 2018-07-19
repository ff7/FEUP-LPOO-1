package model;

//import java.awt.RadialGradientPaint;
import java.util.ArrayList;

/**
 *
 * Represents a King.
 *
 */
public class King extends Character
{
	public boolean isCheck = false;
	public boolean hasCastled = false;


	/**
	 *
	 * Represents a King.
	 *
	 */
	public King()
	{
		super('K');
	}
	
	public King(int player, int x, int y) 
	{
		super(player, x, y, 'K');
	}

	/**
	 *
	 * Returns the possible movements of a specific character
	 *
	 * @param map Game Map.
	 *
	 * @return a pair with position after a move.
	 */
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
			
			relativePos.setFirst(xadd);
			relativePos.setSecond(yadd);
			absolutePos = getMovePosition(relativePos);
						
			if (map.isWithinBounds(absolutePos) && (map.getMap()[absolutePos.getSecond()][absolutePos.getFirst()] instanceof Floor || map.getMap()[absolutePos.getSecond()][absolutePos.getFirst()].player != this.player)) {
				ret.add(absolutePos);
			}

		}

		boolean flag = true;

		if (moveCount == 0 && !hasCastled)
		{
			for (int i = 1; i <= 2; i++)
			{
				if (!map.isWithinBounds(pos.getFirst() + i, pos.getSecond()) || !(map.getMap()[pos.getSecond()][pos.getFirst() + i] instanceof Floor))
				{
					flag = false;
					break;
				}
			}

			if (flag)
			{
				if (map.isWithinBounds(pos.getFirst() + 3, pos.getSecond()) && map.getMap()[pos.getSecond()][pos.getFirst() + 3] instanceof Rook && map.getMap()[pos.getSecond()][pos.getFirst() + 3].moveCount == 0)
				{
					ret.add(new Pair<Integer, Integer>(pos.getFirst() + 2, pos.getSecond()));
				}
				else if (map.isWithinBounds(pos.getFirst() + 4, pos.getSecond()) && map.getMap()[pos.getSecond()][pos.getFirst() + 3] instanceof Floor && map.getMap()[pos.getSecond()][pos.getFirst() + 4] instanceof Rook && map.getMap()[pos.getSecond()][pos.getFirst() + 4].moveCount == 0)
				{
					ret.add(new Pair<Integer, Integer>(pos.getFirst() + 2, pos.getSecond()));
				}
			}

			flag = true;
			for (int i = -1; i >= -2; i--)
			{
				if (!map.isWithinBounds(pos.getFirst() + i, pos.getSecond()) || !(map.getMap()[pos.getSecond()][pos.getFirst() + i] instanceof Floor))
				{
					flag = false;
					break;
				}
			}

			if (flag)
			{
				if (map.isWithinBounds(pos.getFirst() - 3, pos.getSecond()) && map.getMap()[pos.getSecond()][pos.getFirst() - 3] instanceof Rook && map.getMap()[pos.getSecond()][pos.getFirst() - 3].moveCount == 0)
				{
					ret.add(new Pair<Integer, Integer>(pos.getFirst() - 2, pos.getSecond()));
				}
				else if (map.isWithinBounds(pos.getFirst() - 4, pos.getSecond()) && map.getMap()[pos.getSecond()][pos.getFirst() - 3] instanceof Floor && map.getMap()[pos.getSecond()][pos.getFirst() - 4] instanceof Rook && map.getMap()[pos.getSecond()][pos.getFirst() - 4].moveCount == 0)
				{
					ret.add(new Pair<Integer, Integer>(pos.getFirst() - 2, pos.getSecond()));
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
		filename = new King().filename;

		if (player == 0)//White
		{
			filename += "white/";
		}
		else			//Black
		{
			filename += "black/";
		}
		
		super.setTexture(filename + "king.png");
	}
}
