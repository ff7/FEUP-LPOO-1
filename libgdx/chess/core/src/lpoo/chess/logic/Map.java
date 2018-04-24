package lpoo.chess.logic;

//import java.util.ArrayList;

public class Map {

	private Character[][] map =  {
				
						{new Rook(), new Knight(), new Bishop(), new King(), new Queen(), new Bishop(), new Knight(), new Rook()},
						{new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn()},
						{new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
						{new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
						{new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
						{new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
						{new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn()},
						{new Rook(), new Knight(), new Bishop(), new King(), new Queen(), new Bishop(), new Knight(), new Rook()},
			
					};
		
		
			
		/*
		{
				
				{new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
				{new Floor(), new Floor(), new Floor(), new Bishop(), new Floor(), new Queen(), new Floor(), new Floor()},
				{new Floor(), new Floor(), new Knight(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
				{new Knight(), new Floor(), new Floor(), new Knight(), new Floor(), new King(), new Floor(), new Floor()},
				{new Floor(), new Floor(), new King(), new Pawn(), new Floor(), new Floor(), new Floor(), new Floor()},
				{new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Pawn(), new Floor(), new Floor()},
				{new Rook(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Rook()},
				{new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
	
			};
		*/
		
	public Map() 
	{
		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[i].length; j++)
			{
				map[i][j].setX(j);
				map[i][j].setY(i);
				
				if (i == 0 || i == 1)
				{
					map[i][j].setPlayer(1);
				}
				else if (i == 6 || i == 7)
				{
					map[i][j].setPlayer(0);
				}
			}
		}
		
	};
	
	public Map(Character[][] map)
	{
		this.map = map;
	}
	
	public char[][] getCharMap()
	{
		char[][] ret = new char[8][8];
		
		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[i].length; j++)
			{
				ret[i][j] = map[i][j].ch;
			}
		}
		
		return ret;
	}
	
	public Character[][] getMap()
	{
		return this.map;
	}
	
	
	public void setMap(Character[][] map)
	{
		this.map = map;
	}
	
	public boolean isWithinBounds(int x, int y)
	{
		return (x >= 0 && x < map[0].length && y >= 0 && y < map.length);
	}
	
	public boolean isWithinBounds(int[] pair)
	{
		return (pair[0] >= 0 && pair[0] < map[0].length && pair[1] >= 0 && pair[1] < map.length);
	}
	
	public void printMap()
	{
		char[][] charMap = this.getCharMap();
		
		for (int i = 0; i < charMap.length; i++)
		{
			System.out.println(charMap[i]);
		}
	}
}
