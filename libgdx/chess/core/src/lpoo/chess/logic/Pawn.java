package lpoo.chess.logic;

import java.util.ArrayList;

public class Pawn extends Character
{
	int moveCount = 0;
	
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

	@Override
	public ArrayList<Pair<Integer, Integer>> getPossible(Map map)
	{
		ArrayList<Pair<Integer, Integer>> ret = new ArrayList<Pair<Integer, Integer>>();
		Pair<Integer, Integer> relativePos = new Pair<Integer, Integer>();
		Pair<Integer, Integer> absolutePos = new Pair<Integer, Integer>();
		
		if (pos.getSecond() != 1 && pos.getSecond() != 6) //PROVISIONAL
			moveCount = 1;
		
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
	
	public void loadTexture()
	{
		if (player == 0)//White
		{
			super.setTexture("../core/src/lpoo/chess/gui/images/white/pawn.png");
		}
		else			//Black
		{
			super.setTexture("../core/src/lpoo/chess/gui/images/black/pawn.png");
		}
	}
}
