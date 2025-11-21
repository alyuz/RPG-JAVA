import java.util.Random;

public class Dado {
    public static int rolar(int lados) {
        return new Random().nextInt(lados) + 1;
    }
}
