package model;

import java.io.*;
import java.net.*;
import java.util.Enumeration;

/**
 *
 * Class for instanciating servers on another thread. Works with a ServerSocket, a Socket and IOStreams.
 *
 */
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

	/**
	 * Constructs a server on port 6789.
	 * 
	 * 
	 */
	public Server()
	{
		port = 6789;
	}

	/**
	 * Opens a server on a specific port.
	 * 
	 * @param port port on with to open the server on.
	 * 
	 */
	public Server(int port)
	{
		this.port = port;
	}

	/**
	 * Instanciates the ServerSocket as well as the Socket and the IOStreams.
	 * 
	 */
	public void startRunning() {
		try
		{
			server = new ServerSocket(port, 100);

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
			return;
		}
		catch (IOException ioException)
		{
			ioException.printStackTrace();
		}

	}

	/**
	 * Waits for an incomming connection, freezing the thread execution.
	 * 
	 * @throws IOException Caused when the server closes befora estabilishing a connection
	 */
	//wait for connection, then display connection information
	public void waitForConnection() throws IOException
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

	/**
	 * Sets up the IOStreams.
	 * 
	 * @throws IOException Thrown when invalid connection
	 */
	public void setupStreams() throws IOException
	{
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();

		input = new ObjectInputStream(connection.getInputStream());

		showMessage("\n Streams are now setup \n");
	}

	/**
	 * Waits for a message from the client, freezing thread execution. This is the main reason multithreading was implemented.
	 * 
	 * @return The message sent by the client
	 */
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
	
	/**
	 * Close the IOStreams and the Socket.
	 *
	 */
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


	/**
	 * Closes the IOStreams, the Socket and the ServerSocket.
	 * 
	 * 
	 */
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
	
	/**
	 * Sends a message to the client.
	 * 
	 * @param message The server's messsage
	 */
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

	/**
	 * Gets the IP Address of the local network (LAN)
	 * 
	 * @return The IP Address or an empty String
	 */
	public String getIPAddress()
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

	/**
	 * Starts thread execution.
	 * 
	 */
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