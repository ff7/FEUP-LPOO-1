package test;

import model.*;
import model.Character;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterTest {

    private Character[][] map =
            {

                    {new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
                    {new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
                    {new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
                    {new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
                    {new Floor(), new Floor(), new Floor(), new King(), new Floor(), new Floor(), new Floor(), new Floor()},
                    {new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
                    {new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
                    {new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()}

            };

    @Test
    public void getPlayer() {
        assertEquals(0, this.map[4][3].getPlayer());
    }

    @Test
    public void getChar() {
        assertEquals('K',this.map[4][3].getChar());
    }
}