package lpoo.chess.gui;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class MyMouseHandler implements InputProcessor
{
	@Override
	public boolean touchDown(int x, int y, int pointer, int button)
	{
		if (button == Input.Buttons.LEFT)
		{
			System.out.println("x = " + x + ", y = " + y);
			return true;
		}
		
		return false;
	}


	private void onMouseDown()
	{
		
	}


	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}