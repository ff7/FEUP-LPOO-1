package lpoo.chess.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import model.Chess;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Title";
//		config.useGL20 = true;
		config.height = 504;
		config.width = 504;

		Chess chess = new model.Chess();

		String os = System.getProperty("os.name").toLowerCase();

		if (os.indexOf("mac") >= 0) //Has "mac" in it
			chess.setStockfishPath("binaries/stockfish_mac");
		else if (os.indexOf("win") >= 0) //Has "win" in it
			chess.setStockfishPath("binaries/stockfish_windows");
		else
			chess.setStockfishPath("binaries/stockfish_linux");

		new LwjglApplication(chess, config);
	}
}
