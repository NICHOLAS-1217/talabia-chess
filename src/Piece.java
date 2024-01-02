import javax.swing.ImageIcon;

public class Piece {

    public String name;
    private boolean isWhite;
    private ImageIcon image;
    int row;
    int column;
    int x;
    int y;

    public Piece(Board board, int row, int column, String name, boolean isWhite, ImageIcon image) {
        this.row = row;
        this.column = column;
        this.x = column * board.blockSize;
        this.y = row * board.blockSize;
        this.name = name;
        this.isWhite = isWhite;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public ImageIcon getImage() {
        return image;
    }

    public boolean isWhite() {
        return isWhite;
    }
}