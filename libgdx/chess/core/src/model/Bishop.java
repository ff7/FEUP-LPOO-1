package model;

import java.util.ArrayList;

public class Bishop extends Character
{
	int moveCount = 0;
	
	public Bishop()
	{
		super('B');
	}
	
	public Bishop(int player, int x, int y)
	{
		super(player, x, y, 'B');
	}

	@Override
	public ArrayList<Pair<Integer, Integer>> getPossible(Map map)
	{
		ArrayList<Pair<Integer, Integer>> ret = new ArrayList<Pair<Integer, Integer>>();
		Pair<Integer, Integer> relativePos = new Pair<Integer, Integer>();
		Pair<Integer, Integer> absolutePos = new Pair<Integer, Integer>();
				
		int xadd, yadd;
		
		for (int i = 0; i < 4; i++)
		{
			if (i == 0) //Up-Right
			{
				xadd = 1;
				yadd = 1;
			}
			else if (i == 1) //Up-Left
			{
				xadd = -1;
				yadd = 1;
			}
			else if (i == 2) //Down-Right
			{
				xadd = 1;
				yadd = -1;
			}
			else //Down-Left
			{
				xadd = -1;
				yadd = -1;
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
	
	public void loadTexture()
	{
		filename = new Bishop().filename;

		if (player == 0)//White
		{
			filename += "white/";
		}
		else			//Black
		{
			filename += "black/";
		}
		
		super.setTexture(filename + "bishop.png");
	}
}
