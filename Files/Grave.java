package Files;

/**
 * Created by Hakanoreaver on 23/01/2017.
 */
public class Grave extends Sprite {

    public Grave(int x, int y) {
        super(x, y);
        initGrave();
    }

    private void initGrave() {
        loadImage("Resources/Gravestone.png");

    }
}
