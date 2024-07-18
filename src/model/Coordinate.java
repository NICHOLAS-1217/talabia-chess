package model;

// giving the coordinates of the pieces (lai)
public class Coordinate {
    private int row;
    private int col;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setCoord(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
