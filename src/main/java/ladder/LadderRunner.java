package ladder;

import static ladder.Flag.*;

public class LadderRunner {

    private final Row[] rows;

    public LadderRunner(Row[] rows) {
        this.rows = rows;
    }

    public int run(Position position) {
        rows[0].validatePosition(position);             //validatePosition()를 public 으로 변경, 지저분한데...
        //사다리 출력
        for (int i = 0; i < rows.length; i++) {
            printLadder(LadderPosition.from(position, i), BEFORE);                     //기존에는 rows[i].nextPosition(position)에서 시작위치 유효성 검사를 해줬는데 해주기 전에 그려버리니까 오류 발생
            rows[i].nextPosition(position);
            printLadder(LadderPosition.from(position, i), AFTER);
        }

        return position.getValue();
    }

    //전체 사다리 출력 함수
    private void printLadder(LadderPosition ladderPosition, Flag flag){
        StringBuilder sb = new StringBuilder("\n" + flag.getFlag() + "\n");

        for(int i = 0; i < rows.length; i++) {
            sb.append(setLadderRow(i, ladderPosition));
        }

        System.out.println(sb);
    }

    private StringBuilder setLadderRow(int row, LadderPosition ladderPosition){
        if(row == ladderPosition.getRow()){
            return rows[row].getRowStringBuilder(ladderPosition.getCol());               //'*' 추가 X 문자열 받아옴
        }
        return rows[row].getRowStringBuilder();       //'*' 추가 X 문자열 받아옴
    }

}
