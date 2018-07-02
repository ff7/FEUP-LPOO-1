package model;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.io.BufferedReader;

/**
 *
 * Represents a GameState, which represents the pieces interactions.
 *
 */

public class GameState
{
	protected Map map;
	public int player = 0;
	public boolean gameOver = false;
	public int winner = 2;

	public int opponentType;

	private Process stockfish;
	private BufferedReader reader;
	private BufferedWriter writer;

	private ArrayList<String> moves;

	public Server server;
	public Client client;


	/**
	 * Constructs a GameState with the initial chess map accordingly to the the amount of players selected
	 *
	 * @param opponentType Opponent type selected: 0 for Player vs AI, 1 for two Players in the same devices, 2 for two Players in different devices.
	 * @param stockfishPath path to the process that launches AI.
	 */

	public GameState(int opponentType, String stockfishPath)
	{
		map = new Map();
		this.opponentType = opponentType;

		if (opponentType == 0)
		{
			try
			{
				stockfish = Runtime.getRuntime().exec(stockfishPath);
				reader = new BufferedReader(new InputStreamReader(stockfish.getInputStream()));
				writer = new BufferedWriter(new OutputStreamWriter(stockfish.getOutputStream()));

				moves = new ArrayList<String>();

				String foobar = "uci";
				foobar += "\nucinewgame";
				foobar += "\nisready\n";

				writer.write(foobar, 0, foobar.length());
				writer.flush();

			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * Constructs a GameState with the initial chess map accordingly to the the amount of players selected
	 *
	 * @param opponentType Opponent type selected: 0 for Player vs AI, 1 for two Players in the same devices, 2 for two Players in different devices.
	 * @param stockfishPath path to the process that launches AI.
	 * @param server Server which hosts the game.
	 */
	public GameState(int opponentType, String stockfishPath, Server server)
	{
		map = new Map();
		this.opponentType = opponentType;
		this.server = server;

		server.setGameState(this);
		server.start();
	}

	/**
	 * Constructs a GameState with the initial chess map accordingly to the the amount of players selected
	 *
	 * @param opponentType Opponent type selected: 0 for Player vs AI, 1 for two Players in the same devices, 2 for two Players in different devices.
	 * @param stockfishPath path to the process that launches AI.
	 * @param client Client that connects to the server.
	 */
	public GameState(int opponentType, String stockfishPath, Client client)
	{
		map = new Map();
		this.opponentType = opponentType;
		this.client = client;

		this.player = 1; //Server plays firsts
		map.invert();

		client.setGameState(this);
		client.start();
	}

	/**
	 * Constructs a GameState with the initial chess map accordingly to the the amount of players selected
	 *
	 * @param opponentType Opponent type selected: 0 for Player vs AI, 1 for two Players in the same devices, 2 for two Players in different devices.
	 * @param stockfishPath path to the process that launches AI.
	 * @param testFlag Flag necessary to test the game.
	 */
	public GameState(int opponentType, String stockfishPath, boolean testFlag)
	{
		map = new Map(testFlag);
		this.opponentType = opponentType;

		if (opponentType == 0)
		{
			try
			{
				stockfish = Runtime.getRuntime().exec(stockfishPath);
				reader = new BufferedReader(new InputStreamReader(stockfish.getInputStream()));
				writer = new BufferedWriter(new OutputStreamWriter(stockfish.getOutputStream()));

				moves = new ArrayList<String>();

				String foobar = "uci";
				foobar += "\nucinewgame";
				foobar += "\nisready\n";

				writer.write(foobar, 0, foobar.length());
				writer.flush();

			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * Moves one character from one position in the map to another one
	 *
	 * @param ch1 Previous position character.
	 * @param ch2 New position character.
	 */
	public void move(Character ch1, Character ch2) // Moves ch1 to ch2
	{
		if (opponentType == 0)
		{
			moves.add(getMoveSymbol(ch1, ch2));
			map.move(ch1, ch2);
			swapPlayer();

			if (player == 1)
			{
				try
				{
					moveAI();
					printAIAnswer();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

		if (opponentType == 1)
		{
			map.move(ch1, ch2);
			swapPlayer();
		}

		if (opponentType == 2)
		{
			if (player == 0)
			{
				if (client != null)
				{
					client.sendMessage(getInvertedMoveSymbol(ch1, ch2));
					map.move(ch1, ch2);
					swapPlayer();
				}
				else if (server != null)
				{
					server.sendMessage(getInvertedMoveSymbol(ch1, ch2));
					map.move(ch1, ch2);
					swapPlayer();
				}
			}
		}
	}



	/**
	 * Moves one character from one position in the map to another one
	 *
	 * @param symbol Universal Chess Interface notation movement.
	 */
	public void move(String symbol)
	{
		int x1 = symbol.substring(0,1).charAt(0) - 'a', y1 = 8 - Integer.parseInt(symbol.substring(1,2)), x2 = symbol.substring(2,3).charAt(0) - 'a', y2 = 8 - Integer.parseInt(symbol.substring(3,4));

		if (opponentType == 0)
			moves.add(getMoveSymbol(map.getMap()[y1][x1], map.getMap()[y2][x2]));

		map.move(map.getMap()[y1][x1], map.getMap()[y2][x2]);
		swapPlayer();


		this.updatePawns();
		this.updateCastling();
		this.updateGameStatus();
	}

	/**
	 * Prints the map used by the AI
	 *
	 */
	public void printAIBoard() throws IOException
	{
		String foobar = "d\n";

		writer.write(foobar, 0, foobar.length());
		writer.flush();

		printAIAnswer();
	}

	/**
	 * Updates the AI Map accordingly to the best decision
	 *
	 */
	public void updateAIBoard() throws IOException
	{
		String foobar = "position startpos moves";

		for (int i = 0; i < moves.size(); i++) {
			foobar += " " + moves.get(i);
		}
		foobar += "\n";

		writer.write(foobar, 0, foobar.length());
		writer.flush();
	}

	/**
	 * Begins the AI best decision selector process
	 *
	 */
	public void goAI() throws IOException
	{
		String foobar = "go\n";

		writer.write(foobar, 0, foobar.length());
		writer.flush();
	}

	/**
	 * Moves a piece accordingly to the best movement decision.
	 *
	 */
	public void moveAI() throws IOException
	{
		updateAIBoard();
		goAI();

		new java.util.Timer().schedule(new java.util.TimerTask() { //Allows the game to be played while an answer from the AI is waited for
										   @Override
										   public void run()
										   {
										   	try
											{
												ArrayList<String> answer = getAIAnswer();

												printAIAnswer(answer);

												String foobar = answer.get(answer.size()-1);

												foobar = foobar.substring(9, 13);

												move(foobar);

												updateAIBoard();
												printAIBoard();
											}
											catch (IOException e)
											{
												e.printStackTrace();
											}

										   }
									   }, 1000
		);


	}


	/**
	 * Moves a piece accordingly to the best movement decision.
	 *
	 * @return The best moves from the AI.
	 */
	public ArrayList<String> getAIAnswer() throws IOException
	{
		ArrayList<String> ret = new ArrayList<String>();

		while (reader.ready())
		{
			ret.add(reader.readLine());
		}

		return ret;
	}

	/**
	 * Prints the AI decision the console.
	 *
	 */
	public void printAIAnswer()
	{

		ArrayList<String> answer = new ArrayList<String>();
		try {
			answer = getAIAnswer();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < answer.size(); i++)
		{
			System.out.println(answer.get(i));
		}
	}

	/**
	 * Prints the AI decisions to the console.
	 *
	 * @param answer AI best mpvements.
	 */
	public void printAIAnswer(ArrayList<String> answer)
	{
		for (int i = 0; i < answer.size(); i++)
		{
			System.out.println(answer.get(i));
		}
	}

	/**
	 * Prints the AI decisions to the console.
	 *
	 * @param ch1 AI best mpvements.
	 * @param ch2
	 * @return Universal Chess Interface notation corresponding to the map coordinates notation for the white pieces
	 */
	public String getMoveSymbol(Character ch1, Character ch2)
	{
		String str = "";

		str += (char)('a' + ch1.getPos().getFirst());
		str += 8 - ch1.getPos().getSecond();
		str += (char)('a' + ch2.getPos().getFirst());
		str += 8 - ch2.getPos().getSecond();

		return str;
	}

	/**
	 * Prints the AI decisions to the console.
	 *
	 * @param ch1 AI best mpvements.
	 * @param ch2
	 * @return Universal Chess Interface notation corresponding to the map coordinates notation for the black pieces
	 */
	public String getInvertedMoveSymbol(Character ch1, Character ch2)
	{
		String str = "";

		str += (char)('h' - ch1.getPos().getFirst());
		str += ch1.getPos().getSecond() + 1;
		str += (char)('h' - ch2.getPos().getFirst());
		str += ch2.getPos().getSecond() + 1;

		return str;
	}

	public Map getMap()
	{
		return map;
	}

	/**
	 * Returns the opposite player in the current game map
	 *
	 * @return The opposite player.
	 */
	public int otherPlayer()
	{
		if (player == 0)
			return 1;
		return 0;
	}

	/**
	 * Switches the current came player
	 *
	 */
	public void swapPlayer()
	{
		if (player == 0)
		{
			player = 1;
		}
		else
		{
			player = 0;
		}
	}

	/**
	 * Checks if any pawn needs to be promoted to queen.
	 *
	 */
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

	/**
	 * Checks if there is any castle to be done.
	 *
	 */
	public void updateCastling() // Jogada especial em que o rei troca de posicao com a torre
	{
		if (this.getMap().getCharMap()[7][6] == 'K' && this.getMap().getMap()[7][6].player == 0)
		{
			if (this.getMap().getCharMap()[7][5] == '_' && this.getMap().getCharMap()[7][7] == 'R' && this.getMap().getMap()[7][7].player == 0 && this.getMap().getMap()[7][7].moveCount == 0)
			{
				this.getMap().getMap()[7][6] = new King(0, 6,7);
				this.getMap().getMap()[7][5] = new Rook(0, 5,7);
				this.getMap().getMap()[7][7] = new Floor(7,7);
			}
		}
		if (this.getMap().getCharMap()[7][2] == 'K' && this.getMap().getMap()[7][2].player == 0)
		{
			if (this.getMap().getCharMap()[7][3] == '_' && this.getMap().getCharMap()[7][1] == '_' && this.getMap().getCharMap()[7][0] == 'R' && this.getMap().getMap()[7][0].player == 0 && this.getMap().getMap()[7][0].moveCount == 0)
			{
				this.getMap().getMap()[7][3] = new Rook(0, 3,7);
                this.getMap().getMap()[7][2] = new King(0, 2,7);
				this.getMap().getMap()[7][0] = new Floor(0,7);
			}
		}
		if (this.getMap().getCharMap()[0][6] == 'K' && this.getMap().getMap()[0][6].player == 1)
		{
			if (this.getMap().getCharMap()[0][5] == '_' && this.getMap().getCharMap()[0][7] == 'R' && this.getMap().getMap()[0][7].player == 1 && this.getMap().getMap()[0][7].moveCount == 0)
			{
				this.getMap().getMap()[0][6] = new King(1, 6,0);
				this.getMap().getMap()[0][5] = new Rook(1, 5,0);
				this.getMap().getMap()[0][7] = new Floor(7,0);
			}
		}
		if (this.getMap().getCharMap()[0][2] == 'K' && this.getMap().getMap()[0][2].player == 1)
		{
			if (this.getMap().getCharMap()[0][3] == '_' && this.getMap().getCharMap()[0][1] == '_' && this.getMap().getCharMap()[0][0] == 'R' && this.getMap().getMap()[0][0].player == 1 && this.getMap().getMap()[0][0].moveCount == 0)
			{
				this.getMap().getMap()[0][3] = new Rook(1, 3,0);
				this.getMap().getMap()[0][2] = new King(1, 2,0);
				this.getMap().getMap()[0][0] = new Floor(0,0);
			}
		}
	}

	/**
	 * Updates the current gameStatus by verifying checks and check-mates
	 *
	 */
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

	/**
	 * Verifies any check to a specific king.
	 *
	 * @param p player that makes the check.
	 * @param kingPos position of the threatened king.
	 * @return true if check, false otherwise
	 */
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

	/**
	 * Verifies if the game is over by checking any check-mate to a specific king
	 *
	 * @param p player that makes the check.
	 * @param kingPos position of the threatened king.
	 * @return true if check-mate, false otherwise
	 */
	public boolean verifyCheckMate(int p, Pair<Integer, Integer> kingPos)
	{
		System.out.println(isKingCornered(p, kingPos) + "-" + isKingIndefensible(p, kingPos));
		if (isKingCornered(p, kingPos) && isKingIndefensible(p, kingPos))
		{
			return true;
		}
		return false;
	}

	/**
	 * Verifies if the threatened king has no place to move
	 *
	 * @param p player that makes the check.
	 * @param kingPos position of the threatened king.
	 * @return true if king cannot move, false otherwise.
	 */
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

	/**
	 * Verifies if there is any piece capable of defending the king that is in check.
	 *
	 * @param p player that makes the check.
	 * @param kingPos position of the threatened king.
	 * @return true if king is indefensible, false otherwise.
	 */
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

	/**
	 * Trims a certain piece character moves accordingly to the checks and possible-checks in the current gamestate.
	 *
	 * @param ch player which possible moves will be trimmed.
	 * @param arr character possible moves.
	 * @return the trimmed possible moves of the character.
	 */
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

			if (this.player == 0 ) // Caso especial do rei
			{
				if (map.getCharMap()[7][4] == 'K' )
				{
					Pair<Integer, Integer> pos = new Pair<Integer, Integer>();
					pos.setFirst(5);
					pos.setSecond(7);
					if (!isPossible(pos, arr))
					{
						for (int i = 0; i < arr.size(); i++)
						{
							Pair<Integer, Integer> castlePos = new Pair<Integer, Integer>();
							castlePos.setFirst(6);
							castlePos.setSecond(7);
							if (arr.get(i).equals(castlePos))
							{
								arr.remove(i);
								i--;
							}
						}
					}
				}

				else if (map.getCharMap()[7][3] == 'K' )
				{
					Pair<Integer, Integer> pos = new Pair<Integer, Integer>();
					pos.setFirst(2);
					pos.setSecond(7);
					if (!isPossible(pos, arr))
					{
						for (int i = 0; i < arr.size(); i++)
						{
							Pair<Integer, Integer> castlePos = new Pair<Integer, Integer>();
							castlePos.setFirst(1);
							castlePos.setSecond(7);
							if (arr.get(i).equals(castlePos))
							{
								arr.remove(i);
								i--;
							}
						}
					}
				}
			}

			else if (this.player == 1)
			{
				if (map.getCharMap()[0][4] == 'K' )
				{
					Pair<Integer, Integer> pos = new Pair<Integer, Integer>();
					pos.setFirst(5);
					pos.setSecond(0);
					if (!isPossible(pos, arr))
					{
						for (int i = 0; i < arr.size(); i++)
						{
							Pair<Integer, Integer> castlePos = new Pair<Integer, Integer>();
							castlePos.setFirst(6);
							castlePos.setSecond(0);
							if (arr.get(i).equals(castlePos))
							{
								arr.remove(i);
								i--;
							}
						}
					}
				}

				else if (map.getCharMap()[0][3] == 'K' )
				{
					Pair<Integer, Integer> pos = new Pair<Integer, Integer>();
					pos.setFirst(2);
					pos.setSecond(0);
					if (!isPossible(pos, arr))
					{
						for (int i = 0; i < arr.size(); i++)
						{
							Pair<Integer, Integer> castlePos = new Pair<Integer, Integer>();
							castlePos.setFirst(1);
							castlePos.setSecond(0);
							if (arr.get(i).equals(castlePos))
							{
								arr.remove(i);
								i--;
							}
						}
					}
				}
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

	/**
	 * Checks if a current character position is among a set of another character possible moves.
	 *
	 * @param pos first character actual position
	 * @param arr first character possible moves.
	 * @return true if position is contained, false otherwise.
	 */
	public boolean isPossible(Pair<Integer, Integer> pos, ArrayList<Pair<Integer, Integer>> arr)
	{
		return arr.contains(pos);
	}


	/**
	 * Closes the server and the client connections
	 */
	public void exit()
	{
		if (server != null)
			server.closeServer();

		if (client != null)
			client.closeConnection();

		if (opponentType == 0)
			stockfish.destroy();
	}

}