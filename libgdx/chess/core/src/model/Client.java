package model;

import com.badlogic.gdx.Game;

import java.io.*;
import java.net.*;

import view.connectMenu;

public class Client implements Runnable{

	private Thread t;
	private String threadName = "Client thread";

	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String message = "";
	private String serverIP;
	private Socket connection;

	private GameState gameState;
	private boolean read = false;

	//constructor
	public Client(String host)
	{
		serverIP = host;
	}

	
	//connect to server
	public void startRunning(){
		try
		{
			connectToServer();

			if (connection != null && !connection.isClosed())
			{
				setupStreams();
				read = true;
			}


		}
		catch(EOFException eofException)
		{
			showMessage("\n Client terminated the connection");
		}
		catch(IOException ioException)
		{
			ioException.printStackTrace();
		}

	}
	
	//connect to server
	private void connectToServer() throws IOException
	{
		showMessage("Attempting connection... \n");

		try
		{
			connection = new Socket(InetAddress.getByName(serverIP), 6789);
		}
		catch (ConnectException ex)
		{
			showMessage("Failed to connect !");
			return;
		}

		showMessage("Connection Established! Connected to: " + connection.getInetAddress().getHostName());
	}
	
	//set up streams
	private void setupStreams() throws IOException
	{
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		showMessage("\n The streams are now set up! \n");
	}
	
	//while chatting with server
	private void whileChatting() throws IOException
	{
		do
			{
			try
			{
				message = (String) input.readObject();
				showMessage("\n" + message);

				if (gameState != null)
					gameState.move(message);
			}
			catch(ClassNotFoundException classNotFoundException)
			{
				showMessage("Unknown data received!");
			}
		}
		while(!message.equals("SERVER - END"));
	}

	//during the chat conversation
	public String waitAnswer() throws IOException
	{
		String message = null;

		try{
			message = (String) input.readObject();
			System.out.println("\n" + message);

		}catch(ClassNotFoundException classNotFoundException){
			System.out.println("The user has sent an unknown object!");
		}

		return message;
	}

	public boolean isBound()
	{
		if (connection == null)
			return false;

		return (connection.isBound());
	}
	
	//Close connection
	public void closeConnection(){
		showMessage("\n Closing the connection!");
		try
		{
			if (output != null)
				output.close();

			if (input != null)
				input.close();

			if (connection != null)
				connection.close();
		}
		catch(IOException ioException)
		{
			ioException.printStackTrace();
		}
	}
	
	//send message to server
	public void sendMessage(String message){
		try{
			output.writeObject(message);
			output.flush();
			showMessage("\nCLIENT - " + message);
		}catch(IOException ioException){
			showMessage("\n Oops! Something went wrong!");
		}
	}
	
	//update chat window
	private void showMessage(final String message){
		System.out.println(message);
	}


	@Override
	public void run()
	{
		String answer = "Hortalicas";

		if (read)
		{
			System.out.println("Entrou no read");

			while (isBound())
			{
				if (gameState != null)
				{
					try {
						answer = waitAnswer();

						System.out.println("answer = " + answer);

						gameState.move(answer);
					} catch (IOException e) {
						e.printStackTrace();
					}


				}
			}
		}
		else
			startRunning();

	}

	public void start()
	{
		System.out.println("Starting " +  threadName);

//		if (t == null)
//		{
			t = new Thread (this, threadName);
			t.start();
//		}
	}

	public void setGameState(GameState gameState)
	{
		this.gameState = gameState;
	}
}