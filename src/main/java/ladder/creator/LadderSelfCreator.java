package ladder.creator;

import ladder.GreaterThanOne;
import ladder.Position;
import ladder.Row;

public class LadderSelfCreator implements LadderCreator{
    private final Row[] rows;

    public LadderSelfCreator(GreaterThanOne numberOfRow, GreaterThanOne numberOfPerson) {
        rows = new Row[numberOfRow.getNumber()];
        for (int i = 0; i < numberOfRow.getNumber(); i++) {
            rows[i] = new Row(numberOfPerson);
        }
    }


    public void drawLine(Position row, Position col) {
        rows[row.getValue()].drawLine(col);
    }

    public void setRowsString(){
        for(int i = 0; i < rows.length; i++){
            rows[i].setRowString();
        }
    }

    public Row[] getRows() {
        return rows;
    }



}
