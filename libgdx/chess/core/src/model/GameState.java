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
	
	public void updatePawns()
	{
		for (int i = 0; i < this.getMap().getMap().length; i++)
		{
			for (int j = 0; j < this.getMap().getMap()[i].length; j++)
			{
				Character ch = this.getMap().getMap()[j][i];
				
				if (ch.getPlayer() == 0)
				{
					if (ch.getChar() == 'p' && ch.getPos().getSecond() == 0)
					{
						this.getMap().getMap()[j][i] = new Queen(0,ch.getPos().getFirst(), ch.getPos().getSecond());
					}
				}
				else if (ch.getPlayer() == 1)
				{
					if (ch.getChar() == 'p' && ch.getPos().getSecond() == 7)
					{
						this.getMap().getMap()[j][i] = new Queen(1,ch.getPos().getFirst(), ch.getPos().getSecond());
					}
				}
			}
		}
	}

	public void updateCastling() // Jogada especial em que o rei troca de posicao com a torre
	{
		if (this.getMap().getCharMap()[7][5] == 'K' && this.getMap().getMap()[7][5].player == 0)
		{
			if (this.getMap().getCharMap()[7][6] == '_' && this.getMap().getCharMap()[7][7] == 'R' && this.getMap().getMap()[7][7].player == 0)
			{
				this.getMap().getMap()[7][6] = new King(0, 6,7);
				this.getMap().getMap()[7][5] = new Rook(0, 5,7);
				this.getMap().getMap()[7][7] = new Floor(7,7);
			}
		}
		else if (this.getMap().getCharMap()[7][2] == 'K' && this.getMap().getMap()[7][2].player == 0)
		{
			if (this.getMap().getCharMap()[7][1] == '_' && this.getMap().getCharMap()[7][0] == 'R' && this.getMap().getMap()[7][0].player == 0)
			{
				this.getMap().getMap()[7][2] = new Rook(0, 2,7);
                this.getMap().getMap()[7][1] = new King(0, 1,7);
				this.getMap().getMap()[7][0] = new Floor(0,7);
			}
		}
		else if (this.getMap().getCharMap()[0][5] == 'K' && this.getMap().getMap()[0][5].player == 1)
		{
			if (this.getMap().getCharMap()[0][6] == '_' && this.getMap().getCharMap()[0][7] == 'R' && this.getMap().getMap()[0][7].player == 1)
			{
				this.getMap().getMap()[0][6] = new King(1, 6,0);
				this.getMap().getMap()[0][5] = new Rook(1, 5,0);
				this.getMap().getMap()[0][7] = new Floor(7,0);
			}
		}
		else if (this.getMap().getCharMap()[0][2] == 'K' && this.getMap().getMap()[0][2].player == 1)
		{
			if (this.getMap().getCharMap()[0][1] == '_' && this.getMap().getCharMap()[0][0] == 'R' && this.getMap().getMap()[0][0].player == 1)
			{
				this.getMap().getMap()[0][2] = new Rook(1, 2,0);
				this.getMap().getMap()[0][1] = new King(1, 1,0);
				this.getMap().getMap()[0][0] = new Floor(0,0);
			}
		}
	}
	
	public void updateGameStatus() // Trata de ver se ha cheques e cheque-mates
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
		System.out.println(isKingCornered(p, kingPos) + "-" + isKingIndefensible(p, kingPos));
		if (isKingCornered(p, kingPos) && isKingIndefensible(p, kingPos))
		{
			return true;
		}
		return false;
	}
	
	public boolean isKingCornered(int p, Pair<Integer, Integer> kingPos) // True se o rei esta encurralado
	{
		HashMap<Pair<Integer, Integer>, Boolean> possibleMoves = new HashMap<Pair<Integer, Integer>, Boolean>();
		 
		for (int k = 0; k < trimGetPossible(this.getMap().getMap()[kingPos.getSecond()][kingPos.getFirst()], this.getMap().getMap()[kingPos.getSecond()][kingPos.getFirst()].getPossible(getMap())).size(); k++)
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
							entry.setValue(true);
						}
					}
				}
			}
		}
		
		for (HashMap.Entry<Pair<Integer, Integer>, Boolean> entry : possibleMoves.entrySet())
		{
			if (entry.getValue() == false)
			{
				System.out.println(entry.getKey().getFirst() + ":" + entry.getKey().getSecond());
				return false;
			}
		}
		return true;
	}
	
	public boolean isKingIndefensible(int p, Pair<Integer, Integer> kingPos) // True se nenhuma peca conseguir evitar o cheque mate
	{
		for (int i = 0; i < this.getMap().getMap().length; i++)
		{
			for (int j = 0; j < this.getMap().getMap()[i].length; j++) // Estes 2 loops percorrem o mapa
			{
				if (this.getMap().getMap()[j][i].getPossible(getMap()) != null && this.getMap().getMap()[j][i].getPlayer() == player && this.getMap().getMap()[j][i].getChar() != 'K')  
				{
					ArrayList<Pair<Integer, Integer>> arr = this.getMap().getMap()[j][i].getPossible(getMap());
					
					for (int k = 0 ; k < arr.size(); k++)
					{
						int xt = arr.get(k).getFirst(), yt = arr.get(k).getSecond();
						
						Character temp = this.getMap().getMap()[yt][xt];
						Character ch = this.getMap().getMap()[j][i];
						
						this.getMap().getMap()[j][i] = new Floor();
						this.getMap().getMap()[yt][xt] = ch; 
						
						if (!verifyCheck(otherPlayer(), kingPos))
						{
							this.getMap().getMap()[j][i] = ch;
							this.getMap().getMap()[yt][xt] = temp;
							System.out.println(ch.getChar() + ":" + xt +":" + yt);
							return false;
						}
						this.getMap().getMap()[j][i] = ch;
						this.getMap().getMap()[yt][xt] = temp;
					}
				}
			}
		}
		
		return true;
	}
		
	public ArrayList<Pair<Integer, Integer>> trimGetPossible(Character ch, ArrayList<Pair<Integer, Integer>> arr)
	{
		if (ch.getChar() == 'K') // O rei nao pode movimentar-se para um eventual check
		{
			int x = ch.getPos().getFirst(), y = ch.getPos().getSecond();
			for (int i = 0; i < arr.size(); i++)
			{
				Pair<Integer, Integer> kingPos = arr.get(i);
				int xt = arr.get(i).getFirst(), yt = arr.get(i).getSecond();
				Character temp = this.getMap().getMap()[yt][xt];
				
				this.getMap().getMap()[y][x] = new Floor();
				this.getMap().getMap()[yt][xt] = ch; 
				
				if (verifyCheck(otherPlayer(), kingPos))
				{
					arr.remove(i);
					i--;
				}	
				
				this.getMap().getMap()[y][x] = ch;
				this.getMap().getMap()[yt][xt] = temp;
			}		
		}
		else
		{
			int x = ch.getPos().getFirst(), y = ch.getPos().getSecond();
			Pair<Integer, Integer> kingPos = this.getMap().getKingsPosition(player);
			
			for (int i = 0; i < arr.size(); i++)
			{		
				int xt = arr.get(i).getFirst(), yt = arr.get(i).getSecond();
				Character temp = this.getMap().getMap()[yt][xt];
				
				this.getMap().getMap()[y][x] = new Floor();
				this.getMap().getMap()[yt][xt] = ch; 
				
				if (verifyCheck(otherPlayer(), kingPos))
				{
					arr.remove(i);
					i--;
				}
				this.getMap().getMap()[y][x] = ch;
				this.getMap().getMap()[yt][xt] = temp;
			}
			
		}
		 
		return arr;
	}

}