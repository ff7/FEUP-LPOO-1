package view;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import model.Chess;


public class whiteVictory extends ScreenAdapter implements InputProcessor {

    private Chess game;
    private SpriteBatch batch;
    private Texture img;

    public whiteVictory(Chess game)
    {
        img = new Texture("images/menus/whiteVictory.png");
        this.game = game;
        this.batch = game.getBatch();

        Gdx.input.setInputProcessor(this);

        game.stretchPort.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //Prompts stretchPort to update
    }

    @Override
    public void render(float delta)
    {
        batch.begin();

        batch.draw(img, 0, 0, game.width, game.height);

        batch.end();
    }


    @Override
    public boolean touchDown(int x, int y, int pointer, int button){

        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        if (button == Input.Buttons.LEFT)
        {
            if (x > (float)213/1000 * width && x < (float)786/1000 * width && y > (float)743/1344 * height && y < (float)880/1344 * height) //Main Menu
            {
                game.setScreen(new mainMenuGraphics(game));
            }
            else if (x > (float)213/1000 * width && x < (float)786/1000 * width && y > (float)1035/1344 * height && y < (float)1172/1344 * height) //Exit
            {
                Gdx.app.exit();
            }

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
