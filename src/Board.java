import java.awt.*;
import javax.swing.*;

public class Board extends JPanel {

    public final int blockSize = 80;
    private static final int blockColumn = 7;
    private static final int blockRow = 6;

    public Piece[][] pieces = new Piece[blockRow][blockColumn];
    public Piece selectedPiece;

    Input input = new Input(this);

    public Board() {
        this.setPreferredSize(new Dimension(blockColumn * blockSize, blockRow * blockSize));
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        initializePieces();
    }

    public Piece getPiece(int column, int row) {
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                Piece piece = pieces[r][c];
                if (piece.column == column && piece.row == row ) {
                    return piece;
                }
            }
        }
        return null;
    }

    public boolean isValidMove(Move move) {
        if (sameTeam(move.piece, move.capture)) {
            return false;
        }
        return true;
    }

    public void makeMove(Move move){
        move.piece.column = move.newColumn;
        move.piece.row = move.newRow;
        move.piece.x = move.newColumn * blockSize;
        move.piece.y = move.newRow * blockSize;
        capture(move);
    }

    public void capture(Move move) {
        Piece capturedPiece = move.capture;
    
        if (capturedPiece != null) {
            // Remove the captured piece from the board
            pieces[capturedPiece.row][capturedPiece.column] = null;
        }

        System.out.println(capturedPiece.row);
    }

    public boolean sameTeam(Piece p1, Piece p2) {
        if (p1 == null || p2 == null) {
            return false;
        }
        return p1.isWhite() == p2.isWhite();
    }

    private void initializePieces() {

        pieces[0][0] = new Piece(this,0,0,"plus",false, new ImageIcon("src/img/bPlus.png"));
        pieces[0][1] = new Piece(this,0,1,"hourglass",false, new ImageIcon("src/img/bHourglass.png"));
        pieces[0][2] = new Piece(this,0,2,"x",false, new ImageIcon("src/img/bX.png"));
        pieces[0][3] = new Piece(this,0,3,"sun",false, new ImageIcon("src/img/bSun.png"));
        pieces[0][4] = new Piece(this,0,4,"x",false, new ImageIcon("src/img/bX.png"));
        pieces[0][5] = new Piece(this,0,5,"hourglass",false, new ImageIcon("src/img/bHourglass.png"));
        pieces[0][6] = new Piece(this,0,6,"plus",false, new ImageIcon("src/img/bPlus.png"));
        pieces[1][0] = new Piece(this,1,0,"arrow",false, new ImageIcon("src/img/bArrow.png"));
        pieces[1][1] = new Piece(this,1,1,"arrow",false, new ImageIcon("src/img/bArrow.png"));
        pieces[1][2] = new Piece(this,1,2,"arrow",false, new ImageIcon("src/img/bArrow.png"));
        pieces[1][3] = new Piece(this,1,3,"arrow",false, new ImageIcon("src/img/bArrow.png"));
        pieces[1][4] = new Piece(this,1,4,"arrow",false, new ImageIcon("src/img/bArrow.png"));
        pieces[1][5] = new Piece(this,1,5,"arrow",false, new ImageIcon("src/img/bArrow.png"));
        pieces[1][6] = new Piece(this,1,6,"arrow",false, new ImageIcon("src/img/bArrow.png"));

        pieces[5][0] = new Piece(this,5,0,"plus", true, new ImageIcon("src/img/wPlus.png"));
        pieces[5][1] = new Piece(this,5,1,"hourglass", true, new ImageIcon("src/img/wHourglass.png"));
        pieces[5][2] = new Piece(this,5,2,"x", true, new ImageIcon("src/img/wX.png"));
        pieces[5][3] = new Piece(this,5,3,"sun", true, new ImageIcon("src/img/wSun.png"));
        pieces[5][4] = new Piece(this,5,4,"x", true, new ImageIcon("src/img/wX.png"));
        pieces[5][5] = new Piece(this,5,5,"hourglass", true, new ImageIcon("src/img/wHourglass.png"));
        pieces[5][6] = new Piece(this,5,6,"plus", true, new ImageIcon("src/img/wPlus.png"));
        pieces[4][0] = new Piece(this,4,0,"arrow", true, new ImageIcon("src/img/wArrow.png"));
        pieces[4][1] = new Piece(this,4,1,"arrow", true, new ImageIcon("src/img/wArrow.png"));
        pieces[4][2] = new Piece(this,4,2,"arrow", true, new ImageIcon("src/img/wArrow.png"));
        pieces[4][3] = new Piece(this,4,3,"arrow", true, new ImageIcon("src/img/wArrow.png"));
        pieces[4][4] = new Piece(this,4,4,"arrow", true, new ImageIcon("src/img/wArrow.png"));
        pieces[4][5] = new Piece(this,4,5,"arrow", true, new ImageIcon("src/img/wArrow.png"));
        pieces[4][6] = new Piece(this,4,6,"arrow", true, new ImageIcon("src/img/wArrow.png"));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        for (int r = 0; r < blockRow; r++) {
            for (int c = 0; c < blockColumn; c++) {
                g2d.setColor((c + r) % 2 == 0 ? new Color(227, 198, 181) : new Color(157, 105, 53));
                g2d.fillRect(c * blockSize, r * blockSize, blockSize, blockSize);

                // Draw pieces
                Piece piece = pieces[r][c];
                if (piece != null) {
                    Image image = piece.getImage().getImage();
                    g2d.drawImage(image, c * blockSize, r * blockSize, blockSize, blockSize, null);
                }
            }
        }
    }
}