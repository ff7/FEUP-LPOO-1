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

		if (System.getProperty("os.name").equals("Mac OS X"))
			chess.setStockfishPath("binaries/stockfish_mac");
		else
			chess.setStockfishPath("binaries/stockfish");

		new LwjglApplication(chess, config);
	}
}
