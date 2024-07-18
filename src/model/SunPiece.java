package model;

import javax.swing.ImageIcon;

// Sun piece (nicholas)
public class SunPiece extends Piece {

    public SunPiece(boolean isYellow, int row, int col) {
        super(isYellow, row, col);
        //TODO Auto-generated constructor stub
        this.image = isYellow() ? new ImageIcon("lib/img/wSun.png") : new ImageIcon("lib/img/bSun.png");
    }
    
    public boolean checkMove(int[] from, int[] to, boolean yellowTurn, char[][] board) {
        int spaceX = Math.abs(from[1] - to[1]);
        int spaceY = Math.abs(from[0] - to[0]);

        return spaceX <= 1 && spaceY <= 1; 
    }
}
