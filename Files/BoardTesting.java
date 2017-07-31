package Files;//TODO add all random chances into one function that runs once per game tick


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.concurrent.ThreadLocalRandom;


public class BoardTesting extends JPanel implements ActionListener {


    public static JLabel FPSCount;
    static CardLayout cardLayout = new CardLayout();
    private static ArrayList<Tree> trees;
    private static ArrayList<House> houses;
    private static ArrayList<Grave> graves;
    private static boolean elderActive = false;
    private static ElderTree elderTree;
    private static UIPanel UIpanel;
    private static House house;
    private static Timer timer;
    private static boolean ingame, houseBought = false;
    private static int wood = 100, disease = 0, plagues, upgradeCount = 0, medCost = 500, deathChance = 10,
            mCost = 1000;
    private static int houseNO;
    private static int counter = 0, treeNum = 0;
    private static int eHealth = 400;
    private final int B_WIDTH = 1000, B_HEIGHT = 700, DELAY = 15;
    private ArrayList<Axe> axes;
    private int ingameFlag = 0;


    public BoardTesting() {
        initAxes();
        initBoard();
    }

    public static ArrayList getTrees() {
        return trees;
    }

    public static void addTree(Tree m) {
        trees.add(m);
    }

    public static ArrayList getHouses() {
        return houses;
    }

    public static void initPeople() {


        //  for (int i = 0; i < 500; i++) {
        house.spawnForester(3);
        //    }
        //   for (int i = 0; i < 500; i++) {
        house.spawnWoodcutter("Resources/person.png", 1);
        //   }

        elderTree = (new ElderTree(300, 300, 0));
        elderTree.setVisible(false);

    }

    private static void initTrees() {

        trees = new ArrayList<>();


        for (int i = 0; i <= 50; i++) {

            int x = ThreadLocalRandom.current().nextInt(10, 980);
            int y = ThreadLocalRandom.current().nextInt(10, 600);
            trees.add(new Tree(x, y, treeNum, "Resources/Tree.png", 1));
            treeNum++;
        }
    }

    public static int getCounter() {
        return counter;
    }


//TODO add in collisions to take cut down trees

    public static int getTreeNumber() {
        treeNum++;
        return treeNum;

    }

    public static void setFPS(int FPS) {
        FPSCount.setText("FPS " + Integer.toString(FPS));
    }

    public static void startTimer() {
        timer.start();
    }

    public static void saveGame() {
        File file1 = new File("save/GameStats.txt");
        File file2 = new File("save/WoodCutters.txt");
        File file3 = new File("save/Foresters.txt");
        File file4 = new File("save/Houses.txt");
        File file5 = new File("save/Trees.txt");

        try (BufferedWriter buff = new BufferedWriter(new FileWriter(file1))) {
            String content;
            content = Integer.toString(wood) + "\r\n";
            buff.write(content);
            content = Integer.toString(upgradeCount) + "\r\n";
            buff.write(content);
            content = Integer.toString(plagues) + "\r\n";
            buff.write(content);
            content = Integer.toString(medCost) + "\r\n";
            buff.write(content);
            content = Integer.toString(deathChance) + "\r\n";
            buff.write(content);

        } catch (IOException e) {

            System.out.println("error");
            System.out.println(e);

        }

        try (BufferedWriter woodBuff = new BufferedWriter(new FileWriter(file2))) {
            ArrayList<WoodCutter> array = house.getWoodCutters();
            String content;
            content = Integer.toString(array.size()) + "\r\n";
            woodBuff.write(content);

            for (WoodCutter w : array) {
                content = w.getImageString() + "\r\n";
                woodBuff.write(content);
                content = Integer.toString(w.getDamage()) + "\r\n";
                woodBuff.write(content);
            }
        } catch (IOException e) {

            System.out.println("error");
            System.out.println(e);

        }

        try (BufferedWriter forBuff = new BufferedWriter(new FileWriter(file3))) {
            ArrayList<Forester> array = house.getForesters();
            String content;
            content = Integer.toString(array.size()) + "\r\n";
            forBuff.write(content);

            for (Forester w : array) {
                content = Integer.toString(w.getType()) + "\r\n";
                forBuff.write(content);
            }
        } catch (IOException e) {

            System.out.println("error");
            System.out.println(e);

        }

        try (BufferedWriter houseBuff = new BufferedWriter(new FileWriter(file4))) {
            String content;
            content = Integer.toString(houses.size()) + "\r\n";
            houseBuff.write(content);
            for (House h : houses) {
                content = Integer.toString(h.getX()) + "\r\n";
                houseBuff.write(content);
                content = Integer.toString(h.getY()) + "\r\n";
                houseBuff.write(content);
            }
        } catch (IOException e) {

            System.out.println("error");
            System.out.println(e);

        }

        try (BufferedWriter treeBuff = new BufferedWriter(new FileWriter(file5))) {
            String content;
            content = Integer.toString(trees.size()) + "\r\n";
            treeBuff.write(content);
            for (Tree t : trees) {
                content = Integer.toString(t.getX()) + "\r\n";
                treeBuff.write(content);
                content = Integer.toString(t.getY()) + "\r\n";
                treeBuff.write(content);
                content = Integer.toString(t.getWood()) + "\r\n";
                treeBuff.write(content);
                content = t.getImageString() + "\r\n";
                treeBuff.write(content);
            }
        } catch (IOException e) {

            System.out.println("error");
            System.out.println(e);

        }

    }

