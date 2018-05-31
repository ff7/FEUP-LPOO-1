package view;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import model.Chess;


public class networkMenu extends ScreenAdapter implements InputProcessor {

    private Chess game;
    private SpriteBatch batch;
    private Texture img;

    public networkMenu(Chess game)
    {
        img = new Texture("images/menus/multiPlayerMenu.png");
        this.game = game;
        this.batch = game.getBatch();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta)
    {
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        batch.begin();
        batch.draw(img, 0,0,width, height);
        batch.end();
    }


    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {

        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        if (button == Input.Buttons.LEFT) {
            if (x > 0.19 * width && x < (0.19 + 0.58) * width && y > 0.41 * height && y < 0.51 * height) //Connect
                game.setScreen(new connectMenu(game));
            else if (x > 0.19 * width && x < (0.19 + 0.58) * width && y > 0.62 * height && y < 0.72 * height) //Host
                game.setScreen(new hostMenu(game));
            else if (x > 0.19 * width && x < (0.19 + 0.58) * width && y > 0.82 * height && y < 0.92 * height) //Go Back
                game.setScreen(new playerMenuGraphics(game));
            return true;
        }
        return false;
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