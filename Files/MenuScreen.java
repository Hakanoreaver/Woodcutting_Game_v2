package Files;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Hakanoreaver on 7/03/2017.
 */
public class MenuScreen extends JPanel {

    private final int B_WIDTH = 1000, B_HEIGHT = 700;
    Image image;

    public MenuScreen() {
        initButtons();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(image, 0, 0, 1350, 700, this);
    }

    private void initButtons() {
        setBackground(Color.BLUE);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        this.setLayout(null);


        ImageIcon ii = new ImageIcon("Resources/BackgroundImage.png");
        image = ii.getImage();

        repaint();

        JButton button = new JButton("New Game");
        button.setBounds(575, 140, 200, 60);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BoardTesting.startNewGame();
                Main.goGame();
            }
        });
        add(button);


        JButton button2 = new JButton("Load Game");
        button2.setBounds(575, 260, 200, 60);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BoardTesting.loadGame();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        add(button2);

        JButton button3 = new JButton("Save Game");
        button3.setBounds(575, 380, 200, 60);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BoardTesting.saveGame();
            }
        });
        add(button3);

        JButton button4 = new JButton("Resume");
        button4.setBounds(575, 500, 200, 60);
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.goGame();
                BoardTesting.startTimer();
            }
        });
        add(button4);


    }


}