    public static void loadGame() throws IOException {

        /*
        TODO set all values to start values
        TODO implement load game feature
        */
        house = new House(120, 120);
        treeNum = 0;

        trees = new ArrayList<>();
        houses = new ArrayList<>();
        graves = new ArrayList<>();
        graves.clear();


        FileReader fr = new FileReader("save/Trees.txt");
        BufferedReader treeRead = new BufferedReader(fr);

        int size = Integer.parseInt(treeRead.readLine());
        System.out.println("Size " + size);

        for (int i = 0; i <= size - 1; i++) {

            int xx = Integer.parseInt(treeRead.readLine());
            System.out.println(xx);
            int yy = Integer.parseInt(treeRead.readLine());
            System.out.println(yy);
            int wood = Integer.parseInt(treeRead.readLine());
            System.out.println(wood);
            String image = treeRead.readLine();

            trees.add(new Tree(xx, yy, treeNum, image, wood));
            treeNum++;
        }

        treeRead.close();
        fr.close();

        FileReader frr = new FileReader("save/Houses.txt");
        BufferedReader houseRead = new BufferedReader(frr);

        size = Integer.parseInt(houseRead.readLine());

        for (int i = 0; i <= size - 1; i++) {
            int xx = Integer.parseInt(houseRead.readLine());
            int yy = Integer.parseInt(houseRead.readLine());

            houses.add(new House(xx, yy));
        }

        frr.close();
        houseRead.close();

        FileReader frrr = new FileReader("save/GameStats.txt");
        BufferedReader statReader = new BufferedReader(frrr);

        wood = Integer.parseInt(statReader.readLine());
        upgradeCount = Integer.parseInt(statReader.readLine());
        plagues = Integer.parseInt(statReader.readLine());
        medCost = Integer.parseInt(statReader.readLine());
        deathChance = Integer.parseInt(statReader.readLine());

        frrr.close();
        statReader.close();

        FileReader frrrr = new FileReader("save/WoodCutters.txt");
        BufferedReader woodReader = new BufferedReader(frrrr);

        size = Integer.parseInt(woodReader.readLine());

        for (int i = 0; i <= size - 1; i++) {
            String image = woodReader.readLine();
            int damage = Integer.parseInt(woodReader.readLine());

            house.spawnWoodcutter(image, damage);
        }

        frrrr.close();
        woodReader.close();

        FileReader frrrrr = new FileReader("save/Foresters.txt");
        BufferedReader forRead = new BufferedReader(frrrrr);

        size = Integer.parseInt(forRead.readLine());

        for (int i = 0; i <= size - 1; i++) {
            int type = Integer.parseInt(forRead.readLine());
            house.spawnForester(type);
        }

        frrrrr.close();
        forRead.close();

        ingame = true;
        timer.start();
        Main.goGame();
    }

    public static void startNewGame() {

        // TODO set all values to start values
        treeNum = 0;
        initTrees();
        house = new House(120, 120);
        houses = new ArrayList<>();
        graves.clear();
        houses.add(house);
        initPeople();
        timer.start();
        wood = 10000;
        upgradeCount = 0;
        deathChance = 10;
        plagues = 0;
        ingame = true;

    }

