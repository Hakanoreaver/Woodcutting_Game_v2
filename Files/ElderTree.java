package Files;

/**
 * Created by Hakanoreaver on 13/03/2017.
 */
public class ElderTree extends Sprite {


    private boolean healthy = true;
    private int health;

    public ElderTree(int x, int y, int health) {
        super(x, y);
        this.health = health;

        setup();
    }

    private void setup() {
        loadImage("Resources/ElderTree.png");
        getImageDimensions();
    }

    public int getHealth() {
        return health;
    }


    public void setHealth(int i) {
        health -= i;
        System.out.println("health " + health);
        if (health <= 0) healthy = false;
    }

    public boolean getHealthy() {
        return healthy;
    }

}
