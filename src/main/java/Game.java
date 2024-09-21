import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    public static void main(String[] args) throws IOException, IllegalAccessException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int startPoint;

        System.out.printf("시작할 사다리의 위치를 입력하시오 : ");
        startPoint = Integer.parseInt(br.readLine());

        Ladder ladder = new Ladder(6, 3);
        ladder.drawLadder(1,1,2);
        ladder.drawLadder(2,2,3);
        ladder.drawLadder(3,1,2);
        ladder.drawLadder(4,2,3);
        ladder.drawLadder(5,1,2);
        ladder.drawLadder(6,2,3);
        ladder.run(startPoint);

        br.close();
    }

}
