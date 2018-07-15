package view;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import model.Chess;


public class mainMenuGraphics extends ScreenAdapter implements InputProcessor {

    private Chess game;
    private SpriteBatch batch;
    private Texture img;

    public mainMenuGraphics(Chess game)
    {
        img = new Texture("images/menus/mainMenu.png");
        this.game = game;
        this.batch = game.getBatch();

        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);

        game.stretchPort.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //Prompts stretchPort to update

        Gdx.graphics.requestRendering();
    }

    @Override
    public void render(float delta)
    {
        batch.begin();

        batch.draw(img, 0, 0, game.width, game.height);

        batch.end();
    }


    @Override
    public boolean touchDown(int x, int y, int pointer, int button)
    {
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        if (button == Input.Buttons.LEFT)
        {
            if (x > (float)213/1000 * width && x < (float)786/1000 * width && y > (float)575/1344 * height && y < (float)711/1344 * height) //Single Player Menu
            {
                game.setOpponentType(0);
                game.setScreen(new Graphics(game));
            }
            else if (x > (float)213/1000 * width && x < (float)786/1000 * width && y > (float)828/1344 * height && y < (float)964/1344 * height) //MultiPlayer Menu
                game.setScreen(new playerMenuGraphics(game));
            else if (x > (float)213/1000 * width && x < (float)786/1000 * width && y > (float)1095/1344 * height && y < (float)1231/1344 * height) //Exit Menu
                Gdx.app.exit();

            return true;
        }

        return false;
    }


    @Override
    public boolean keyDown(int keycode)
    {
        if (keycode == Input.Keys.ESCAPE || keycode == Input.Keys.BACK)
        {
            Gdx.app.exit();
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
