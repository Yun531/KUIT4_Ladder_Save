package ladder;

import static ladder.Flag.*;

public class LadderRunner {

    private final Row[] rows;

    public LadderRunner(Row[] rows) {
        this.rows = rows;
    }

    public int run(Position position) {
        rows[0].validatePosition(position);             //지저분한데...
        //사다리 출력
        for (int i = 0; i < rows.length; i++) {
            printLadderRow(i, position, AFTER);      //기존에는 rows[i].nextPosition(position)에서 시작위치 유효성 검사를 해줬는데 해주기 전에 그려버리니까 오류 발생
            rows[i].nextPosition(position);
            printLadderRow(i, position, BEFORE);
        }

        return position.getValue();
    }

    //사다리 출력 함수
    private void printLadderRow(int rowIndex, Position position, Flag flag){          //전체 사다리 출력 함수
        StringBuilder sb = new StringBuilder("/n" + flag.getFlag() + "/n");

        for(int i = 0; i < rows.length; i++) {
            if(i != rowIndex){
                sb.append(rows[rowIndex].getRowStringBuilder());
            }
            if(i == rowIndex){
                sb.append(rows[rowIndex].getRowStringBuilder(position));
            }
        }

        System.out.println(sb);
    }

}
