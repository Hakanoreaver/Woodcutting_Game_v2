package Files;

/**
 * Created by Hakanoreaver on 27/02/2017.
 */
public class Monster extends Sprite {


    int health; // how much click damage is needed to kill the monster
    int damage; // how much wood the monster will steal per game tick


    public Monster(int x, int y, int health, int damage) {
        super(x, y);
        this.health = health;
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int damagee) {
        health -= damagee;
    }

    public int getDamage() {
        return damage;
    }


}
