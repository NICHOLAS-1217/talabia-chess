package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import main.Main;

// end screen (sunterresaa)
public class EndScreen extends JFrame {

    public EndScreen(String winner) {
        setTitle("Talabia Chess Menu");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel winnerLabel = new JLabel(winner + " wins");
        winnerLabel.setBounds(150, JLabel.CENTER, 150, 150);
        winnerLabel.setHorizontalAlignment(JLabel.CENTER);
        winnerLabel.setPreferredSize(new Dimension(200, 200));
        winnerLabel.setFont(new Font("Comic Sans", Font.BOLD, 20));
        add(winnerLabel);

        JButton exitBtn = new JButton("Back to main menu");
        exitBtn.setBounds(150, 400, 150, 40);
        add(exitBtn);

        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.main(null);
                dispose();
            }
        });

        setLayout(null);
        setVisible(true);
    }
}
