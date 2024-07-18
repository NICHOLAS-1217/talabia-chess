package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import controller.ChessController;
import view.Board;
import view.EndScreen;

public class Game {
    private Board view;

    private boolean yellowTurn = true; // yellow goes first
    public char[][] board; // 2d array of the board
    private int turn = 1; // turn counter
    private int conversionCounter = 0; // counter for piece conversion

    // initializes the default board (nicholas)
    public Game() {
        board = new char[][] {
                { 'p', 'h', 't', 's', 't', 'h', 'p' },
                { 'a', 'a', 'a', 'a', 'a', 'a', 'a' },
                { ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
                { ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
                { 'A', 'A', 'A', 'A', 'A', 'A', 'A' },
                { 'P', 'H', 'T', 'S', 'T', 'H', 'P' },
        };
    }

    public void setController(ChessController controller) {
    }

    public void setView(Board view) {
        this.view = view;
    }

    // switch turn (nicholas)
    public void switchTurn() {
        yellowTurn = !yellowTurn;
    }

    // check if it is yellow's turn (nicholas)
    public boolean isYellowTurn() {
        return yellowTurn;
    }

    // Handles the logic to move a piece from one position to another (nicholas)
    public void checkPiece(int[] from, int[] to) {
        char selected = this.board[from[0]][from[1]]; // get the selected piece
        boolean isYellow = Character.isUpperCase(selected) ? true : false; // check if the selected piece is yellow
        char selectedDowncased = Character.toLowerCase(selected); // convert to lowercase for piece identification

        // Check if it's the correct player's turn
        if (yellowTurn != isYellow) {
            System.out.println("wrong colour piece");
            Board board = new Board();
            board.showErrorDialog("Invalid move. Please try again.");
            return;
        }

        boolean isValid;

        // Check the type of the selected piece and validate the move (nicholas)
        switch (selectedDowncased) {
            case 'a':
                isValid = ((PointPiece) this.view.pieces[from[0]][from[1]]).checkMove(from, to, isYellow, board);
                break;

            case 'p':
                isValid = ((PlusPiece) this.view.pieces[from[0]][from[1]]).checkMove(from, to, isYellow, board);
                break;

            case 'h':
                isValid = ((HourglassPiece) this.view.pieces[from[0]][from[1]]).checkMove(from, to, isYellow, board);
                break;

            case 't':
                isValid = ((TimePiece) this.view.pieces[from[0]][from[1]]).checkMove(from, to, isYellow, board);
                break;

            case 's':
                isValid = ((SunPiece) this.view.pieces[from[0]][from[1]]).checkMove(from, to, isYellow, board);
                break;

            case 'r':
                isValid = ((PointPiece) this.view.pieces[from[0]][from[1]]).checkMove(from, to, isYellow, board);
                break;

            default:
                isValid = false;
                break;
        }
        System.out.println(isValid);

        // Check for invalid moves or clashes with pieces of the same color (sunterresaa)
        if (!isValid || !checkNoSameColourClash(from, to)) {
            Board board = new Board();
            board.showErrorDialog("Invalid move. Please try again.");
            return;
        }

        // Switch turns and update the game state (fulin)
        switchTurn();
        if (yellowTurn) {
            turn += 1;
        }
        movePiece(from, to, selected);
        flip2dBoard();

        // Handle piece conversions after every two turns (fulin)
        if (turn >= 3 && turn % 2 != 0) {
            if (conversionCounter % 2 == 0) {
                // Convert time piece to plus piece and vice versa
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 7; j++) {
                        if (board[i][j] == 't') {
                            board[i][j] = 'p';
                        } else if (board[i][j] == 'T') {
                            board[i][j] = 'P';
                        } else if (board[i][j] == 'p') {
                            board[i][j] = 't';
                        } else if (board[i][j] == 'P') {
                            board[i][j] = 'T';
                        }
                    }
                }
                conversionCounter++; // Increment the counter after conversion
            } else {
                // Decrement the counter without converting (to ensure conversion every two
                // turns)
                conversionCounter--;
            }
        }
        // print the updated game board
        for (char[] row : board) {
            for (char col : row) {
                System.out.print(col);
            }
            System.out.println();
        }

        // Update the board view and flip it for the opponent's view
        this.view.updateBoard(from, to, selected);
        this.view.flipBoard();
    }

