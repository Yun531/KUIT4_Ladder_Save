package ladder;

import static ladder.Flag.*;

public class LadderRunner {

    private final Row[] rows;

    public LadderRunner(Row[] rows) {
        this.rows = rows;
    }

    public int run(LadderPosition ladderPosition) {
        rows[0].validatePosition(ladderPosition.getCol());             //validatePosition()를 public 으로 변경, 지저분한데...
        //사다리 출력
        for (Row row : rows) {
            ladderPosition.next();                      //불필요하게 한줄 추가된 거 아닌가?
            printLadderRow(ladderPosition, AFTER);                     //기존에는 rows[i].nextPosition(position)에서 시작위치 유효성 검사를 해줬는데 해주기 전에 그려버리니까 오류 발생
            row.nextPosition(ladderPosition.getCol());             //어차피 i 를 사용해서 접근해야 하는데 printLadderRow의 인자를 '좌표'로 묶어서 줘야할 필요가 있나?
            printLadderRow(ladderPosition, BEFORE);
        }

        return ladderPosition.getCol().getValue();
    }

    //전체 사다리 출력 함수
    private void printLadderRow(LadderPosition ladderPosition, Flag flag){
        StringBuilder sb = new StringBuilder("\n" + flag.getFlag() + "\n");

        for(int i = 0; i < rows.length; i++) {
            if(i != ladderPosition.getRow()){
                sb.append(rows[i].getRowStringBuilder());
            }
            if(i == ladderPosition.getRow()){
                sb.append(rows[i].getRowStringBuilder(ladderPosition.getCol()));
            }
        }

        System.out.println(sb);
    }

}
