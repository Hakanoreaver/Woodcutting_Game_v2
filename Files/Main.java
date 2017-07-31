package Files;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    static CardLayout cardLayout = new CardLayout();
    static JPanel contentPane;

    public Main() {
        initUI();
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main ex = new Main();
                ex.setVisible(true);
            }
        });
    }

    public static void goMenu() {
        cardLayout.show(contentPane, "menu");
    }

    public static void goGame() {
        cardLayout.show(contentPane, "game");
    }

    public void initUI() {


        contentPane = new JPanel();
        contentPane.setLayout(cardLayout);


        BoardTesting board = new BoardTesting();
        contentPane.add(board, "game");
        MenuScreen menuScreen = new MenuScreen();
        contentPane.add(menuScreen, "menu");

        add(contentPane);

        contentPane.setPreferredSize(new Dimension(1350, 700));


        setResizable(false);

        setBackground(Color.BLACK);
        pack();
        setTitle("Woodcutting Game");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        CardLayout cardLayout = (CardLayout) contentPane.getLayout();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        cardLayout.show(contentPane, "menu");
    }
}