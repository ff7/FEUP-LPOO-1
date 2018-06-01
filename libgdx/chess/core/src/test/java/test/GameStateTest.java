package test;

import com.badlogic.gdx.Game;
import model.*;
import model.Character;
import model.GameState;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class GameStateTest {

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

    private Character[][] successfulCheckMateMap =
            {

                    {new Floor(), new Floor(), new Floor(), new Queen(), new King(), new Bishop(), new Knight(), new Rook()},
                    {new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Pawn(), new Pawn(), new Pawn()},
                    {new Floor(), new Floor(), new Knight(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
                    {new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
                    {new Pawn(), new Floor(), new Pawn(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
                    {new Floor(), new Floor(), new Pawn(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
                    {new Pawn(), new Pawn(), new Floor(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn()},
                    {new Rook(), new Knight(), new Bishop(), new Floor(), new King(), new Bishop(), new Floor(), new Rook()}

            };

    private Character[][] successFulCheckMap =
            {

                    {new Floor(), new Floor(), new Floor(), new Floor(), new King(), new Bishop(), new Knight(), new Rook()},
                    {new Floor(), new Floor(), new Floor(), new Queen(), new Floor(), new Pawn(), new Pawn(), new Pawn()},
                    {new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
                    {new Floor(), new Floor(), new Knight(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
                    {new Pawn(), new Floor(), new Pawn(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
                    {new Floor(), new Floor(), new Pawn(), new Floor(), new Floor(), new Floor(), new Floor(), new Floor()},
                    {new Pawn(), new Pawn(), new Floor(), new Pawn(), new Pawn(), new Pawn(), new Pawn(), new Pawn()},
                    {new Rook(), new Knight(), new Bishop(), new Floor(), new King(), new Bishop(), new Floor(), new Rook()}

            };

    public void initializeMap(Character[][] map)
    {
        for(int i = 0;i < map.length; i++)
        {
            for (int j = 0; j < map[i].length; j++)
            {
                map[i][j].setPos(new Pair<Integer, Integer>(j,i));
            }
        }
    }

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

    @Test
    public void swapPlayer(){
        Chess chess = new model.Chess();
        chess.setStockfishPath("binaries/stockfish");
        GameState gs = new GameState(1, chess.getStockfishPath(), true);
        assertEquals(gs.player, 0);
        gs.swapPlayer();
        assertEquals(gs.player, 1);
    }

    @Test
    public void otherPlayer()
    {
        Chess chess = new model.Chess();
        chess.setStockfishPath("binaries/stockfish");
        GameState gs = new GameState(1, chess.getStockfishPath(), true);
        assertEquals(gs.player, 0);
        assertEquals(gs.otherPlayer(), 1);
    }

    @Test
    public void verifyFailedCheck()
    {
        Chess chess = new model.Chess();
        chess.setStockfishPath("binaries/stockfish");
        GameState gs = new GameState(1, chess.getStockfishPath(), true);
        assertFalse(gs.verifyCheck(gs.otherPlayer(), gs.getMap().getKingsPosition(gs.player)));
    }

    @Test
    public void verifyFailedCheckMate()
    {
        Chess chess = new model.Chess();
        chess.setStockfishPath("binaries/stockfish");
        GameState gs = new GameState(1, chess.getStockfishPath(), true);
        assertFalse(gs.verifyCheckMate(gs.otherPlayer(), gs.getMap().getKingsPosition(gs.player)));
    }

    @Test
    public void verifySuccessfulCheck()
    {
        Chess chess = new model.Chess();
        chess.setStockfishPath("binaries/stockfish");
        GameState gs = new GameState(1, chess.getStockfishPath(), true);

        gs.getMap().setMap(successFulCheckMap);
        initializeMap(gs.getMap().getMap());

        gs.getMap().getMap()[0][3].setPlayer(0, true);
        gs.getMap().getMap()[0][4].setPlayer(1, true);
        gs.getMap().getMap()[0][5].setPlayer(1, true);
        gs.getMap().getMap()[0][6].setPlayer(1, true);
        gs.getMap().getMap()[0][7].setPlayer(1, true);
        gs.getMap().getMap()[1][5].setPlayer(1, true);
        gs.getMap().getMap()[1][6].setPlayer(1, true);
        gs.getMap().getMap()[1][7].setPlayer(1, true);
        gs.getMap().getMap()[3][2].setPlayer(0, true);
        gs.getMap().getMap()[4][0].setPlayer(1, true);
        gs.getMap().getMap()[4][2].setPlayer(1, true);
        gs.getMap().getMap()[5][2].setPlayer(1, true);

        gs.getMap().getMap()[6][0].setPlayer(0, true);
        gs.getMap().getMap()[6][1].setPlayer(0, true);
        gs.getMap().getMap()[6][3].setPlayer(0, true);
        gs.getMap().getMap()[6][4].setPlayer(0, true);
        gs.getMap().getMap()[6][5].setPlayer(0, true);
        gs.getMap().getMap()[6][6].setPlayer(0, true);
        gs.getMap().getMap()[6][7].setPlayer(0, true);
        gs.getMap().getMap()[7][0].setPlayer(0, true);
        gs.getMap().getMap()[7][1].setPlayer(0, true);
        gs.getMap().getMap()[7][2].setPlayer(0, true);
        gs.getMap().getMap()[7][4].setPlayer(0, true);
        gs.getMap().getMap()[7][5].setPlayer(0, true);
        gs.getMap().getMap()[7][7].setPlayer(0, true);

        gs.swapPlayer();
        assertTrue(gs.verifyCheck(gs.otherPlayer(), gs.getMap().getKingsPosition(gs.player)));
    }

    @Test
    public void verifySuccessfulCheckMate()
    {
        Chess chess = new model.Chess();
        chess.setStockfishPath("binaries/stockfish");
        GameState gs = new GameState(1, chess.getStockfishPath(), true);

        gs.getMap().setMap(successfulCheckMateMap);
        initializeMap(gs.getMap().getMap());

        gs.getMap().getMap()[0][3].setPlayer(0, true);
        gs.getMap().getMap()[0][4].setPlayer(1, true);
        gs.getMap().getMap()[0][5].setPlayer(1, true);
        gs.getMap().getMap()[0][6].setPlayer(1, true);
        gs.getMap().getMap()[0][7].setPlayer(1, true);
        gs.getMap().getMap()[1][5].setPlayer(1, true);
        gs.getMap().getMap()[1][6].setPlayer(1, true);
        gs.getMap().getMap()[1][7].setPlayer(1, true);
        gs.getMap().getMap()[2][2].setPlayer(0, true);
        gs.getMap().getMap()[4][0].setPlayer(1, true);
        gs.getMap().getMap()[4][2].setPlayer(1, true);
        gs.getMap().getMap()[5][2].setPlayer(1, true);

        gs.getMap().getMap()[6][0].setPlayer(0, true);
        gs.getMap().getMap()[6][1].setPlayer(0, true);
        gs.getMap().getMap()[6][3].setPlayer(0, true);
        gs.getMap().getMap()[6][4].setPlayer(0, true);
        gs.getMap().getMap()[6][5].setPlayer(0, true);
        gs.getMap().getMap()[6][6].setPlayer(0, true);
        gs.getMap().getMap()[6][7].setPlayer(0, true);
        gs.getMap().getMap()[7][0].setPlayer(0, true);
        gs.getMap().getMap()[7][1].setPlayer(0, true);
        gs.getMap().getMap()[7][2].setPlayer(0, true);
        gs.getMap().getMap()[7][4].setPlayer(0, true);
        gs.getMap().getMap()[7][5].setPlayer(0, true);
        gs.getMap().getMap()[7][7].setPlayer(0, true);

        gs.swapPlayer();
        gs.updateGameStatus();
        assertTrue(gs.verifyCheckMate(gs.otherPlayer(), gs.getMap().getKingsPosition(gs.player)));

    }

    @Test
    public void gamestateTest()
    {
        Chess chess = new model.Chess();
        chess.setStockfishPath("binaries/stockfish_mac");
        GameState gs = new GameState(0, chess.getStockfishPath(), true);
        try {
            gs.goAI();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(gs.opponentType, 0);

    }

    @Test
    public void moveAI()
    {
        Chess chess = new model.Chess();
        chess.setStockfishPath("binaries/stockfish_mac");
        GameState gs = new GameState(0, chess.getStockfishPath(), true);
        try {
            gs.getAIAnswer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gs.printAIAnswer();
        try {
            gs.printAIBoard();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gs.getInvertedMoveSymbol(gs.getMap().getMap()[0][0], gs.getMap().getMap()[1][1]);
        gs.getMoveSymbol(gs.getMap().getMap()[0][0], gs.getMap().getMap()[1][1]);
        assertEquals(gs.opponentType, 0);
        assertFalse(gs.gameOver);

    }

    @Test
    public void testUpdates()
    {
        Chess chess = new model.Chess();
        chess.setStockfishPath("binaries/stockfish_mac");
        GameState gs = new GameState(0, chess.getStockfishPath(), true);
        gs.updatePawns();
        gs.updateCastling();
        assertEquals(gs.gameStatus, 0);
    }

    @Test
    public void testServer()
    {
        Server s = new Server();
        Client c = new Client("0.0.0.0");
        assertEquals(s.t ,null);
    }

}