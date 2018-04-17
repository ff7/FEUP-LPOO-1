package chess.cli;

import java.util.ArrayList;

import chess.logic.*;
import chess.logic.Character;


public class cli
{

	public static void test()
	{
		GameState gs = new GameState();
		
		gs.getMap().getMap()[3][3].setPlayer(0);	//Queen
		gs.getMap().getMap()[5][1].setPlayer(0);	//Queen
		gs.getMap().getMap()[4][2].setPlayer(1);	//King
		gs.getMap().getMap()[2][2].setPlayer(1);	//Knight
		gs.getMap().getMap()[5][5].setPlayer(1);	//Pawn
		gs.getMap().getMap()[1][3].setPlayer(1);	//Bishop
		gs.getMap().getMap()[4][3].setPlayer(0);	//Pawn
		gs.getMap().getMap()[3][0].setPlayer(1);	//Knight
		gs.getMap().getMap()[3][5].setPlayer(1);	//King
		
		gs.getMap().printMap();
		
		System.out.println("Bishop position: " + 3 + ", " + 3 + "\n");

		ArrayList<int[]> foo = gs.getMap().getMap()[3][3].getPossible(gs.getMap());
		
		for (int i = 0; i < foo.size(); i++)
		{
			System.out.println(foo.get(i)[0] + ", " + foo.get(i)[1]);
		}
			
	}
	
	public static void main(String[] args)
	{
		GameState gs = new GameState();
	}

}
