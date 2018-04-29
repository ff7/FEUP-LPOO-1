package model;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;

public abstract class Character {
		Pair<Integer, Integer> pos;
		char ch;
		int player;
		Texture texture;
		String filename = "../core/src/view/images/";
		
		public boolean isCheck = false;
		
		public Character()
		{

		}
		
		public Character(char ch)
		{
			this.ch = ch;
		}
		
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
		
		public void setPos(Pair<Integer, Integer> pos)
		{
			this.pos = pos;
		}
		
		public void move(int x, int y)
		{
			pos.setFirst(x);
			pos.setSecond(y);
		}
		
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
		

		public abstract ArrayList<Pair<Integer, Integer>> getPossible(Map map);
		
		public abstract void loadTexture();

		
		
		
}
