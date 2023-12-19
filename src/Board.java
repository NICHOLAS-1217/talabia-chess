import java.awt.*;
import javax.swing.*;

public class Board extends JPanel {

    public static final int blockSize = 80;
    private static final int column = 7;
    private static final int row = 6;

    private Piece[][] pieces = new Piece[row][column];

    public Board() {
        this.setPreferredSize(new Dimension(column * blockSize, row * blockSize));
        initializePieces();
    }

    private void initializePieces() {

        pieces[0][0] = new Piece(true, new ImageIcon("src/img/bPlus.png"));
        pieces[0][1] = new Piece(true, new ImageIcon("src/img/bHourglass.png"));
        pieces[0][2] = new Piece(true, new ImageIcon("src/img/bX.png"));
        pieces[0][3] = new Piece(true, new ImageIcon("src/img/bSun.png"));
        pieces[0][4] = new Piece(true, new ImageIcon("src/img/bX.png"));
        pieces[0][5] = new Piece(true, new ImageIcon("src/img/bHourglass.png"));
        pieces[0][6] = new Piece(true, new ImageIcon("src/img/bPlus.png"));
        pieces[1][0] = new Piece(true, new ImageIcon("src/img/bArrow.png"));
        pieces[1][1] = new Piece(true, new ImageIcon("src/img/bArrow.png"));
        pieces[1][2] = new Piece(true, new ImageIcon("src/img/bArrow.png"));
        pieces[1][3] = new Piece(true, new ImageIcon("src/img/bArrow.png"));
        pieces[1][4] = new Piece(true, new ImageIcon("src/img/bArrow.png"));
        pieces[1][5] = new Piece(true, new ImageIcon("src/img/bArrow.png"));
        pieces[1][6] = new Piece(true, new ImageIcon("src/img/bArrow.png"));

        pieces[5][0] = new Piece(true, new ImageIcon("src/img/wPlus.png"));
        pieces[5][1] = new Piece(true, new ImageIcon("src/img/wHourglass.png"));
        pieces[5][2] = new Piece(true, new ImageIcon("src/img/wX.png"));
        pieces[5][3] = new Piece(true, new ImageIcon("src/img/wSun.png"));
        pieces[5][4] = new Piece(true, new ImageIcon("src/img/wX.png"));
        pieces[5][5] = new Piece(true, new ImageIcon("src/img/wHourglass.png"));
        pieces[5][6] = new Piece(true, new ImageIcon("src/img/wPlus.png"));
        pieces[4][0] = new Piece(true, new ImageIcon("src/img/wArrow.png"));
        pieces[4][1] = new Piece(true, new ImageIcon("src/img/wArrow.png"));
        pieces[4][2] = new Piece(true, new ImageIcon("src/img/wArrow.png"));
        pieces[4][3] = new Piece(true, new ImageIcon("src/img/wArrow.png"));
        pieces[4][4] = new Piece(true, new ImageIcon("src/img/wArrow.png"));
        pieces[4][5] = new Piece(true, new ImageIcon("src/img/wArrow.png"));
        pieces[4][6] = new Piece(true, new ImageIcon("src/img/wArrow.png"));
        // for (int r = 0; r < row; r++) {
        //     for (int c = 0; c < column; c++) {
        //         if ((c + r) % 2 == 0) {
        //             pieces[r][c] = new Piece(new ImageIcon("src/image/bArrow.png"), true);
        //         } else {
        //             pieces[r][c] = new Piece(new ImageIcon("src/image/wSun.png"), false);
        //         }
        //     }
        // }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
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
