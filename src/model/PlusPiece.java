package model;

import javax.swing.ImageIcon;

// Plus piece (nicholas)
public class PlusPiece extends Piece {

    public PlusPiece(boolean isYellow, int row, int col) {
        super(isYellow, row, col);
        // TODO Auto-generated constructor stub
        this.image = isYellow() ? new ImageIcon("lib/img/wPlus.png") : new ImageIcon("lib/img/bPlus.png");
    }

    public boolean checkMove(int[] from, int[] to, boolean yellowTurn, char[][] board) {
        if (to[0] == from[0]) {
            // check horizontal move
            return checkHorizantalMove(from, to, board);
        } else if (to[1] == from[1]) {
            // check vertical move
            return checkVerticalMove(from, to, board);
        }
        return false;
    }

    private boolean checkHorizantalMove(int[] from, int[] to, char[][] board) {
        int min = Math.min(from[0], to[0]);
        int max = Math.max(from[0], to[0]);

        // check if there's any obstacles in between
        for (int x = min + 1; x < max; x++) {
            if (board[from[0]][x] != ' ') {
                return false;
            }
        }
        return true;
    }

    private boolean checkVerticalMove(int[] from, int[] to, char[][] board) {
        int min = Math.min(from[1], to[1]);
        int max = Math.max(from[1], to[1]);

        // check if there's any obstacles in between
        for (int y = min + 1; y < max; y++) {
            if (board[y][from[1]] != ' ') {
                return false;
            }
        }
        return true;
    }
}
