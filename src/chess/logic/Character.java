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
		
		public int[] getMovePosition(int x, int y)
		{
			if (this.player == 0)
			{
				y *= -1;
			}
			else
			{
				x *= -1;
			}
			
			int[] ret = {x, y};
			
			return ret;
		}
		
		public int[] getMovePosition(int[] pair)
		{
			if (this.player == 0)
			{
				pair[1] *= -1;
			}
			else
			{
				pair[0] *= -1;
			}
			
			int[] ret = {pair[0], pair[1]};
			
			return ret;
		}
		
		public abstract ArrayList<int[]> getPossible(Map map);
}
