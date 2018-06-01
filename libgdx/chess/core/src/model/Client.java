package model;

import java.io.*;
import java.net.*;

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
	public void connectToServer() throws IOException
	{
		showMessage("Attempting connection... \n");

		try
		{
			connection = new Socket(InetAddress.getByName(serverIP), 6789);
		}
		catch (ConnectException ex1)
		{
			showMessage("Failed to connect !");
			return;
		}
		catch (UnknownHostException ex2)
		{
			showMessage("Invalid IP address!");
			return;
		}

		showMessage("Connection Established! Connected to: " + connection.getInetAddress().getHostName());
	}
	
	//set up streams
	public void setupStreams() throws IOException
	{
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		showMessage("\n The streams are now set up! \n");
	}


	public String waitAnswer() throws IOException
	{
		String message = "";

		try
		{
			message = (String) input.readObject();
			showMessage("SERVER - " + message + "\n");
		}
		catch(ClassNotFoundException classNotFoundException)
		{
			showMessage("The user has sent an unknown object!");
		}
		catch (IOException ioex)
		{
			showMessage("Connection interrupted!");
			closeConnection();
		}

		return message;
	}

	public boolean isBound()
	{
		if (connection == null)
			return false;

		if (connection.isClosed())
			return false;

		return (connection.isBound());
	}
	
	//Close connection
	public void closeConnection()
    {
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
		try
		{
			output.writeObject(message);
			output.flush();
			showMessage("\nCLIENT - " + message);
		}
		catch(IOException ioException)
		{
			showMessage("\n Oops! Something went wrong!");
		}
	}
	
	//update chat window
	private void showMessage(String message)
	{
		System.out.println(message);
	}


	@Override
	public void run()
	{
		if (read)
		{
			String foobar = "";

			while (isBound())
			{
				if (gameState != null)
				{
					try
					{
						foobar = waitAnswer();

						if (foobar.length() == 4)
							gameState.move(foobar);
						else
						{
							return;
						}
					}
					catch (IOException e)
					{
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
		showMessage("Starting " +  threadName);

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