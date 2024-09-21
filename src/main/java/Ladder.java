public class Ladder {
    private final int[][] rows;


    public Ladder(int row, int numberOfPerson) throws IllegalAccessException {
        if(row < 0 || numberOfPerson < 1){
            throw new IllegalAccessException("입력된 사다리의 설정 값이 잘못되었습니다.");
        }
        rows = new int[row][numberOfPerson];
    }

    public boolean drawLadder(int row, int leftPoint, int rightPoint) throws IllegalAccessException {                          //사다리 상세 완성
        return checkLine(inputLine(row, leftPoint, rightPoint));
    }
/*
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
*/
    private Position inputLine(int row, int left, int right) throws IllegalAccessException {         //입력된 선(Line)의 길이가 1인지 확인
        if(0 >= row || rows.length < row){
            throw new IllegalAccessException("입력된 선(Line)의 높이가 범위를 벗어납니다 : 선(Line)의 생성이 실패했습니다.");
        }
        if((right-left) != 1){
            throw new IllegalAccessException("입력된 선(Line)의 길이가 1 보다 큽니다 : 선(Line)의 생성이 실패했습니다.");
        }
        if(left < 0 || right > rows[0].length+1){
            throw new IllegalAccessException("입력된 선(Line)의 좌표가 범위를 벗어납니다 : 선(Line)의 생성이 실패했습니다.");
        }
        return new Position(row-1, left-1, right-1);                                   //해당 위치에서 입력값과 배열 index 사이의 차이를 조정
    }

    private boolean checkLine(Position position){                                                     //각 position의 유효성 검사 + 사다리에 입력

        if(rows[position.getRow()][position.getLeft()] != 0 || rows[position.getRow()][position.getRight()] != 0){
            System.out.println("해당 위치에 선(Line)을 그릴수 없습니다: 미리 선언된 선 존재 함");
            return false;
        }

        drawLine(position);
        return true;
    }

    private void drawLine(Position position) {                                                      //사다리 각 라인의 생성
        rows[position.getRow()][position.getLeft()] = 1;
        rows[position.getRow()][position.getRight()] = -1;

    }

    private int findEndPoint(int startPoint){
        int temp = startPoint - 1;

        for(int i = 0; i < rows.length; i++){
            temp += rows[i][temp];
        }

        return temp;
    }
    private boolean validateStarPoint(int startPoint){
        if(rows[0].length < startPoint || 1 > startPoint){
            return false;
        }
        return true;
    }

    public void run(int startPoint) throws IllegalAccessException {                    //사다리 선택 > 도착 번호
//        drawLadder();
        if(!validateStarPoint(startPoint)){
            throw new IllegalAccessException("해당 위치에서 사다리 타기를 시작할 수 없습니다.");
        }

        int endPoint = findEndPoint(startPoint);
        System.out.println();
        System.out.println("사다리 타기의 결과 " + startPoint + "의 종료 위치는 " + (endPoint+1) + "입니다.");
    }
}


