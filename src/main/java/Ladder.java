import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ladder {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final int[][] rows;


    public Ladder(int row, int numberOfPerson) {
        rows = new int[row][numberOfPerson];
    }

    private void drawLadder() throws IOException, IllegalAccessException {                          //사다리 상세 완성
        int row, left, right;
        System.out.println("생성할 사다리의 선(line)을 " + (rows[0].length-1)*3 + "번 입력하세요 > ");

        for(int i = 1; i <= (rows[0].length-1)*3; i++){
            System.out.printf("선(line) [%d]의 높이를 입력하시오 : ", i);
            row = Integer.parseInt(br.readLine());
            System.out.printf("선(line) [%d]의 왼쪽 좌표를 입력하시오 : ", i);
            left = Integer.parseInt(br.readLine());
            System.out.printf("선(line) [%d]의 오른쪽 좌표를 입력하시오 : ", i);
            right = Integer.parseInt(br.readLine());

            if(checkLine(inputLine(row, left, right))){
                System.out.println("선(line) [" + i + "]이 성공적으로 생성되었습니다.");
                System.out.println();
            }
        }
    }
    private void drawLine(Position position) {                                                      //사다리 각 라인의 생성
        rows[position.getRow()][position.getLeft()] = 1;
        rows[position.getRow()][position.getRight()] = -1;

    }

    private boolean checkLine(Position position){                                                   //각 position의 유효성 검사

        if(rows[position.getRow()][position.getLeft()] != 0 || rows[position.getRow()][position.getRight()] != 0){
            System.out.println("해당 위치에 선(Line)을 그릴수 없습니다: 미리 선언된 선 존재 함");
            return false;
        }

        drawLine(position);
        return true;
    }

    private Position inputLine(int row, int left, int right) throws IllegalAccessException {         //입력된 선(Line)의 길이가 1인지 확인
        if((right-left) != 1){
            throw new IllegalAccessException("입력된 선(Line)의 길이가 1 보다 큽니다 : 선(Line)의 생성이 실패했습니다.");
        }
        if(left < 0 || right > rows[0].length+1){
            throw new IllegalAccessException("입력된 선(Line)의 좌표가 범위를 벗어납니다 : 선(Line)의 생성이 실패했습니다.");
        }
        return new Position(row-1, left-1, right-1);
    }

    private int start(int startPoint){
        int temp = startPoint - 1;

        for(int i = 0; i < rows.length; i++){
            temp += rows[i][temp];
        }

        return temp;
    }

    public void run(int startPoint) throws IOException, IllegalAccessException {                    //사다리 선택 > 도착 번호
        drawLadder();
        int endPont = start(startPoint);
        System.out.println();
        System.out.println("사다리 타기의 결과 " + startPoint + "의 종료 위치는 " + (endPont+1) + "입니다.");
    }
}


