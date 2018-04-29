package model;

import java.util.ArrayList;

public class Knight extends Character
{
	int moveCount = 0;
	
	public Knight()
	{
		super('k');
	}
	
	public Knight(int player, int x, int y)
	{
		super(player, x, y, 'k');
	}

	@Override
	public ArrayList<Pair<Integer, Integer>> getPossible(Map map)
	{
		ArrayList<Pair<Integer, Integer>> ret = new ArrayList<Pair<Integer, Integer>>();
		Pair<Integer, Integer> relativePos = new Pair<Integer, Integer>();
		Pair<Integer, Integer> absolutePos = new Pair<Integer, Integer>();
				
		int xadd, yadd;
		
		for (int i = 0; i < 8; i++)
		{
			if (i == 0) //Up-Left
			{
				xadd = -1;
				yadd = 2;
			}
			else if (i == 1) //Up-Right
			{
				xadd = 1;
				yadd = 2;
			}
			else if (i == 2) //Right-Up
			{
				xadd = 2;
				yadd = 1;
			}
			else if (i == 3) //Right-Down
			{
				xadd = 2;
				yadd = -1;
			}
			else if (i == 4) //Down-Right
			{
				xadd = 1;
				yadd = -2;
			}
			else if (i == 5) //Down-Left
			{
				xadd = -1;
				yadd = -2;
			}
			else if (i == 6) //Left-Down
			{
				xadd = -2;
				yadd = -1;
			}
			else // Left-Up
			{
				xadd = -2;
				yadd = 1;
			}
			
			relativePos.setFirst(xadd);
			relativePos.setSecond(yadd);
			absolutePos = getMovePosition(relativePos);

						
			if (map.isWithinBounds(absolutePos) && (map.getMap()[absolutePos.getSecond()][absolutePos.getFirst()] instanceof Floor || map.getMap()[absolutePos.getSecond()][absolutePos.getFirst()].player != this.player))
			{
				ret.add(absolutePos);
			}
		}
		
		
		return ret;
	}
	
	public void loadTexture()
	{
		if (player == 0)//White
		{
			filename += "white/";
		}
		else			//Black
		{
			filename += "black/";
		}
		
		super.setTexture(filename + "knight.png");
	}
}
