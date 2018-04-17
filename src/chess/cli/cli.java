package chess.cli;

import chess.logic.*;
import chess.logic.Character;


public class cli
{

	public static void main(String[] args)
	{
		GameState gs = new GameState();
		
		Character[][] temp = {
				
				{new Rook(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Rook()},
				{new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
				{new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
				{new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
				{new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
				{new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
				{new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn()},
				{new Rook(), new Knight(), new Bishop(), new King(), new Queen(), new Bishop(), new Knight(), new Rook()},
	
			};
		
		gs.getMap().setMap(temp);
		

		
		
	}

}
