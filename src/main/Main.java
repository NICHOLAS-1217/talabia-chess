package main;

import controller.ChessController;
import model.Game;
import view.Board;
import view.MenuGUI;

public class Main {
    // main (ernest) 
    Board view;
    ChessController controller;

    public static void main(String[] args) {
        new MenuGUI();
    }

    public static void startNewGame() {
        Game model = new Game();
        Board view = new Board();
        ChessController controller = new ChessController(view, model);

        view.setController(controller);
        view.setModel(model);

        model.setController(controller);
        model.setView(view);

        view.initialize();
    }

    public static void loadGame() {
        Game model = new Game();
        Board view = new Board();
        ChessController controller = new ChessController(view, model);

        view.setController(controller);
        view.setModel(model);

        model.setController(controller);
        model.setView(view);
        model.loadFile();

        view.initialize();
    }
}
