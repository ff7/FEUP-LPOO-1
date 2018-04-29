package model;

import java.util.ArrayList;

public class Rook extends Character
{
	int moveCount = 0;
	
	public Rook()
	{
		super('R');
	}
	
	public Rook(int player, int x, int y)
	{
		super(player, x, y, 'R');
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
	public ArrayList<Pair<Integer, Integer>> getPossible(Map map)
	{
		ArrayList<Pair<Integer, Integer>> ret = new ArrayList<Pair<Integer, Integer>>();
		Pair<Integer, Integer> relativePos = new Pair<Integer, Integer>();
		Pair<Integer, Integer> absolutePos = new Pair<Integer, Integer>();
				
		int xadd, yadd;
		
		for (int i = 0; i < 4; i++)
		{
			if (i == 0) //Up
			{
				xadd = 0;
				yadd = 1;
			}
			else if (i == 1) //Left
			{
				xadd = -1;
				yadd = 0;
			}
			else if (i == 2) //Down
			{
				xadd = 0;
				yadd = -1;
			}
			else //Right
			{
				xadd = 1;
				yadd = 0;
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
		if (player == 0)//White
		{
			filename += "white/";
		}
		else			//Black
		{
			filename += "black/";
		}
		
		super.setTexture(filename + "rook.png");
	}
}
