package lpoo.chess.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import lpoo.chess.Chess;

public class DesktopLauncher {
	public static void main (String[] arg) {		
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Title";
//		config.useGL20 = true;
		config.height = 504;
		config.width = 504;
		
		new LwjglApplication(new Chess(), config);
	}
}
