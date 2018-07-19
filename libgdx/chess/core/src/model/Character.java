package model;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;


/**
 *
 * Represents a Character, which represents a chess piece.
 *
 */
public abstract class Character {
		Pair<Integer, Integer> pos;
		char ch;
		int player;
		Texture texture;
		String filename = "images/";

		public int moveCount = 0;

		public Character()
		{

		}

	/**
	 *
	 * Constructs a character
	 *
	 * @param ch character representative symbol.
	 */
		public Character(char ch)
		{
			this.ch = ch;
		}

	/**
	 *
	 * Constructs a character
	 *
	 * @param player character team.
	 * @param x character X position.
	 * @param y character Y position.
	 * @param ch character representative symbol.
	 */
		public Character(int player, int x, int y, char ch)
		{
			setPlayer(player);
			pos = new Pair<Integer, Integer>(x, y);
			this.ch = ch;
		}

		public char getChar()
		{
			return this.ch;
		}

	/**
	 *
	 * Returns a pair with the character position
	 *
	 * @return character position.
	 */
		public Pair<Integer, Integer> getPos()
		{
			return pos;
		}
		
		public int getPlayer()
		{
			return this.player;
		}
		
		public void setPlayer(int player)
		{
			this.player = player;
			loadTexture();
		}

		public void setPlayer(int player, boolean testFlag)
		{
			this.player = player;
			if (!testFlag)
				loadTexture();
		}

	/**
	 *
	 * Sets a new character position
	 *
	 * @param pos pair containing the new X and the new Y.
	 *
	 */
		public void setPos(Pair<Integer, Integer> pos)
		{
			this.pos = pos;
		}
		
		public void move(int x, int y)
		{
			pos.setFirst(x);
			pos.setSecond(y);
		}

	/**
	 *
	 * Returns the position after a move
	 *
	 * @param x X coordinate.
	 * @param y Y coordinate.
	 *
	 * @return a pair with position after a move.
	 */
		public Pair<Integer, Integer> getMovePosition(int x, int y) // Esquerda negativo e direita positivo
		{
			Pair<Integer, Integer> ret = new Pair<Integer, Integer>(x, y);
			
			if (this.player == 0)
			{
				ret.setFirst(pos.getFirst() + x);
				ret.setSecond(pos.getSecond() - y);
			}
			else
			{
				ret.setFirst(pos.getFirst() - x);
				ret.setSecond(pos.getSecond() + y);
			}
			
			return ret;
		}

	/**
	 *
	 * Returns the position after a move
	 *
	 * @param  pos pair with the new x and y coordinates.
	 *
	 * @return a pair with position after a move.
	 */
		public Pair<Integer, Integer> getMovePosition(Pair<Integer, Integer> pos) // Esquerda negativo e direita positivo
		{
			return getMovePosition(pos.getFirst(), pos.getSecond());
		}
		
		public void setTexture(String filename)
		{
			texture = new Texture(filename);
		}
		
		public Texture getTexture()
		{
			return texture;
		}


	/**
	 *
	 * Returns the possible movements of a specific character
	 *
	 * @param map Game Map.
	 *
	 * @return a pair with position after a move.
	 */
		public abstract ArrayList<Pair<Integer, Integer>> getPossible(Map map);

	/**
	 *
	 *  Loads the corresponding character texture
	 */
		public abstract void loadTexture();

		
		
		
}
