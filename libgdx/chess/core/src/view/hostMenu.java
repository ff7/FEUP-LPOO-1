package view;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import model.Chess;
import model.Server;


public class hostMenu extends ScreenAdapter implements InputProcessor, Input.TextInputListener {

    private Chess game;
    private SpriteBatch batch;
    private Texture img;
    private BitmapFont bitmapfont;
    private Server server;
    private String IPAdress;
    public boolean startGame = false;


    public hostMenu(Chess game)
    {
        img = new Texture("images/menus/hostMenu.png");
        this.game = game;
        batch = game.getBatch();
        bitmapfont = new BitmapFont(Gdx.files.internal("fonts/Caveat.fnt"));
        bitmapfont.setColor(1, 0, 0, 1);
        bitmapfont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        bitmapfont.getData().setScale((float)(0.8* Gdx.graphics.getWidth()/504));

        server = new Server();
        server.start();

        IPAdress = server.getIPAddress();

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta)
    {
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        batch.begin();
        batch.draw(img, 0,0,width, height);
        bitmapfont.draw(batch, "Your ip adress is : " + IPAdress, (int)(0.25*width), (int)(0.62*height));

        if (server.isBound())
            startGame();

        batch.end();
    }


    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {

        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        if (button == Input.Buttons.LEFT) {
            if (x > 0.19 * width && x < (0.19 + 0.58) * width && y > 0.82 * height && y < 0.92 * height) //Go Back
            {
                server.closeServer();
                game.setScreen(new networkMenu(game));
            }
            return true;
        }
        return false;
    }

    public void startGame()
    {
        game.setScreen(new Graphics(game, server));
    }


    @Override
    public boolean keyDown(int keycode) {

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

    @Override
    public void input(String text) {
        IPAdress = text;
    }

    @Override
    public void canceled() {

    }
}