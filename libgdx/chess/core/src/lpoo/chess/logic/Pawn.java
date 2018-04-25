package lpoo.chess.logic;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;

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
	public ArrayList<int[]> getPossible(Map map)
	{
		ArrayList<int[]> ret = new ArrayList<int[]>();
		int[] relativePos = new int[2]; // {x, y}
		int[] absolutePos = new int[2]; // {x, y}
		
		relativePos[0] = 0;
		relativePos[1] = 1;
		absolutePos = this.getMovePosition(relativePos);
		
		if (map.isWithinBounds(absolutePos) && map.getMap()[absolutePos[1]][absolutePos[0]] instanceof Floor)
		{
			ret.add(absolutePos);
			
			if (moveCount == 0)
			{	
				relativePos[1] = 2;
				absolutePos = this.getMovePosition(relativePos);
				
				if (map.isWithinBounds(absolutePos) && map.getMap()[absolutePos[1]][absolutePos[0]] instanceof Floor)
					ret.add(absolutePos);
			}
		}

		//Capture
		relativePos[0] = 1;
		relativePos[1] = 1;
		absolutePos = this.getMovePosition(relativePos);
		
		if (map.isWithinBounds(absolutePos) && !(map.getMap()[absolutePos[1]][absolutePos[0]] instanceof Floor) && map.getMap()[absolutePos[1]][absolutePos[0]].getPlayer() != this.player)
			ret.add(absolutePos);
		
		relativePos[0] = -1;
		absolutePos = this.getMovePosition(relativePos);
		
		if (map.isWithinBounds(absolutePos) && !(map.getMap()[absolutePos[1]][absolutePos[0]] instanceof Floor) && map.getMap()[absolutePos[1]][absolutePos[0]].getPlayer() != this.player)
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
