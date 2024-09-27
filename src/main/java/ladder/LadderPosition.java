package ladder;

import java.util.Random;

public class LadderPosition {
    private final int row;
    private final Position col;

    private LadderPosition(int col, int row){
        this.row = row;
        this.col = Position.from(col);
    }

    public static LadderPosition autoCoordinate(int numberOfCol, int numberOfRow){
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        int col = rand.nextInt(numberOfCol-1);          //line을 그려줘야 되기 때문에 옆으로 한칸 여유가 있어야 함
        int row = rand.nextInt(numberOfRow);

        return new LadderPosition(col, row);
    }

    public int getRow() {
        return row;
    }

    public Position getCol() {
        return col;
    }
}
