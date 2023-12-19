import javax.swing.ImageIcon;

public class Piece {

    private boolean isWhite;
    private ImageIcon image;

    public Piece(boolean isWhite, ImageIcon image) {
        this.isWhite = isWhite;
        this.image = image;
    }

    public ImageIcon getImage() {
        return image;
    }

    public boolean isWhite() {
        return isWhite;
    }
}
