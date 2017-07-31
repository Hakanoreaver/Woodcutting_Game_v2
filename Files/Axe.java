package Files;

public class Axe {

    private String axeName;
    private int axeLevel;
    private int cost;

    public Axe(String name, int level, int cost) {
        axeName = name;
        axeLevel = level;
        this.cost = cost;
    }

    public String getName() {
        return axeName;
    }

    public int getLevel() {
        return axeLevel;
    }

    public int getCost() {
        return cost;
    }
}
