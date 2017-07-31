package Files;

import java.util.ArrayList;

/**
 * Created by Hakanoreaver on 27/02/2017.
 */
public class WoodCutter extends Sprite {

    private final int BOARD_WIDTH = 1500;
    private final int MISSILE_SPEED = 8;
    int personNo;
    ArrayList<String> moving;
    private double lowDis = 0;
    private int moveX;
    private int moveY;
    private int moveXOld;
    private int moveYOld;
    private boolean moveSet = false;
    private boolean withTree = false;
    private boolean withElder = false;
    private boolean houseTouch = false;
    private int movingInt = 0;
    private int stationary = 0;
    private int treeNumber;
    private int damage;
    private int wood;
    private String image;

    public WoodCutter(int x, int y, int z, String image, int cutAmount) {
        super(x, y);
        initPerson(image);
        personNo = z;
        damage = cutAmount;
        this.image = image;


    }

    private void initPerson(String image) {

        loadImage(image);
        getImageDimensions();

    }


    public void moveToTree() {


        ArrayList<Tree> trees = BoardTesting.getTrees();

        moveXOld = moveX;
        moveYOld = moveY;


        for (Tree m : trees) {


            if (m.isTaken() == false && m.isVisible()) {

                int tx = m.getX();
                int ty = m.getY();
                int px = this.x;
                int py = this.y;
                int tnum = m.getTreeNumber();


                double dis = Math.sqrt(Math.pow(px - tx, 2) + Math.pow(py - ty, 2));

                if (lowDis > dis || lowDis == 0) {

                    lowDis = dis;
                    moveX = tx;
                    moveY = ty;
                    treeNumber = tnum;


                }
            }
        }
        lowDis = 0;


        for (Tree m : trees) {


            if (treeNumber == m.getTreeNumber()) {
                m.setTakenTrue();
                m.setChoppableBy(personNo);
            }
        }


        moveSet = !(moveXOld == moveX && moveYOld == moveY);


    }

    public void elderMove() {

        ElderTree elder = BoardTesting.getElderTree();

        moveXOld = moveX;
        moveYOld = moveY;


        if (elder.getHealthy() == true) {


            moveX = elder.getX();
            moveY = elder.getY();
            withElder = true;
            moveSet = true;

        } else moveToTree();


        if (moveXOld == moveX && moveYOld == moveY) {
            moveSet = false;
        }


    }

    public void moveToHouse() {

        ArrayList<House> houses = BoardTesting.getHouses();

        for (House m : houses) {
            int tx = m.getX();
            int ty = m.getY();
            int px = this.x;
            int py = this.y;

            double dis = Math.sqrt(Math.pow(px - tx, 2) + Math.pow(py - ty, 2));

            if (lowDis > dis || lowDis == 0) {

                lowDis = dis;
                moveX = tx;
                moveY = ty;

            }
        }
        lowDis = 0;
    }

    public void move() {


        if (moveX > x) {
            x += MISSILE_SPEED;

        }


        if (moveX < x) {
            x -= MISSILE_SPEED;
        }


        if (moveY < y) {
            y -= MISSILE_SPEED;
        }


        if (moveY > y) {
            y += MISSILE_SPEED;
        }

        if (x > BOARD_WIDTH)
            vis = false;

        if (moveX == x & moveY == y) {
            moveToTree();
        }


    }

    public void setMoveSetFalse() {
        moveSet = false;
    }

    public void setWithTreeTrue() {
        withTree = true;
    }

    public void setWithTreeFalse() {
        withTree = false;
    }

    public boolean getMoveSet() {
        return moveSet;
    }

    public boolean getWithTree() {
        return withTree;
    }

    public void setHouseTouchTrue() {
        houseTouch = true;
    }

    public void setHouseTouchFalse() {
        houseTouch = false;
    }

    public boolean getHouseTouch() {
        return houseTouch;
    }

    public int getPersonNo() {
        return personNo;
    }

    public int getMoveX() {
        return moveX;
    }

    public int getMoveY() {
        return moveY;
    }

    public int getDamage() {
        return damage;
    }

    public int getWood() {
        return wood;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    public String getImageString() {
        return image;
    }

    public boolean getWithElder() {
        return withElder;
    }

    public void setWithElderFalse() {
        withElder = false;
    }

    public void setWithElderTrue() {
        withElder = true;
    }
}