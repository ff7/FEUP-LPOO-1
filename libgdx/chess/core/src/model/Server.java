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
		try {
			server = new ServerSocket(port, 100); //6789 is a dummy port for testing, this can be changed. The 100 is the maximum people waiting to connect.
			try {
				if (connection == null)
					waitForConnection();

				if (input == null && connection != null)
				{
					setupStreams();
					read = true;
				}


			} catch (EOFException eofException) {
				System.out.println("\n Server ended the connection! ");
			}

		}
		catch (BindException bindException)
		{
			System.out.println("Server already hosted!");
			closeServer();
			t.interrupt();
		}
		catch (IOException ioException)
		{
			ioException.printStackTrace();
		}

	}

	//wait for connection, then display connection information
	private void waitForConnection() throws IOException
	{
		System.out.println(" Waiting for someone to connect... \n");

		try
		{
			connection = server.accept();
		}
		catch (SocketException socketException)
		{
			System.out.println("Server closed before anyone connected\n");
			return;
		}

		System.out.println(" Now connected to " + connection.getInetAddress().getHostName());
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

		System.out.println("\n Streams are now setup \n");
	}
	
	//during the chat conversation
	private void whileChatting() throws IOException
	{
		String message = " You are now connected! ";
		sendMessage(message);
		do{
			try{
				message = (String) input.readObject();
				System.out.println("\n" + message);
			}catch(ClassNotFoundException classNotFoundException){
				System.out.println("The user has sent an unknown object!");
			}
		}while(!message.equals("CLIENT - END"));
	}

	//during the chat conversation
	public String waitAnswer() throws IOException
	{
		String message = "";

		try{
			message = (String) input.readObject();
			System.out.println("\n" + message);
		}catch(ClassNotFoundException classNotFoundException){
			System.out.println("The user has sent an unknown object!");
		}

		return message;
	}
	
	public void closeConnection()
	{
		System.out.println("\n Closing Connections... \n");
		try{
			if (output != null)
				output.close(); //Closes the output path to the client

			if (input != null)
				input.close(); //Closes the input path to the server, from the client.

			if (connection != null)
				connection.close(); //Closes the connection between you can the client
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}

	public void closeServer()
	{
		closeConnection();

		try {
			if (server != null)
				server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Send a mesage to the client
	public void sendMessage(String message){
		try{
			output.writeObject(message);
			output.flush();
			System.out.println("\nSERVER -" + message);
		}catch(IOException ioException){
			System.out.println("\n ERROR: CANNOT SEND MESSAGE, PLEASE RETRY");
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
					while(e.hasMoreElements())
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
//        {
            t = new Thread (this, threadName);
            t.start();
//        }
	}

	public void setGameState(GameState gameState)
	{
		this.gameState = gameState;
	}

}