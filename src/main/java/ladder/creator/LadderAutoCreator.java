package ladder.creator;

import ladder.LadderPosition;
import ladder.GreaterThanOne;
import ladder.Position;
import ladder.Row;

import java.util.HashSet;

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
        HashSet<LadderPosition> line = new HashSet<>();

        while(line.size() < numberOfLine){
            canDrawLine(line, LadderPosition.autoLadderPosition(numberOfCol.getValue(), numberOfRow.getValue()));
        }
    }

    public void setRowsString(){
        for (Row row : rows) {
            row.setRowString();
        }
    }

    public Row[] getRows() {
        return rows;
    }

    //Line 생성 개수를 안전하게 '내림'하여 사용 함
    private int calcLineNumber(int numberOfRow, int numberOfPerson){
        return (int)Math.floor(numberOfRow*numberOfPerson*0.3);
    }

    //랜덤으로 생성한 좌표에서 Line을 그림
    private boolean canDrawLine(HashSet<LadderPosition> line, LadderPosition ladderPosition) {
        try {
            rows[ladderPosition.getRow()].drawLine(ladderPosition.getCol());
            line.add(ladderPosition);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
