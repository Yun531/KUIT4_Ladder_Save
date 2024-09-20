import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    public static void main(String[] args) throws IOException, IllegalAccessException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int row, numberOfPerson, startPoint;

        System.out.printf("사다리의 개수를 입력하시오 : ");
        numberOfPerson = Integer.parseInt(br.readLine());
        System.out.printf("사다리의 높이를 입력하시오(6층 이상) : ");
        row = Integer.parseInt(br.readLine());
        System.out.printf("시작할 사다리의 위치를 입력하시오 : ");
        startPoint = Integer.parseInt(br.readLine());

        Ladder ladder = new Ladder(row, numberOfPerson);
        ladder.run(startPoint);

        br.close();
    }

}
