package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;
import controller.ChessController;
import model.*;

// defining the board
public class Board extends JFrame {
    private ChessController controller;
    private Game model;

    // create row and column variables for the board (fulin)
    public static final int blocksize = 100;
    private static final int column = 7;
    private static final int row = 6;

    // GUI components: placing the board, pieces, and labels (fulin)
    public JPanel boardPanel;
    public Piece[][] pieces = new Piece[row][column];
    public JLabel[][] label = new JLabel[row][column];
    private JLayeredPane layeredPane;
    private JLabel textLabel = new JLabel();
    private JLabel turnLabel = new JLabel();
    private JPanel buttonPanel;
    private JPanel labelPanel;

    // constructor for the board
    public Board() {
        // setting up the window (fulin)
        setTitle("Talabia Chess");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        getContentPane().add(layeredPane, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        labelPanel = new JPanel();
        getContentPane().add(labelPanel, BorderLayout.NORTH);

        // handling window resizing (fulin)
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustContent();
            }
        });
    }

    // error dialog function (sunterresaa)
    public void showErrorDialog(String message) {
        // Create a dialog with an OK button with invalid move message
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Adjusting Content on Resize (fulin)
    private void adjustContent() {
        // Clear existing components
        layeredPane.removeAll();
    
        // Initialize board and pieces again
        initializeBoard();
        initializePieces();
    
        // Re-add labels, buttons, and other components
        setLabel();
        setButtons();
    
        // Revalidate and repaint the layeredPane
        layeredPane.revalidate();
        layeredPane.repaint();
    }    

    // setting controller and model
    public void setController(ChessController controller) {
        this.controller = controller;
    }
    public void setModel(Game model) {
        this.model = model;
    }

    // initialize the game (fulin)
    public void initialize() {
        initializeBoard();
        initializePieces();
        setLabel();
        setButtons();

        setVisible(true);
    }

    // initializing the Board (fulin)
    private void initializeBoard() {
        Color light = new Color(227, 198, 181);
        Color dark = new Color(157, 105, 53);

        // Calculate the square size based on the minimum dimension of the window
        int squareSize = Math.min(getWidth(), getHeight()) / column;

        // Center the board by calculating offsets
        int xOffset = (getWidth() - squareSize * column) / 2;
        int yOffset = (getHeight() - squareSize * row) / 2 - 20;

        // Create squares dynamically based on row and column
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                JPanel square = new JPanel();
                square.setBounds(xOffset + j * squareSize, yOffset + i * squareSize, squareSize, squareSize);

                // Alternate the background color
                square.setBackground((i + j) % 2 == 0 ? light : dark);

                layeredPane.add(square, JLayeredPane.DEFAULT_LAYER);
            }
        }
    }

    // initializing the pieces (nicholas)
    private void initializePieces() {
        int squareSize = Math.min(getWidth(), getHeight()) / column;

        // Center the pieces by calculating offset
        int xOffset = (getWidth() - squareSize * column) / 2;
        int yOffset = (getHeight() - squareSize * row) / 2 - 20;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                boolean isYellow = Character.isUpperCase(model.board[i][j]) ? true : false;

                if (model.board[i][j] == 'p') {
                    pieces[i][j] = new PlusPiece(isYellow, i, j);
                } else if (model.board[i][j] == 'h') {
                    pieces[i][j] = new HourglassPiece(isYellow, i, j);
                } else if (model.board[i][j] == 't') {
                    pieces[i][j] = new TimePiece(isYellow, i, j);
                } else if (model.board[i][j] == 's') {
                    pieces[i][j] = new SunPiece(isYellow, i, j);
                } else if (model.board[i][j] == 'a') {
                    pieces[i][j] = new PointPiece(isYellow, i, j, false);
                } else if (model.board[i][j] == 'P') {
                    pieces[i][j] = new PlusPiece(isYellow, i, j);
                } else if (model.board[i][j] == 'H') {
                    pieces[i][j] = new HourglassPiece(isYellow, i, j);
                } else if (model.board[i][j] == 'T') {
                    pieces[i][j] = new TimePiece(isYellow, i, j);
                } else if (model.board[i][j] == 'S') {
                    pieces[i][j] = new SunPiece(isYellow, i, j);
                } else if (model.board[i][j] == 'A') {
                    pieces[i][j] = new PointPiece(isYellow, i, j, false);
                } else if (model.board[i][j] == 'r') {
                    pieces[i][j] = new PointPiece(isYellow, i, j, true);
                } else if (model.board[i][j] == 'R') {
                    pieces[i][j] = new PointPiece(isYellow, i, j, true);
                } else {
                    pieces[i][j] = null;
                }

                if (pieces[i][j] != null) {
                    label[i][j] = new JLabel(pieces[i][j].getImage());
                    int[] coord = { i, j };
                    label[i][j].putClientProperty("coord", coord);
    
                    // Adjust the bounds based on the calculated offsets and square size
                    label[i][j].setBounds(xOffset + j * squareSize, yOffset + i * squareSize, squareSize, squareSize);
                } else {
                    label[i][j] = new JLabel();
                    int[] coord = { i, j };
                    label[i][j].putClientProperty("coord", coord);
                    
                    // Adjust the bounds based on the calculated offsets and square size
                    label[i][j].setBounds(xOffset + j * squareSize, yOffset + i * squareSize, squareSize, squareSize);
                }
    
                layeredPane.add(label[i][j], JLayeredPane.DRAG_LAYER);
                label[i][j].addMouseListener(controller);
            }
        }

    }
 
    // updating the board (nicholas)
    public void updateBoard(int[] from, int[] to, char selected) {

        this.pieces[to[0]][to[1]] = this.pieces[from[0]][from[1]];
        this.pieces[from[0]][from[1]] = null;

        this.label[to[0]][to[1]].setIcon(this.pieces[to[0]][to[1]].getImage());
        this.label[from[0]][from[1]].setIcon(null);
        setLabel();
    }

    // setting the label (sunterresaa)
    private void setLabel() {
        boolean yellowTurn = this.model.isYellowTurn();

        String text = yellowTurn ? "Yellow's Turn" : "Black's Turn";
        turnLabel.setText("turn: " + this.model.getTurn());
        textLabel.setText(text);

        // Calculate yOffset based on the actual board size
        int yOffset = (getHeight() - row * blocksize) / 2;

        // Position label at the top
        textLabel.setBounds(30, yOffset - 70, 100, 50);
        turnLabel.setBounds(30, yOffset - 50, 100, 50);
        labelPanel.add(textLabel);
        labelPanel.add(turnLabel);
    }

    // setting button (lai)
    private void setButtons() {
        JButton saveBtn = new JButton("save");
        JButton exitBtn = new JButton("exit");

        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.createGameFile();
                model.writeFile();
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MenuGUI();
                dispose();
            }
        });

        buttonPanel.removeAll(); // Clear existing buttons
        buttonPanel.add(saveBtn);
        buttonPanel.add(exitBtn);

        // Revalidate and repaint the buttonPanel
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }
    
    // flip the board (nicholas)
    public void flipBoard() {
        layeredPane.removeAll();
        initializeBoard();
        initializePieces();
        layeredPane.revalidate();
        layeredPane.repaint();
        setLabel();
        setButtons();
    }
}
