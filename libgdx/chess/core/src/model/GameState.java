package model;

import java.util.ArrayList;
import java.util.HashMap;

public class GameState
{		
	public int gameStatus = 0; // -1 = game over, 0 = normal, 1 = win

	protected Map map; 
	public int player = 0;
	public boolean gameOver = false;
	public int winner = 2;

	
	public GameState()
	{ 
		map = new Map();
		
//		getMap().getMap()[3][4].setPlayer(0);	//Knight
//		getMap().getMap()[5][1].setPlayer(0);	//Queen
//		getMap().getMap()[4][2].setPlayer(0);	//King
//		getMap().getMap()[2][2].setPlayer(1);	//Knight
//		getMap().getMap()[5][5].setPlayer(1);	//Pawn
//		getMap().getMap()[1][3].setPlayer(1);	//Bishop
//		getMap().getMap()[4][3].setPlayer(0);	//Pawn
//		getMap().getMap()[3][0].setPlayer(1);	//Knight
//		getMap().getMap()[3][5].setPlayer(1);	//King
//		getMap().getMap()[6][0].setPlayer(1);	//Rook
//		getMap().getMap()[6][2].setPlayer(0);	//Pawn
//		getMap().getMap()[6][4].setPlayer(0);	//Pawn
//		getMap().getMap()[4][6].setPlayer(0);	//Bishop
		
	}

	
	public void update(int player, int x1, int y1, int x2, int y2)
	{
		if (gameStatus != 0)
			return;

	}
	
	public void move(Character ch, int x, int y)
	{
		move(ch, map.getMap()[y][x]);
	}
	
	public void move(Character ch1, Character ch2) // Moves ch1 to ch2
	{
		map.move(ch1, ch2);
	}
	
	public Map getMap()
	{
		return map;
	}
	
	public int otherPlayer()
	{
		if (player == 0)
			return 1;
		return 0;
	}
	
	public void updateGameStatus()
	{
		Pair<Integer, Integer> kingPos = this.getMap().getKingsPosition(player);
		
		if (verifyCheck(otherPlayer(), kingPos))
		{
			this.getMap().getMap()[kingPos.getFirst()][kingPos.getSecond()].isCheck = true;
			if (verifyCheckMate(otherPlayer(), kingPos))
			{
				System.out.println("CheckMate");
				winner = otherPlayer();
				gameOver = true;
			}
		}
		else 
		{
			this.getMap().getMap()[kingPos.getFirst()][kingPos.getSecond()].isCheck = false;
		}
	} 
	
	public boolean verifyCheck(int p, Pair<Integer, Integer> kingPos)
	{ 
		for (int i = 0; i < this.getMap().getMap().length; i++)
		{
			for (int j = 0; j < this.getMap().getMap()[i].length; j++)
			{
				if (this.getMap().getMap()[j][i].getPossible(getMap()) != null)
				{
					if (this.getMap().getMap()[j][i].getPossible(getMap()).contains(kingPos) && this.getMap().getMap()[j][i].getPlayer() == p)
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	public boolean verifyCheckMate(int p, Pair<Integer, Integer> kingPos)
	{
		HashMap<Pair<Integer, Integer>, Boolean> possibleMoves = new HashMap<Pair<Integer, Integer>, Boolean>();
		 
		for (int k = 0; k < this.getMap().getMap()[kingPos.getSecond()][kingPos.getFirst()].getPossible(getMap()).size(); k++)
		{ 
			possibleMoves.put(this.getMap().getMap()[kingPos.getSecond()][kingPos.getFirst()].getPossible(getMap()).get(k), false);
		}
		
		for (int i = 0; i < this.getMap().getMap().length; i++)
		{
			for (int j = 0; j < this.getMap().getMap()[i].length; j++) // Estes 2 loops percorrem o mapa
			{
				if (this.getMap().getMap()[j][i].getPossible(getMap()) != null) 
				{
					for (HashMap.Entry<Pair<Integer, Integer>, Boolean> entry : possibleMoves.entrySet()) // Percorre os moves possiveis do rei em check
					{
						if (this.getMap().getMap()[j][i].getPossible(getMap()).contains(entry.getKey()) && this.getMap().getMap()[j][i].getPlayer() == p) // Ve se ha pecas da equipa adversaria a fazer check aos moves do rei
						{
							testA(this.getMap().getMap()[j][i].getPossible(getMap()), this.getMap().getMap()[j][i]);
							entry.setValue(true);
						}
					}
				}
			}
		}
		
		for (HashMap.Entry<Pair<Integer, Integer>, Boolean> entry : possibleMoves.entrySet())
		{
			testValues(entry.getKey(), entry.getValue());
			if (entry.getValue() == false)
				return false;
		}
		return true;
	}
	
	public void testValues(Pair<Integer, Integer> key, boolean value)
	{
		System.out.println(key.getFirst() + "-" + key.getSecond() + " : " + value);
	}
	
	public void testA(ArrayList<Pair<Integer, Integer>> a, Character b)
	{
		for (int i = 0; i < a.size(); i++)
		{
			System.out.println(a.get(i).getFirst() + " - " + a.get(i).getSecond() + " - " + b.getPlayer());
			if (a.get(i).getFirst() == 3 && a.get(i).getFirst() == 1)
			{
				System.out.println(b.getChar() + " " + b.getPlayer());
			}
		}
	}
	
	public ArrayList<Pair<Integer, Integer>> trimGetPossible(Character ch, ArrayList<Pair<Integer, Integer>> arr)
	{
		if (ch.getChar() == 'K') // O rei nao pode movimentar-se para um eventual check
		{
			for (int i = 0; i < arr.size(); i++)
			{
				Pair<Integer, Integer> kingPos = arr.get(i);
				if (verifyCheck(otherPlayer(), kingPos))
				{
					arr.remove(i);
					i--;
				}
			}
		}
		 
		return arr;
	}

}