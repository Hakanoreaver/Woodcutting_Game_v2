package Files;

import java.util.concurrent.ThreadLocalRandom;



public class Forester extends Sprite {

    private final int moveSpeed = 8;
    private int moveX;
    private int moveY;
    private boolean onTask = false;
    private int type;


    public Forester(int x, int y, int type, String image) {
        super(x, y);
        initForester(image);
        this.type = type;


    }

    private void initForester(String image) {
        loadImage(image);
        getImageDimensions();
    }


    public void move() {


        if (moveX > x) {
            x += moveSpeed;
            if (x >= moveX) moveX = x;

        }


        if (moveX < x) {
            x -= moveSpeed;
            if (x <= moveX) moveX = x;
        }



        if (moveY < y) {
            y -= moveSpeed;
            if (y <= moveX) moveY = y;
        }


        if (moveY > y) {
            y += moveSpeed;
            if (y >= moveX) moveY = y;
        }


        if (moveX == x && moveY == y) {
            plantTree();
            onTask = false;
        }


    }
    public void plantTreesMove() {


        moveX = ThreadLocalRandom.current().nextInt(10, 980);
        moveY = ThreadLocalRandom.current().nextInt(10, 600);


        onTask = true;

    }

    public void plantTree() {
        Tree tree;
        if (type == 1) tree = new Tree(x, y, BoardTesting.getTreeNumber(), "Resources/Tree.png", 1);
        else if (type == 2) tree = new Tree(x, y, BoardTesting.getTreeNumber(), "Resources/Oak.png", 5);
        else if (type == 3) tree = new Tree(x, y, BoardTesting.getTreeNumber(), "Resources/BloodTree.png", 10);
        else tree = new Tree(x, y, BoardTesting.getTreeNumber(), "Resources/Tree.png", 1);
        BoardTesting.addTree(tree);
    }

    public boolean getOnTask() {
        return onTask;
    }

    public int getType() {
        return type;
    }
}