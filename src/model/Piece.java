package model;

import javax.swing.ImageIcon;

// pieces blueprint (fulin)
public class Piece {

    private boolean isYellow;
    protected ImageIcon image;
    private Coordinate coord;

    public Piece(boolean isYellow, int row, int col) {
        this.isYellow = isYellow;
        this.coord = new Coordinate(row, col);
    }

    public ImageIcon getImage() {
        return image;
    }

    public boolean isYellow() {
        return isYellow;
    }

    public int[] getCoordinate() {
        int[] c = { coord.getRow(), coord.getCol() };
        return c;
    }
}
