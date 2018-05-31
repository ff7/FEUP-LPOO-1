package test;

import model.*;
import model.Character;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CharacterTest {

    private Character[][] map =
            {

                    {new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
                    {new Floor(), new Floor(), new Bishop(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
                    {new Floor(), new Floor(), new Floor(), new Floor(), new King(), new Floor(), new Floor(), new Floor()},
                    {new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
                    {new Floor(), new Queen(), new Floor(), new Floor(), new Knight(), new Floor(), new Floor(), new Floor()},
                    {new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
                    {new Pawn(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Rook(), new Floor()},
                    {new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()}

            };

    @Test
    public void getPlayer() {
        assertEquals(0, this.map[2][4].getPlayer());
    }

    @Test
    public void getChar() {
        assertEquals('K',this.map[2][4].getChar());
    }

    @Test
    public void getKingMovePosition() throws IOException {
        Map mapa = new Map(this.map);
        assertEquals(this.map[2][4].getPossible(mapa).size(), 8);
    }

    @Test
    public void getQueenMovePostion() throws IOException {
        Map mapa = new Map(this.map);

        assertTrue(this.map[4][1].getPossible(mapa).contains(new Pair<Integer, Integer> (2,4)));
        assertTrue(this.map[4][1].getPossible(mapa).contains(new Pair<Integer, Integer> (0,4)));
        assertTrue(this.map[4][1].getPossible(mapa).contains(new Pair<Integer, Integer> (1,5)));
        assertTrue(this.map[4][1].getPossible(mapa).contains(new Pair<Integer, Integer> (1,3)));

        assertTrue(this.map[4][1].getPossible(mapa).contains(new Pair<Integer, Integer> (2,3)));
        assertTrue(this.map[4][1].getPossible(mapa).contains(new Pair<Integer, Integer> (2,5)));
        assertTrue(this.map[4][1].getPossible(mapa).contains(new Pair<Integer, Integer> (0,3)));
        assertTrue(this.map[4][1].getPossible(mapa).contains(new Pair<Integer, Integer> (0,5)));

    }

    @Test
    public void getRookMovePostion() throws IOException {
        Map mapa = new Map(this.map);

        assertTrue(this.map[6][6].getPossible(mapa).contains(new Pair<Integer, Integer> (6,7)));
        assertTrue(this.map[6][6].getPossible(mapa).contains(new Pair<Integer, Integer> (6,5)));
        assertTrue(this.map[6][6].getPossible(mapa).contains(new Pair<Integer, Integer> (5,6)));
        assertTrue(this.map[6][6].getPossible(mapa).contains(new Pair<Integer, Integer> (7,6)));
    }

    @Test
    public void getBishopMovePosition() throws IOException {
        Map mapa = new Map(this.map);

        assertTrue(this.map[1][2].getPossible(mapa).contains(new Pair<Integer, Integer> (1,0)));
        assertTrue(this.map[1][2].getPossible(mapa).contains(new Pair<Integer, Integer> (1,2)));
        assertTrue(this.map[1][2].getPossible(mapa).contains(new Pair<Integer, Integer> (3,0)));
        assertTrue(this.map[1][2].getPossible(mapa).contains(new Pair<Integer, Integer> (3,2)));
    }

    @Test
    public void getKnightMovePosition() throws IOException {
        Map mapa = new Map(this.map);

        assertTrue(this.map[4][4].getPossible(mapa).contains(new Pair<Integer, Integer> (5,2)));
        assertTrue(this.map[4][4].getPossible(mapa).contains(new Pair<Integer, Integer> (6,3)));
        assertTrue(this.map[4][4].getPossible(mapa).contains(new Pair<Integer, Integer> (6,5)));
        assertTrue(this.map[4][4].getPossible(mapa).contains(new Pair<Integer, Integer> (5,6)));
        assertTrue(this.map[4][4].getPossible(mapa).contains(new Pair<Integer, Integer> (3,6)));
        assertTrue(this.map[4][4].getPossible(mapa).contains(new Pair<Integer, Integer> (2,5)));
        assertTrue(this.map[4][4].getPossible(mapa).contains(new Pair<Integer, Integer> (2,3)));
    }

    @Test
    public void getPawnMovePosition() throws IOException {
        Map mapa = new Map(this.map);

        assertTrue(this.map[6][0].getPossible(mapa).contains(new Pair<Integer, Integer> (0,5)));
        assertTrue(this.map[6][0].getPossible(mapa).contains(new Pair<Integer, Integer> (0,4)));
    }
}