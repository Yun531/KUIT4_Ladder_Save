package ladder.creator;

import ladder.LadderPosition;
import ladder.GreaterThanOne;
import ladder.Position;
import ladder.Row;

public class LadderAutoCreator implements LadderCreator{

    private final Row[] rows;

    public LadderAutoCreator(GreaterThanOne numberOfRow, GreaterThanOne numberOfPerson) {
        rows = new Row[numberOfRow.getNumber()];

        for (int i = 0; i < numberOfRow.getNumber(); i++) {
            rows[i] = new Row(numberOfPerson);
        }

        drawLine(Position.from(numberOfRow.getNumber()), Position.from(numberOfPerson.getNumber()));
    }

    public void drawLine(Position numberOfRow, Position numberOfCol){
        int numberOfLine = calcLineNumber(numberOfRow.getValue(), numberOfCol.getValue());

        for(int i = 0; i < numberOfLine; ){
            if(canDrawLine(LadderPosition.autoLadderPosition(numberOfCol.getValue(), numberOfRow.getValue()))){
                i++;            //실제로 Line이 그려진 경우 for문의 i를 증가시킴
            }
        }
    }

    public void setRowsString(){
        for(int i = 0; i < rows.length; i++){
            rows[i].setRowString();
        }
    }

    public Row[] getRows() {
        return rows;
    }

    //Line 생성 개수를 안전하게 '내림'하여 사용 함
    private int calcLineNumber(int numberOfRow, int numberOfPerson){
        return (int)Math.floor(numberOfRow*numberOfPerson*0.3);
    }

    //랜덤으로 생성한 좌표에서 Line을 그릴수 있는지 확인
    private boolean canDrawLine(LadderPosition ladderPosition) {
        try {
            rows[ladderPosition.getRow()].drawLine(ladderPosition.getCol());
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
