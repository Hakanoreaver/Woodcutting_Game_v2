package Files;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class House extends Sprite {

    int personNo = 1;

    private ArrayList<WoodCutter> woodcutters;
    private ArrayList<Forester> foresters;
    private JPanel panel;
    private int efficiency = 1;
    private JButton button;
    private JLabel sawingEff;
    private int levelCost = 100;

    public House(int x, int y) {
        super(x, y);
        vis = true;

        initHouse();
        initJPanel();
    }

    private void initHouse() {
        woodcutters = new ArrayList<>();
        foresters = new ArrayList<>();
        loadImage("Resources/House.png");
        getImageDimensions();
    }


    public ArrayList<WoodCutter> getWoodCutters() {
        return woodcutters;
    }

    public void spawnWoodcutter(String image, int cutAmount) {

        woodcutters.add(new WoodCutter(x + width + 5, y + height + 5, personNo, image, cutAmount));
        personNo++;
    }

    public void spawnForester(int type) {

        foresters.add(new Forester(x + width + 5, y + height + 5, type, "Resources/Forester.png"));
    }

    public ArrayList<Forester> getForesters() {
        return foresters;
    }


    public void initJPanel() {
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, 200));
        panel.setVisible(true);
        sawingEff = new JLabel("Level: 0");
        sawingEff.setLocation(10, 100);
        panel.add(sawingEff);
        JLabel lvlCost = new JLabel("Cost: " + levelCost);
        lvlCost.setLocation(50, 105);
        panel.add(lvlCost);
        button = new JButton("Level Files.House");
        button.setLocation(50, 10);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (BoardTesting.getWood() >= levelCost) {
                    int wood = BoardTesting.getWood() - levelCost;
                    BoardTesting.setWood(wood);
                    levelCost *= 2;
                    efficiency++;
                    System.out.println("sawing upgraded" + this + "   " + efficiency);
                    sawingEff.setText("Level: " + (efficiency - 1));
                    lvlCost.setText("Cost: " + levelCost);

                }
            }
        });
        panel.add(button);

        JButton previous = new JButton("Previous");
        previous.setBounds(10, 150, 100, 50);
        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = BoardTesting.getHouseNO() - 1;
                System.out.println("previous");
                BoardTesting.makeHouseUI(i);
            }
        });
        panel.add(previous);

        JButton next = new JButton("Next");
        next.setBounds(110, 150, 100, 50);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = BoardTesting.getHouseNO() + 1;
                System.out.println("next");
                BoardTesting.makeHouseUI(i);
            }
        });
        panel.add(next);
    }

    public JPanel getJPanel() {
        updateJPanel();

        return panel;
    }

    public void updateJPanel() {
        sawingEff.setText("Level : " + (efficiency - 1));
    }

    public int getEfficiency() {
        return efficiency;
    }





}
