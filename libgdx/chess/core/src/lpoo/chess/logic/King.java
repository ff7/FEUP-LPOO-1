package lpoo.chess.logic;

import java.awt.RadialGradientPaint;
import java.util.ArrayList;

public class King extends Character
{
	int moveCount = 0;
	
	public King()
	{
		super('K');
	}
	
	public King(int player, int x, int y)
	{
		super(player, x, y, 'p');
	}

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
			super.setTexture("../core/src/lpoo/chess/gui/images/white/king.png");
		}
		else			//Black
		{
			super.setTexture("../core/src/lpoo/chess/gui/images/black/king.png");
		}
	}
}
