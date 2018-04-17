package chess.logic;

import java.util.ArrayList;

public abstract class Character {
		int x;
		int y;
		char ch;
		int player;
		
		public Character()
		{
			
		}
		
		public Character(char ch)
		{
			this.ch = ch;
		}
		
		public Character(int player, int x, int y, char ch)
		{
			this.player = player;
			this.x = x;
			this.y = y; 
			this.ch = ch;
		}
		
		public char getChar()
		{
			return this.ch;
		}
		
		public int getX()
		{
			return this.x;
		}
		
		public int getY()
		{
			return this.y;
		}
		
		public void setPlayer(int player)
		{
			this.player = player;
		}
		
		public void setX(int x)
		{
			this.x = x;
		}
		
		public void setY(int y)
		{
			this.y = y;
		}
		
		public void move(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		
		public int[] getMovePosition(int x, int y) // Esquerda negativo e direita positivo
		{
			int[] ret = new int[2];
			
			if (this.player == 0)
			{
				ret[0] = this.x + x;
				ret[1] = this.y - y;
			}
			else
			{
				ret[0] = this.x - x;
				ret[1] = this.y + y;
			}
			
			return ret;
		}
		
		public int[] getMovePosition(int[] pair) // Esquerda negativo e direita positivo
		{
			int[] ret = new int[2];
			
			if (this.player == 0)
			{
				ret[0] = this.x + pair[0];
				ret[1] = this.y - pair[1];
			}
			else
			{
				ret[0] = this.x - pair[0];
				ret[1] = this.y + pair[1];
			}
			
			return ret;
		}
		

		public abstract ArrayList<int[]> getPossible(Map map);
}
