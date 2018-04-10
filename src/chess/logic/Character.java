package chess.logic;

import java.util.ArrayList;

public abstract class Character {
		int x;
		int y;
		char ch;
		int player;
		char[][] map;
		
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
		
		public abstract ArrayList<int[]> getPossible();
}