    // Checks if there is no clash with pieces of the same color (nicholas)
    private boolean checkNoSameColourClash(int[] from, int[] to) {
        if (this.board[to[0]][to[1]] != ' ') {
            if (((Piece) this.view.pieces[from[0]][from[1]]).isYellow() == ((Piece) this.view.pieces[to[0]][to[1]])
                    .isYellow()) {
                return false;
            }
        }
        return true;
    }

    // Moves the piece from one position to another and handles piece flipping (nicholas)
    private void movePiece(int[] from, int[] to, char selected) {

        // change flipped piece's character
        if (this.board[from[0]][from[1]] == 'a') {
            if (((PointPiece) this.view.pieces[from[0]][from[1]]).getIsFlipped()) {
                selected = 'r';
            }
        } else if (this.board[from[0]][from[1]] == 'A') {
            if (((PointPiece) this.view.pieces[from[0]][from[1]]).getIsFlipped()) {
                selected = 'R';
            }
        }

        char captured = this.board[to[0]][to[1]];
        this.board[from[0]][from[1]] = ' ';
        this.board[to[0]][to[1]] = selected;

        checkWin(captured);
    }

    // Returns the current turn as a string 
    public String getTurn() {
        return Integer.toString(turn);
    }

    // Flips the 2D array board, used for changing perspective (nicholas)
    private void flip2dBoard() {
        // reverse chessboard row
        for (int i = 0; i < 6 / 2; i++) {
            char[] temp = board[i];
            board[i] = board[board.length - 1 - i];
            board[board.length - 1 - i] = temp;
        }

        // reverse all pieces of each row
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < board[i].length / 2; j++) {
                char temp = board[i][j];
                board[i][j] = board[i][board[i].length - 1 - j];
                board[i][board[i].length - 1 - j] = temp;
            }
        }
    }

    // if s captured, yellow wins, if S captured, black wins (lai)
    private void checkWin(char captured) {
        if (captured != 's' && captured != 'S') {
            return;
        }

        if (captured == 's') {
            decideWinner("Yellow");
        } else {
            decideWinner("Black");
        }
    }

    // Displays the end screen and deletes the game file (lai)
    private void decideWinner(String winner) {
        new EndScreen(winner);

        this.view.dispose();
        deleteGameFile();
    }

    // Creates a new game file (sunterresaa)
    public void createGameFile() {
        try {
            // Create the directory if it doesn't exist
            File dataDirectory = new File("data");
            if (!dataDirectory.exists()) {
                if (dataDirectory.mkdirs()) {
                    System.out.println("Data directory created: " + dataDirectory.getPath());
                } else {
                    System.out.println("Failed to create data directory.");
                }
            }

            File gameFile = new File("data/myGame.txt");
            if (gameFile.createNewFile()) {
                System.out.println("File created: " + gameFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Deletes the game file (sunterresaa)
    public void deleteGameFile() {
        File gameFile = new File("data/myGame.txt");
        if (gameFile.exists()) {
            if (gameFile.delete()) {
                System.out.println("Deleted the file: " + gameFile.getName());
            } else {
                System.out.println("Failed to delete the file.");
            }
        } else {
            System.out.println("This file doesn't exist");
        }
    }
 
    // Writes the game state to the game file (sunterresaa)
    public void writeFile() {
        try {
            FileWriter writer = new FileWriter("data/myGame.txt");

            for (char[] row : board) {
                for (char col : row) {
                    writer.write(col);
                }
                writer.write("\n");
            }

            writer.write(Boolean.toString(yellowTurn) + "\n");
            writer.write(Integer.toString(turn) + "\n");

            writer.write("end of line");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Loads the game state from the game file (sunterresaa)
    public void loadFile() {
        try {
            String line;
            int lineNumber = 0;
            int nextIteration = 6;
            Path path = Paths.get("data/myGame.txt");

            for (; lineNumber < nextIteration; lineNumber++) {
                line = Files.readAllLines(path).get(lineNumber);
                for (int i = 0; i < line.length(); i++) {
                    board[lineNumber][i] = line.charAt(i);
                }
            }

            line = Files.readAllLines(path).get(lineNumber);
            yellowTurn = Boolean.parseBoolean(line);

            lineNumber++;
            line = Files.readAllLines(path).get(lineNumber);
            turn = Integer.parseInt(line);

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(this.board[i][j]);
            }
            System.out.println();
        }
    }
}
