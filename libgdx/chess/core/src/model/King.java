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
	public int moveCount = 0;

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

			if (map.getCharMap()[7][4] == 'K' && map.getMap()[7][4].player == 0 && map.getCharMap()[7][7] == 'R' && map.getMap()[7][7].player == 0 & this.player == 0 && moveCount == 0)
			{
				if (map.getCharMap()[7][5] == '_' && map.getCharMap()[7][6] == '_')
				{
					Pair<Integer, Integer> castleOption = new Pair<Integer, Integer>(6,7);
					ret.add(castleOption);
				}
			}
			if (map.getCharMap()[7][4] == 'K' && map.getMap()[7][4].player == 0 && map.getCharMap()[7][0] == 'R' && map.getMap()[7][0].player == 0 && this.player == 0 && moveCount == 0)
			{
				if (map.getCharMap()[7][1] == '_' && map.getCharMap()[7][2] == '_' && map.getCharMap()[7][3] == '_')
				{
					Pair<Integer, Integer> castleOption = new Pair<Integer, Integer>(2,7);
					ret.add(castleOption);
				}
			}

			if (map.getCharMap()[0][4] == 'K' && map.getMap()[0][4].player == 1 && map.getCharMap()[0][7] == 'R' && map.getMap()[0][7].player == 1 && this.player == 1 && moveCount == 0)
			{
				if (map.getCharMap()[0][5] == '_' && map.getCharMap()[0][6] == '_')
				{
					Pair<Integer, Integer> castleOption = new Pair<Integer, Integer>(6,0);
					ret.add(castleOption);
				}
			}
			if (map.getCharMap()[0][4] == 'K' && map.getMap()[0][4].player == 1 && map.getCharMap()[0][0] == 'R' && map.getMap()[0][0].player == 1 && this.player == 1 && moveCount == 0)
			{
				if (map.getCharMap()[0][1] == '_' && map.getCharMap()[0][2] == '_' && map.getCharMap()[0][3] == '_')
				{
					Pair<Integer, Integer> castleOption = new Pair<Integer, Integer>(2,0);
					ret.add(castleOption);
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
