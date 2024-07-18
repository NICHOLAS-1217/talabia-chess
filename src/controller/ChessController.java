package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.MouseInputListener;

import view.Board;
import model.Game;

//controller for the chess game (ernest)
public class ChessController extends JFrame implements MouseInputListener {
    private Game model;

    private static final int BORDER_WIDTH = 3;
    private boolean isSelected = false;
    private JLabel selectedLabel;
    private int[] from;
    private int[] to;

    public ChessController(Board view, Game model) {
        this.model = model;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        JLabel label = (JLabel) e.getSource();
        int[] coord = (int[]) label.getClientProperty("coord");

        if (!isSelected) {
            from = coord;
            if (this.model.board[from[0]][from[1]] != ' ') {
                isSelected = !isSelected;

                // Remove border from the previously selected square
                if (selectedLabel != null) {
                    selectedLabel.setBorder(null);
                }

                // Add border to the currently selected square
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK, BORDER_WIDTH));
                selectedLabel = label;
            }
        } else {
            to = coord;
            this.model.checkPiece(from, to);
            isSelected = !isSelected;

            // Remove border from the previously selected square
            if (selectedLabel != null) {
                selectedLabel.setBorder(null);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
