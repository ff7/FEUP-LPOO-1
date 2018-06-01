package view;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import model.Chess;


public class youWon extends ScreenAdapter implements InputProcessor {

    private Chess game;
    private SpriteBatch batch;
    private Texture img;

    private BitmapFont font;

    public youWon(Chess game)
    {
        img = new Texture("images/menus/youWon.png");
        this.game = game;
        this.batch = game.getBatch();

        font = new BitmapFont(Gdx.files.internal("myFont.fnt"));
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta)
    {
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        batch.begin();
        batch.draw(img, 0,0,width, height);;
        //font.getData().setScale(2);
        //font.draw(batch, "Time to Chess", 180, 450);
        batch.end();

    }


    @Override
    public boolean touchDown(int x, int y, int pointer, int button){

        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        if (button == Input.Buttons.LEFT)
        {
            if (x > 0.21 * width && x < (0.21 + 0.58) * width && y > 0.64 * height && y < 0.74 * height) //MultiPlayer Menu
                game.setScreen(new mainMenuGraphics(game));
            else if (x > 0.21 * width && x < (0.21 + 0.58) * width && y > 0.86 * height && y < 0.96 * height) //Exit Menu
                Gdx.app.exit();
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