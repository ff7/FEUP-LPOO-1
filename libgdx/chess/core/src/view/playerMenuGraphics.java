package view;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.IOException;

import model.Chess;


public class playerMenuGraphics extends ScreenAdapter implements InputProcessor {

    private Chess game;
    private SpriteBatch batch;
    private Texture img;

    private BitmapFont font;

    public playerMenuGraphics(Chess game)
    {
        img = new Texture("images/menus/optionMenu.png");
        this.game = game;
        this.batch = game.getBatch();
        //font = new BitmapFont(Gdx.files.internal("myFont.fnt"));
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta)
    {
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        batch.begin();
        batch.draw(img, 0,0,width, height);
        //font.draw(batch, "Time to Chess", 250, 400);
        batch.end();
    }


    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {

        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        if (button == Input.Buttons.LEFT) {
            if (x > 0.19 * width && x < (0.19 + 0.58) * width && y > 0.41 * height && y < 0.51 * height) //Same Device Menu
                game.setScreen(new Graphics(game, false));
            else if (x > 0.19 * width && x < (0.19 + 0.58) * width && y > 0.62 * height && y < 0.72 * height) //Different Devices Menu
                game.setScreen(new Graphics(game, false));
            else if (x > 0.19 * width && x < (0.19 + 0.58) * width && y > 0.82 * height && y < 0.92 * height) //Go Back
                game.setScreen(new mainMenuGraphics(game));
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