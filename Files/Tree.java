package Files;

public class Tree extends Sprite {

    private int wood;
    private boolean taken = false;
    private int treeNumber;
    private int choppableBy = 0;
    private String image;

    public Tree(int x, int y, int tnum, String image, int wood) {
        super(x, y);
        treeNumber = tnum;
        this.wood = wood;
        initTree(image);
        this.image = image;
    }

    private void initTree(String image) {


        loadImage(image);
        getImageDimensions();
    }

    public void setTakenTrue() {
        taken = true;
    }

    public boolean isTaken() {
        return taken;
    }

    public int getTreeNumber() {
        return treeNumber;
    }

    public void setTakenFalse() {
        taken = false;
    }

    public int getChoppableBy() {
        return choppableBy;
    }

    public void setChoppableBy(int i) {
        choppableBy = i;
    }

    public boolean getTaken() {
        return taken;
    }

    public int getWood() {
        return wood;
    }

    public String getImageString() {
        return image;
    }
}