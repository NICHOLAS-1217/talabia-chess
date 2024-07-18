package model;

import javax.swing.ImageIcon;

// Point piece (nicholas)
public class PointPiece extends Piece {
    private boolean isFlipped;

    public PointPiece(boolean isYellow, int row, int col, boolean isFlipped) {
        super(isYellow, row, col);
        // TODO Auto-generated constructor stub
        this.image = isYellow() ? new ImageIcon("lib/img/wArrow.png") : new ImageIcon("lib/img/bArrow.png");
        this.isFlipped = isFlipped;
    }

    // check point piece move
    public boolean checkMove(int[] from, int[] to, boolean yellowTurn, char[][] board) {
        // check if piece is moving the correct direction
        if ((to[0] < from[0] && !isFlipped) || (to[0] > from[0] && isFlipped)) {
            return checkSteps(from, to, 2, board);
        }
        return false;
    }

    // check if piece steps are valid
    private boolean checkSteps(int[] from, int[] to, int maxSteps, char[][] board) {
        // check if piece is moving
        if (from[1] != to[1])
            return false;

        // check if pieces only moves 1 or 2 steps forward at most
        int steps = Math.abs(to[0] - from[0]);

        // ensure that maximum steps is 2
        if (steps <= maxSteps) {
            if (steps == 2) {
                return checkObstacles(from, to, board);
            }

            if ((to[0] == 5 || to[0] == 0) && !isFlipped) {
                isFlipped = !isFlipped;
            }
            return true;
        }
        return false;
    }

    // check if there are any obstacles in between point piece
    private boolean checkObstacles(int[] from, int[] to, char[][] board) {
        // ensure there's no obstacles in between when moving 2 steps
        int mid = (from[0] + to[0]) / 2;

        if (board[mid][from[1]] == ' ') {
            return true;
        }
        return false;
    }

    public boolean getIsFlipped() {
        return isFlipped;
    }
}
