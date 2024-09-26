package ladder.creator;

import ladder.Coordinate;
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
            if(canDrawLine(Coordinate.autoCoordinate(numberOfCol.getValue(), numberOfRow.getValue()))){
                i++;
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

    private int calcLineNumber(int numberOfRow, int numberOfPerson){
        return (int)Math.floor(numberOfRow*numberOfPerson*0.3);
    }

    private boolean canDrawLine(Coordinate coordinate) {
        try {
            rows[coordinate.getRow()].drawLine(coordinate.getCol());
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
