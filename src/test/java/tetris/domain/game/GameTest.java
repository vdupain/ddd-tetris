package tetris.domain.game;

import junit.framework.Assert;

import org.junit.Test;

public class GameTest {

    @Test
    public void testMovePieceLeft_EmptyBoard_Moved() {
        final Game game = new Game(Board.defaultBoard(), new Shape(5, 0, Tetromino.I));

        game.movePieceLeft();

        Assert.assertEquals(4, game.getPiece().getX());
    }

    @Test
    public void testMovePieceLeft_FullBoard_None() {
        final Game game = new Game(Board.defaultBoard().fill(), new Shape(5, 0, Tetromino.I));

        game.movePieceLeft();

        Assert.assertEquals(5, game.getPiece().getX());
    }

    @Test
    public void testFallPiece_EmptyBoard_PieceFallen() {
        final Game game = new Game(Board.defaultBoard(), new Shape(5, 0, Tetromino.I));

        game.fallPiece();

        Assert.assertEquals(1, game.getPiece().getY());
    }

    @Test
    public void testFallPiece_FullBoard_PieceNotFallen() {
        final Game game = new Game(Board.defaultBoard().fill(), new Shape(5, 0, Tetromino.I));

        game.fallPiece();

        Assert.assertEquals(0, game.getPiece().getY());
    }

    @Test
    public void testFallPiece_FullBoard_PieceFilled() {
        final Game game = new Game(Board.defaultBoard().fill(), new Shape(5, 0, Tetromino.O));

        game.fallPiece();

        Assert.assertEquals(Tetromino.O, game.getBoard().getBlockAt(6, 1).getTetromino());
    }
}