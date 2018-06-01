package view;

import java.util.ArrayList;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import controller.MouseHandler;
import model.Character;
import model.Chess;
import model.Client;
import model.GameState;
import model.Pair;
import model.Server;

public class Graphics extends ScreenAdapter implements InputProcessor
{
	SpriteBatch batch;
	GameState gamestate;
	MouseHandler mousehandler;
	Chess game;
	
	int width = -1, height, widthInc, heightInc;
	
	Texture blackFloor = new Texture("images/black/floor.png");
	Texture whiteFloor = new Texture("images/white/floor.png");
	Texture redFloor = new Texture("images/highlight.png");
	
	ArrayList<Pair<Integer, Integer>> possible = new ArrayList<Pair<Integer, Integer>>();
	
	Character selected;

	public Graphics(Chess game)
	{
		this.game = game;
		this.batch = game.getBatch();

		gamestate = new GameState(game.getOpponentType(), game.getStockfishPath());

		selected = null;
		mousehandler = new MouseHandler(this);
	    Gdx.input.setInputProcessor(this);
	}

	public Graphics(Chess game, Server server)
	{
		this.game = game;
		this.batch = game.getBatch();

		gamestate = new GameState(game.getOpponentType(), game.getStockfishPath(), server);

		selected = null;
		mousehandler = new MouseHandler(this);
		Gdx.input.setInputProcessor(this);
	}

	public Graphics(Chess game, Client client)
	{
		this.game = game;
		this.batch = game.getBatch();

		gamestate = new GameState(game.getOpponentType(), game.getStockfishPath(), client);

		selected = null;
		mousehandler = new MouseHandler(this);
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render(float delta)
	{		
		batch.begin();

		if (gamestate.opponentType == 2)
		{
			if (gamestate.server != null && !gamestate.server.isBound())
			{
				exit(2);
			}

			if (gamestate.client != null && !gamestate.client.isBound())
			{
				exit(2);
			}
		}


			
		Character[][] map = gamestate.getMap().getMap();
		
		if (width == -1)
		{
			width = Gdx.graphics.getWidth();
			height = Gdx.graphics.getHeight();
			widthInc = width / map[0].length; // Increment of the width, aka image width.
			heightInc = height / map.length; // Increment of the height, aka image height.
			
		}
		
		
		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[i].length; j++)
			{
				if ((i+j) % 2 == 0)
				{
					batch.draw(blackFloor, j*widthInc, height - (i+1)*heightInc, widthInc, heightInc);
				}
				else
				{
					batch.draw(whiteFloor, j*widthInc, height - (i+1)*heightInc, widthInc, heightInc);
				}
				
				if (map[i][j].getTexture() != null)
				{
					batch.draw(map[i][j].getTexture(), j*widthInc, height - (i+1)*heightInc, widthInc, heightInc);
				}
			}
		}
		
		if (possible != null)
		{
			int x, y;
			for (int i = 0; i < possible.size(); i++)
			{
				x = possible.get(i).getFirst();
				y = possible.get(i).getSecond();
								
				batch.draw(redFloor, x*widthInc, height - (y+1)*heightInc, widthInc, heightInc);
				
				if (map[y][x].getTexture() != null)
				{
					batch.draw(map[y][x].getTexture(), x*widthInc, height - (y+1)*heightInc, widthInc, heightInc);
				}
			}
		}

		batch.end();
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button){

		if (button == Input.Buttons.LEFT)
		{
			this.click(x,y);
			return true;
		}

		return false;
	}
	

	
	public void click(int x, int y)
	{
		Character piece = getPiece(x, y);

		if (piece.equals(selected))
		{
			selected = null;
			possible = null;
		}
		else if (selected == null || !possible.contains(piece.getPos()))
		{
			if (piece.getPlayer() != gamestate.player || (gamestate.opponentType == 2 && gamestate.player == 1))
			{
				selected = null;
				possible = null;
				return;
			}

			selected = piece;
			possible = gamestate.trimGetPossible(selected, selected.getPossible(gamestate.getMap()));
		}
		else
		{
			if (!piece.equals(selected) && (gamestate.opponentType != 2 || gamestate.player == 0))
            {
				gamestate.move(selected, piece);
				gamestate.updatePawns();
				gamestate.updateCastling();
				gamestate.updateGameStatus();
				endGame();
			}

			selected = null;
			possible = null;
		}
	}


	public Character getPiece(int x, int y)
	{
		return gamestate.getMap().getMap()[y/heightInc][x/widthInc];
	}
	
	public void endGame()
	{
		if (gamestate.gameOver)
		{
			String winner;
			if (gamestate.winner == 0)
				winner = "White Pieces";
			else
				winner = "Black Pieces";
			System.out.println("This game is finished. " + winner + " won!");

			exit(gamestate.winner);
		}
	}

	@Override
	public boolean keyDown(int keycode) {

		if (keycode == Input.Keys.ESCAPE)
		{
			exit(2);
			return true;
		}

		return false;
	}

	public void exit(int winner)
	{
		gamestate.exit();

		if (winner  == 0)
		{
			game.setScreen(new whiteVictory(game));
		}
		else if (winner == 1)
		{
			game.setScreen(new blackVictory(game));
		}
		else {
			game.setScreen(new mainMenuGraphics(game));
		}
	}


	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
