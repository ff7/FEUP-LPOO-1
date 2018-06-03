package controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import view.Graphics;

public class MouseHandler implements InputProcessor
{
	Graphics graphics;
	public int flag = 0;


	public MouseHandler(Graphics graphics)
	{
		this.graphics = graphics;
	}


	@Override
	public boolean touchDown(int x, int y, int pointer, int button){

		if (button == Input.Buttons.LEFT)
		{
			graphics.click(x,y);
			return true;
		}

		return false;
	}

	@Override
	public boolean keyDown(int keycode) {

		if (keycode == Input.Keys.ESCAPE || keycode == Input.Keys.BACK)
		{
			graphics.exit(2);
			return true;
		}

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