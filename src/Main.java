import java.awt.*;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(Color.black);
        frame.setMinimumSize(new Dimension(1000, 1000));
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());

        Board board = new Board();
        frame.add(board);
        
        frame.setVisible(true);

    }
    
}
