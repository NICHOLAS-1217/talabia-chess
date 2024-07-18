package model;

import javax.swing.ImageIcon;

// Sun piece (nicholas)
public class TimePiece extends Piece {

    public TimePiece(boolean isYellow, int row, int col) {
        super(isYellow, row, col);
        // TODO Auto-generated constructor stub
        this.image = isYellow() ? new ImageIcon("lib/img/wX.png") : new ImageIcon("lib/img/bX.png");
    }

    public boolean checkMove(int[] from, int[] to, boolean yellowTurn, char[][] board) {
        int xGap = Math.abs(from[0] - to[0]);
        int yGap = Math.abs(from[1] - to[1]);

        if (xGap == yGap) {
            return checkObstacles(from, to, board);
        }

        return false;
    }

    private boolean checkObstacles(int[] from, int[] to, char[][] board) {
        int incrementRow = 0;
        int incrementCol = 0;

        if (from[0] > to[0]) {
            incrementRow = -1;
        } else if (from[0] < to[0]) {
            incrementRow = 1;
        }

        if (from[1] > to[1]) {
            incrementCol = -1;
        } else if (from[1] < to[1]) {
            incrementCol = 1;
        }

        for (int row = from[0] + incrementRow,
                col = from[1] + incrementCol; row != to[0]; row += incrementRow, col += incrementCol) {
            System.out.println(row);
            System.out.println(col);
            if (board[row][col] != ' ') {
                return false;
            }
        }

        return true;
    }
}
