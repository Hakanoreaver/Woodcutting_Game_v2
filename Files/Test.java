package Files;

public class Test {

    public static void main(String[] args) {


        double t = Double.parseDouble(args[0]);
        double r = Double.parseDouble(args[0]);

        System.out.println("r = " + r + ", t = " + t);

        double c = 2 * Math.PI * r;
        double A = Math.PI * r * r;

        double x = r * Math.cos(t);
        double y = r * Math.sin(t);

        System.out.println("c = " + c);
        System.out.println("A = " + A);
        System.out.println("x = " + x + ", " + "y = " + y);
    }
}