    public static int getWood() {
        return wood;
    }

    public static void setWood(int woood) {
        wood = woood;
    }

    public static void makeHouseUI(int i) {
        if (i >= houses.size()) i = 0;
        if (i < 0) i = houses.size() - 1;
        UIPanel.closeUI();
        new UIPanel(houses.get(i).getJPanel(), houses.get(i).getX(), houses.get(i).getY());
        houseNO = i;
    }

    public static int getHouseNO() {
        return houseNO;
    }

    public static ElderTree getElderTree() {
        return elderTree;
    }

    private void initAxes() {

        axes = new ArrayList<Axe>();

        axes.add(new Axe("Bronze Hatchet", 1, 0));
        axes.add(new Axe("Iron Hatchet", 2, 300));
        axes.add(new Axe("Steel Hatchet", 4, 900));
        axes.add(new Axe("Mithril Hatchet", 8, 2100));
        axes.add(new Axe("Adamantite Hatchet", 16, 5000));
        axes.add(new Axe("Dragon Hatchet", 32, 10000));
        axes.add(new Axe("Crystal Hatchet", 64, 40000));
        axes.add(new Axe("Elder Hatchet", 128, 1000000));
        axes.add(new Axe("Demonic Hatchet", 256, 10000000));
        axes.add(new Axe("Master Hatchet", 512, 15000000));

    }

    private void initBoard() {


        initButtons();
        ingame = false;
        setFocusable(true);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        this.setLayout(null);
        graves = new ArrayList<Grave>();


        // initTrees();
        house = new House(120, 120);
        houses = new ArrayList<>();
        houses.add(house);
        // initPeople();
        timer = new Timer(DELAY, this);
//        timer.start();


    }

