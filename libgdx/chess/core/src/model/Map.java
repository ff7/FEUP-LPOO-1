package model;

/**
 *
 * Represents a Map
 *
 */
public class Map {

	private Character[][] map =  
		{
				
						{new Rook(), new Knight(), new Bishop(), new Queen(), new King(), new Bishop(), new Knight(), new Rook()},
						{new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn()},
						{new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
						{new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
						{new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
						{new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
						{new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn()},
						{new Rook(), new Knight(), new Bishop(), new Queen(), new King(), new Bishop(), new Knight(), new Rook()}
			
					};

	/**
	 *
	 * Constructs a Map.
	 *
	 */
	public Map() 
	{
		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[i].length; j++)
			{
				map[i][j].setPos(new Pair<Integer, Integer>(j, i));
				
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
		
	}

	public Map(boolean testFlag)
	{
		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[i].length; j++)
			{
				map[i][j].setPos(new Pair<Integer, Integer>(j, i));

				if (i == 0 || i == 1)
				{
					map[i][j].setPlayer(1, testFlag);
				}
				else if (i == 6 || i == 7)
				{
					map[i][j].setPlayer(0, testFlag);
				}
			}
		}

	}


	public Map(Map mapa)
	{
		for (int i = 0; i < mapa.map.length; i++)
		{
			for (int j = 0; j < mapa.map[i].length; j++)
			{
				map[i][j] = mapa.map[i][j];
			}
		}

		updatePositions();
	}


	public void updatePositions()
	{
		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[i].length; j++) {
				map[i][j].setPos(new Pair<Integer, Integer>(j, i));
			}
		}
	}

	/**
	 *
	 * Returns the corresponding map but in chars
	 *
	 * @return a char map.
	 *
	 */
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

	/**
	 *
	 * Checks if the character is within bounds
	 *
	 * @param pair player position.
	 * @return true if character is within the bounds, false otherwise.
	 *
	 */
	public boolean isWithinBounds(Pair<Integer, Integer> pair)
	{
		return (isWithinBounds(pair.getFirst(), pair.getSecond()));
	}

	/**
	 *
	 * Prints the map to the console
	 *
	 */
	public void printMap()
	{
		char[][] charMap = this.getCharMap();
		
		for (int i = 0; i < charMap.length; i++)
		{
			System.out.println(charMap[i]);
		}
	}

	/**
	 *
	 * Moves a character from one position to another
	 *
	 * @param ch1 character old postion
	 * @param ch2 character new position
	 */
	public void move(Character ch1, Character ch2)
	{
		map[ch2.getPos().getSecond()][ch2.getPos().getFirst()] = ch1;

		map[ch1.getPos().getSecond()][ch1.getPos().getFirst()] = new Floor(ch1.getPos().getFirst(), ch1.getPos().getSecond());

		ch1.setPos(ch2.getPos());

		ch1.moveCount++;
	}

	/**
	 *
	 * Returns the kings position of a certain player
	 *
	 * @param player character team
	 * @return the kings position of a certain player.
	 *
	 */
	public King getKing(int player)
	{
		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[i].length; j++)
			{
				if (map[i][j] instanceof King && map[i][j].player == player)
				{
					return (King)map[i][j];
				}		
			}
		}

		return null;
	}

	/**
	 *
	 * Inverts the map accordingly to the black pieces.
	 *
	 */
	public void invert()
	{
		map[0][3] = new King(1, 3, 0);
		map[0][4] = new Queen(1, 4, 0);

		map[7][3] = new King(0, 3, 7);
		map[7][4] = new Queen(0, 4, 7);

		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[i].length; j++)
			{
				if (map[i][j].player == 1)
				{
					map[i][j].setPlayer(0);
					map[i][j].player = 1;
				}
				else if (map[i][j].player == 0)
				{
					map[i][j].setPlayer(1);
					map[i][j].player = 0;
				}

			}
		}
	}

}
