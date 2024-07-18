package model;

import javax.swing.ImageIcon;

// Hourglass piece (nicholas)
public class HourglassPiece extends Piece {

    public HourglassPiece(boolean isYellow, int row, int col) {
        super(isYellow, row, col);
        //TODO Auto-generated constructor stub
        this.image = isYellow() ? new ImageIcon("lib/img/wHourglass.png") : new ImageIcon("lib/img/bHourglass.png");
    }

    public boolean checkMove(int[] from, int[] to, boolean yellowTurn, char[][] board) {
        int xSpace = Math.abs(from[1] - to[1]);
        int ySpace = Math.abs(from[0] - to[0]);
        System.out.println("x" + xSpace);
        System.out.println("y" + ySpace);
        
        if (xSpace == 1 && ySpace == 2) {
            return true;
        } else if (xSpace == 2 && ySpace == 1) {
            return true;
        }

        return false;
    }
}
