package auth.cableTv.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;

public class Gui extends JFrame{

    private final JPanel categoryPanel = new JPanel();
    private KeyAdapter keyAdapter1;
    private KeyAdapter keyAdapter2;

    public void newPanel() {
        getContentPane().removeAll();
        repaint();
        setTitle("CableTv");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setFocusable(true);
        setLayout(new GridBagLayout());
        JLabel label = new JLabel("Καλως ήρθατε");
        add(label);
        setVisible(true);
        JButton b1 = new JButton(String.valueOf( 1));
        add(b1);
        setVisible(true);

    }
}
