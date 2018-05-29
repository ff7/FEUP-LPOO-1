package lpoo.chess;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import model.Chess;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		Chess chess = new model.Chess();
		chess.setStockfishPath(copyStockfish());

		initialize(chess, config);
	}


	public String copyStockfish()
	{
		String stockfishPath = "/data/data/lpoo.chess/stockfish";
		File file;
		InputStream is;
		OutputStream os;
		byte[] buffer;
		try
		{
			file = new File(stockfishPath);
			file.delete();

			is = getAssets().open("binaries/Stockfish-9-armv7");
			os = new FileOutputStream(file);
			buffer = new byte[is.available()];
			is.read(buffer);
			os.write(buffer);

			file.setExecutable(true);

            os.close();
            is.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}

		return stockfishPath;
	}
}
