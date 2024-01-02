import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Input extends MouseAdapter{

    Board board;

    public Input (Board board) {
        this.board = board;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int column = e.getX() / board.blockSize;
        int row = e.getY() / board.blockSize;
        Piece pieceXY = board.getPiece(column, row);
        if (pieceXY != null) {
            board.selectedPiece = pieceXY;
        }
    }

    
    @Override
    public void mouseDragged(MouseEvent e) {
        if (board.selectedPiece != null) {
            board.selectedPiece.x = e.getX() - board.blockSize / 2;
            board.selectedPiece.y = e.getY() - board.blockSize / 2;

            board.repaint();
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        int column = e.getX() / board.blockSize;
        int row = e.getY() / board.blockSize;
        if (board.selectedPiece != null) {
            Move move = new Move(board, board.selectedPiece, column, row);
            if (board.isValidMove(move)) {
                board.makeMove(move);
            } else {
                board.selectedPiece.x = board.selectedPiece.column * board.blockSize;
                board.selectedPiece.y = board.selectedPiece.row * board.blockSize;
            }
        }
        board.selectedPiece = null;
        board.repaint();
    }

}
