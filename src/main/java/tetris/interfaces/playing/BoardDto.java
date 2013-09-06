package tetris.interfaces.playing;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Result")
public class BoardDto {
    private String[][] grid;

    private ScoreDto score;

    private PieceDto piece;

    private boolean gameOver;

    public String[][] getGrid() {
        return grid;
    }

    public void setGrid(String[][] grid) {
        this.grid = grid;
    }

    public PieceDto getPiece() {
        return piece;
    }

    public void setPiece(PieceDto piece) {
        this.piece = piece;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameover) {
        this.gameOver = gameover;
    }

    public ScoreDto getScore() {
        return score;
    }

    public void setScore(ScoreDto score) {
        this.score = score;
    }
}