    public void initButtons() {

        JPanel buttonPanel = new JPanel();
        JPanel buttons = new JPanel();
        JPanel buttonsCard = new JPanel();
        buttonsCard.setLayout(cardLayout);

        buttons.setLayout(null);
        buttons.setBounds(1000, 0, 400, 700);
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(1000, 0, 400, 700);

        buttonsCard.add(buttons, "card1");

        JButton clearGraves = new JButton("Clear Graves");
        clearGraves.setBounds(100, 600, 200, 30);
        clearGraves.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Grave grave : graves) {
                    grave.setVisible(false);
                }
            }
        });
        buttonPanel.add(clearGraves);

        FPSCount = new JLabel("0");
        this.add(FPSCount);
        FPSCount.setBounds(950, 10, 150, 30);


        JButton clearGrabess = new JButton("Clear The Graves");
        clearGrabess.setBounds(100, 600, 200, 30);
        clearGrabess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Grave grave : graves) {
                    grave.setVisible(false);
                }
            }
        });
        buttons.add(clearGrabess);


        JLabel mediCost = new JLabel("Cost: " + medCost);
        mediCost.setBounds(220, 70, 200, 30);
        buttons.add(mediCost);

        JButton upgradeMedicine = new JButton("Upgrade Medicine");
        upgradeMedicine.setBounds(10, 70, 200, 30);
        upgradeMedicine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (wood >= medCost) {
                    wood -= medCost;
                    medCost *= 2;
                    deathChance += 5;
                    mediCost.setText("Cost: " + medCost);

                }
            }
        });
        buttons.add(upgradeMedicine);

        JLabel axeCost = new JLabel("Cost: " + axes.get(upgradeCount + 1).getCost());
        axeCost.setBounds(220, 10, 200, 30);
        buttons.add(axeCost);

        JButton btnUpgradeAxe = new JButton("Buy: " + axes.get(upgradeCount + 1).getName());
        btnUpgradeAxe.setBounds(10, 10, 200, 30);
        btnUpgradeAxe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (upgradeCount < (axes.size() - 1)) {
                    if (wood >= axes.get(upgradeCount + 1).getCost()) {
                        wood -= axes.get(upgradeCount + 1).getCost();
                        System.out.println("Upgraded: " + upgradeCount);
                        upgradeCount++;
                        if (upgradeCount == axes.size() - 1) {
                            axeCost.setText("Max Upgrades");
                            btnUpgradeAxe.setText("Max Upgrades");
                        }
                        if (upgradeCount < axes.size() - 1) {
                            btnUpgradeAxe.setText(("Buy: " + axes.get(upgradeCount + 1).getName()));
                            axeCost.setText("Cost: " + axes.get(upgradeCount + 1).getCost());
                        }
                    }
                } else {
                    axeCost.setText("Max Upgrades");
                    btnUpgradeAxe.setText("Max Upgrades");
                }
            }

        });
        buttons.add(btnUpgradeAxe);

        buttonPanel.setLocation(1010, 70);

        JButton btnAddForester = new JButton("Buy Files.Forester");
        btnAddForester.setBounds(10, 70, 150, 30);
        btnAddForester.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (wood >= 30) {
                    wood -= 30;
                    house.spawnForester(1);
                }
            }
        });
        buttonPanel.add(btnAddForester);

        JButton mainMenu = new JButton("Files.Main Menu");
        mainMenu.setBounds(60, 500, 200, 100);
        mainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.goMenu();
                timer.stop();
            }
        });
        buttonPanel.add(mainMenu);
        JButton btnAddForester2 = new JButton("Buy Oak Files.Forester");
        btnAddForester2.setBounds(10, 300, 150, 30);
        btnAddForester2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (wood >= 500) {
                    wood -= 500;
                    house.spawnForester(2);

                }
            }
        });
        buttonPanel.add(btnAddForester2);

        JLabel oakForester = new JLabel("Cost 500");
        oakForester.setBounds(170, 260, 200, 30);
        buttonPanel.add(oakForester);

        JLabel bloodForester = new JLabel("Cost 10000");
        bloodForester.setBounds(170, 320, 200, 30);
        buttonPanel.add(bloodForester);

        JButton btnAddForester3 = new JButton("Buy Blood Files.Forester");
        btnAddForester3.setBounds(10, 340, 150, 30);
        btnAddForester3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (wood >= 10000) {
                    wood -= 10000;
                    house.spawnForester(3);

                }


            }
        });
        buttonPanel.add(btnAddForester3);

        JLabel mForesterCost = new JLabel("Cost: " + mCost);
        mForesterCost.setBounds(200, 190, 200, 30);
        buttonPanel.add(mForesterCost);

        JButton buyMForester = new JButton("Buy Master Files.Forester");
        buyMForester.setBounds(10, 190, 180, 30);
        buyMForester.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (wood >= mCost) {
                    wood -= mCost;
                    house.spawnWoodcutter("Resources/MasterForester.png", 10);
                    mCost = mCost * 130 / 100;
                    mForesterCost.setText("Cost: " + mCost);
                }
            }
        });
        buttonPanel.add(buyMForester);


        JButton btnAddPerson = new JButton("Buy Lumberjack");
        btnAddPerson.setBounds(10, 10, 150, 30);
        btnAddPerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (wood >= 10) {
                    wood -= 10;
                    house.spawnWoodcutter("Resources/Person.png", 1);
                }
            }
        });
        buttonPanel.add(btnAddPerson);
        btnAddPerson.setLocation(1010, 10);

        JLabel personCost = new JLabel("Cost: 10 Wood");
        personCost.setBounds(120, 10, 200, 30);
        buttonPanel.add(personCost);
        personCost.setLocation(1210, 10);


        JLabel foragerCost = new JLabel("Cost: 30 Wood");
        foragerCost.setBounds(120, 70, 200, 30);
        buttonPanel.add(foragerCost);
        foragerCost.setLocation(1210, 70);

        JLabel houseCost = new JLabel("Cost: 200 Wood");
        houseCost.setBounds(120, 150, 200, 30);
        buttonPanel.add(houseCost);
        houseCost.setLocation(1220, 150);

        JButton btnAddHouse = new JButton("Buy Files.House");
        btnAddHouse.setBounds(10, 150, 150, 30);
        btnAddHouse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (wood > 200 && houseBought == false) {
                    wood -= 200;
                    houseBought = true;
                    houseCost.setText("Click To Place");


                }

            }
        });
        buttonPanel.add(btnAddHouse);

        JTabbedPane buttonsPane = new JTabbedPane();
        buttonsPane.setBounds(1000, 0, 350, 690);
        buttonsPane.add("Workers", buttonPanel);
        buttonsPane.add("Upgrades", buttonsCard);


        buttonsPane.setVisible(true);
        buttonPanel.setVisible(true);
        this.add(buttonsPane);

        btnAddPerson.setLocation(10, 10);
        btnAddForester.setLocation(10, 70);
        btnAddHouse.setLocation(10, 130);
        personCost.setLocation(170, 10);
        foragerCost.setLocation(170, 70);
        houseCost.setLocation(170, 130);
        btnAddForester2.setLocation(10, 260);
        btnAddForester3.setLocation(10, 320);


        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                if (houseBought == true) {
                    int ex = me.getX();
                    int why = me.getY();
                    houses.add(new House(ex, why));
                    houseBought = false;
                    houseCost.setText("Cost: 200 wood");
                }

                for (int i = 0; i <= houses.size() - 1; i++) {
                    Rectangle r = houses.get(i).getBounds();
                    if (r.contains(me.getX(), me.getY())) {
                        houseNO = i;
                        makeHouseUI(i);
                    }
                }


            }
        });


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (ingame) {

            drawObjects(g);

        } else {

            drawGameOver(g);

        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void checkCollisions() {

        ArrayList<WoodCutter> ms = house.getWoodCutters();

        for (WoodCutter m : ms) {

            Rectangle r1 = m.getBounds();

            for (House p : houses) {

                Rectangle r3 = p.getBounds();

                if (r1.intersects(r3)) {


                    if (m.getHouseTouch() == false && m.getWithTree() == true) {
                        m.setWithTreeFalse();
                        m.setHouseTouchTrue();
                        wood += axes.get(upgradeCount).getLevel() * m.getDamage() * m.getWood() * p.getEfficiency();
                        if (elderActive == true) {
                            m.elderMove();
                        }
                        if (elderActive == false) m.moveToTree();
                    }

                    if (!m.getHouseTouch() && m.getWithElder()) {
                        m.setWithElderFalse();
                        m.setHouseTouchTrue();
                        wood += axes.get(upgradeCount).getLevel() * m.getDamage() * m.getWood() * p.getEfficiency();


                        elderTree.setHealth(axes.get(upgradeCount).getLevel() * m.getDamage());


                        if (elderTree.getHealth() <= 0 && elderActive) {
                            elderTree.setVisible(false);
                            elderActive = false;


                            ListIterator<Tree> treeit = trees.listIterator();
                            while (treeit.hasNext()) {
                                Tree tree1 = treeit.next();
                                tree1.setVisible(false);

                            }
                            for (WoodCutter w : house.getWoodCutters()) {
                                w.setHouseTouchFalse();
                                w.setMoveSetFalse();
                                w.setWithTreeFalse();
                                w.setWithElderFalse();
                                w.moveToHouse();
                            }

                        }
                        if (elderActive == true) {
                            m.elderMove();
                        }
                        if (elderActive == false) m.moveToTree();
                    }
                }
            }


            Rectangle r4 = elderTree.getBounds();

            if (r1.intersects(r4) && elderActive) {
                m.setWood(axes.get(upgradeCount).getLevel() * m.getDamage());
                m.setWithElderTrue();
                m.moveToHouse();
                m.setHouseTouchFalse();

            }


            for (Tree tree : trees) {

                Rectangle r2 = tree.getBounds();
                if (r1.intersects(r2) && m.getWithTree() == false && tree.isTaken() == true && m.getPersonNo() == tree.getChoppableBy()) {
                    tree.setVisible(false);
                    m.setWood(tree.getWood());
                    m.setWithTreeTrue();
                    m.moveToHouse();
                    m.setHouseTouchFalse();
                }
            }
        }

    }

    private void drawObjects(Graphics g) {

        for (House m : houses) {
            if (m.isVisible()) {
                g.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }
        }

        for (Grave gg : graves) {

            g.drawImage(gg.getImage(), gg.getX(), gg.getY(),
                    this);
        }

        for (Tree m : trees) {
            if (m.isVisible()) {
                g.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }
        }


        if (elderActive) {
            g.drawImage(elderTree.getImage(), elderTree.getX(), elderTree.getY(), this);
        }


        ArrayList<WoodCutter> ms = house.getWoodCutters();

        for (WoodCutter m : ms) {
            if (m.isVisible()) {
                g.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }
        }

        ArrayList<Forester> fs = house.getForesters();

        for (Forester f : fs) {
            if (f.isVisible()) {
                g.drawImage(f.getImage(), f.getX(), f.getY(), this);
            }
        }
        g.setColor(Color.BLACK);
        g.drawString("Wood: " + Integer.toString(wood), 5, 15);

        g.setColor(Color.BLACK);
        g.drawString("Workers: " + Integer.toString(house.getWoodCutters().size() + house.getForesters().size()), 15, 60);


        g.drawString("Plagues: " + Integer.toString(plagues), 15, 200);

    }

    private void drawGameOver(Graphics g) {

        String msg = "WINNER!";
        Font small = new Font("Helvetica", Font.BOLD, 90);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.BLACK);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2);


    }

    private void update() {

        ArrayList<WoodCutter> ms = house.getWoodCutters();

        for (int i = 0; i < ms.size(); i++) {

            WoodCutter m = ms.get(i);
            if (m.getMoveSet() == false) {
                if (elderActive == true) {
                    m.elderMove();

                } else m.moveToTree();

            }

            if (m.isVisible()) {
                m.move();
            } else {
                ms.remove(i);
            }
        }

        ArrayList<Forester> fs = house.getForesters();

        for (int i = 0; i < fs.size(); i++) {

            Forester f = fs.get(i);
            if (f.getOnTask() == false) {
                f.plantTreesMove();
            }

            if (f.isVisible()) {
                f.move();
            } else {
                fs.remove(i);
            }
        }


        for (int i = 0; i < trees.size(); i++) {

            if (trees.get(i).isVisible() == false) {
                trees.remove(i);
                checkDisease(ThreadLocalRandom.current().nextInt(0, 3000));
            }
        }

        for (int i = 0; i < graves.size(); i++) {

            Grave m = graves.get(i);

            if (m.isVisible()) {

            } else {
                graves.remove(i);
            }
        }


        if (elderTree.isVisible() == false) {
            elderActive = false;
        }
        if (elderTree.isVisible()) {
            elderActive = true;
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        FPSCounter.StopAndPost();
        inGame();
        update();
        checkCollisions();
        repaint();
        updateCounter();
        FPSCounter.startCounter();

        if (ingameFlag == 1) {
            this.setVisible(true);
            this.setFocusable(true);
            timer.restart();
        }


    }

    private void updateCounter() {
        counter++;
        if (counter == 100) counter = 50;
    }

    private void inGame() {

        if (wood > 30000000) ingame = false;

        if (!ingame) {
            timer.stop();
        }
    }

    private void checkDisease(int ii) {

        switch (ii) {

            case 99:

                disease = 0;
                ArrayList<WoodCutter> ms = house.getWoodCutters();
                ArrayList<Forester> mf = house.getForesters();


                for (int i = 0; i < house.getWoodCutters().size(); i++) {

                    int chance = ThreadLocalRandom.current().nextInt(0, deathChance);

                    if (chance == 9) {
                        for (Tree t : trees) {

                            if (t.getX() == house.getWoodCutters().get(i).getMoveX() && t.getY() == house.getWoodCutters().get(i).getMoveY()) {
                                t.setTakenFalse();
                            }
                        }
                        graves.add(new Grave(house.getWoodCutters().get(i).getX(), house.getWoodCutters().get(i).getY()));
                        house.getWoodCutters().remove(i);


                    }

                }

                for (int i = 0; i < house.getForesters().size(); i++) {

                    int chance = ThreadLocalRandom.current().nextInt(0, deathChance);

                    if (chance == 9) {
                        graves.add(new Grave(house.getForesters().get(i).getX(), house.getForesters().get(i).getY()));
                        house.getForesters().remove(i);

                    }

                }

                plagues++;

                break;

            case 2:

                if (!elderActive) {
                    eHealth = ((eHealth * 12) / 10) + 1000;
                    System.out.println(eHealth);
                    elderTree = null;
                    checkDisease(ThreadLocalRandom.current().nextInt(0, 3000));
                    elderTree = new ElderTree(ThreadLocalRandom.current().nextInt(50, 750), ThreadLocalRandom.current().nextInt(50, 500), eHealth);
                    elderActive = true;
                }


        }


    }

    public Axe getAxe() {
        return axes.get(upgradeCount);
    }
}



