package model;

import java.io.*;
import java.net.*;
import java.util.Enumeration;

public class Server implements Runnable{

	public Thread t;
	private String threadName = "Server thread";

	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;
	private int port;

	private GameState gameState;
	private boolean read = false;

	//constructor
	public Server()
	{
		port = 6789;
	}

	public Server(int port)
	{
		this.port = port;
	}

	
	public void startRunning() {
		try
		{
			server = new ServerSocket(port, 100); //6789 is a dummy port for testing, this can be changed. The 100 is the maximum people waiting to connect.

			try
			{
				if (connection == null)
					waitForConnection();

				if (input == null && connection != null)
				{
					setupStreams();
					read = true;
				}
			}
			catch (EOFException eofException)
			{
				showMessage("\n Server ended the connection! ");
				closeServer();
			}
		}
		catch (BindException bindException)
		{
			showMessage("Server already hosted!");
			closeServer();
		}
		catch (IOException ioException)
		{
			ioException.printStackTrace();
		}

	}

	//wait for connection, then display connection information
	private void waitForConnection() throws IOException
	{
		showMessage(" Waiting for someone to connect... \n");

		try
		{
			connection = server.accept();
		}
		catch (SocketException socketException)
		{
			showMessage("Server closed before anyone connected\n");
			return;
		}

		showMessage(" Now connected to " + connection.getInetAddress().getHostName());
	}

	public boolean isBound()
	{
		return (connection != null);
	}

	//get stream to send and receive data
	private void setupStreams() throws IOException
	{
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();

		input = new ObjectInputStream(connection.getInputStream());

		showMessage("\n Streams are now setup \n");
	}

	public String waitAnswer()
	{
		String message = "";

		try
		{
			message = (String) input.readObject();
			showMessage("CLIENT - " + message + "\n");
		}
		catch(ClassNotFoundException classNotFoundException)
		{
			showMessage("The user has sent an unknown object!");
		}
		catch (IOException ioex)
		{
			showMessage("Connection interruped!\n");
			closeServer();
		}

		return message;
	}
	
	public void closeConnection()
	{
		showMessage("\n Closing Connections... \n");
		try
		{
			if (output != null)
				output.close(); //Closes the output path to the client

			if (input != null)
				input.close(); //Closes the input path to the server, from the client.

			if (connection != null)
            {
                connection.close(); //Closes the connection between you can the client
                connection = null;
            }

		}
		catch(IOException ioException)
		{
			ioException.printStackTrace();
		}
	}

	public void closeServer()
	{
		closeConnection();

		try
		{
			if (server != null)
				server.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	//Send a mesage to the client
	public void sendMessage(String message)
	{
		try
		{
			output.writeObject(message);
			output.flush();
			showMessage("\nSERVER -" + message);
		}
		catch(IOException ioException)
		{
			showMessage("\n ERROR: CANNOT SEND MESSAGE, PLEASE RETRY");
		}
	}

	public String getIPAddress()
	{
		while (true)
		{
			if (server != null && server.getInetAddress() != null)
			{
				try
				{
					Enumeration e = null;
					e = NetworkInterface.getNetworkInterfaces();
					while (e.hasMoreElements())
					{
						NetworkInterface n = (NetworkInterface) e.nextElement();
						Enumeration ee = n.getInetAddresses();
						while (ee.hasMoreElements())
						{
							InetAddress i = (InetAddress) ee.nextElement();
							if (i.isSiteLocalAddress())
								return i.getHostAddress();
						}
					}
				}
				catch (SocketException e1)
				{
					e1.printStackTrace();
				}

				return "";
			}
		}
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
					foobar = waitAnswer();

					if (foobar.length() == 4)
						gameState.move(foobar);
					else
						return;
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
//        {
            t = new Thread (this, threadName);
            t.start();
//        }
	}

	public void setGameState(GameState gameState)
	{
		this.gameState = gameState;
	}

	private void showMessage(String message)
	{
		System.out.println(message);
	}

}