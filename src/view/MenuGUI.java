package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.*;

public class MenuGUI extends JFrame {

    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JButton startButton;
    private JButton loadButton;
    private JButton quitButton;
    private JButton instructionsButton;

    // constructor for the menu (lai)
    public MenuGUI() {
        setTitle("Talabia Chess Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240));

        JLabel titleLabel = new JLabel("Talabia Chess", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(51, 102, 153));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        buttonPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));

        startButton = createStyledButton("Start", new Color(34, 139, 34));
        loadButton = createStyledButton("Load", new Color(255, 165, 0));
        instructionsButton = createStyledButton("Instructions", new Color(70, 130, 180));
        quitButton = createStyledButton("Quit", new Color(178, 34, 34));

        buttonPanel.add(startButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(instructionsButton);
        buttonPanel.add(quitButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.startNewGame();
                dispose();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.loadGame();
                dispose();
            }
        });

        instructionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = "Instructions:\nTo begin a new game, click \"Start\" to start a new game. \nClick \"Load\" to continue the saved game. \nClick \"Quit\" to exit the game."; 
                JOptionPane.showMessageDialog(MenuGUI.this, message);   
            }
        });

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(color);
        button.setForeground(Color.white);
        button.setPreferredSize(new Dimension(120, 40));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MenuGUI();
            }
        });
    }
}
