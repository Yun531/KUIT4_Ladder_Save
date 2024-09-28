package ladder.creator;

import java.util.Random;

public class RandomNumber {
    public static int from(int range) {
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        return rand.nextInt(range);
    }

}
