package Files;

/**
 * Created by Hakanoreaver on 27/02/2017.
 */
public class SmallBeaver extends Monster {


    public SmallBeaver(int x, int y, int health, int damage) {
        super(x, y, health, damage);
        initMonster();
    }

    private void initMonster() {
        loadImage("Resources/SmallBeaver.png");
        getImageDimensions();
    }


}
