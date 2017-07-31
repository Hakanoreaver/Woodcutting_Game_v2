package Files; /**
 * Created by Hakanoreaver on 13/03/2017.
 */

import javax.swing.*;
import java.awt.*;


public class UIPanel {


    private static JFrame frame;
    private static boolean created = false;
    GridLayout layout = new GridLayout();

    public UIPanel(JPanel panel, int x, int y) {

        frame = new JFrame();
        created = true;
        if (x < 800) x += 50;
        else x -= 200;
        if (y < 500) y += 50;
        else y -= 200;
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setBounds(x, y, 200, 200);
        frame.setVisible(true);
        frame.setLayout(layout);
        frame.add(panel);
        frame.pack();

    }

    public static void closeUI() {
        if (created)
            frame.dispose();
        System.out.println("closed");
    }
}
