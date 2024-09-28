package ladder;

import ladder.creator.LadderCreator;

public class LadderGame {

    private final LadderCreator ladderCreator;

    public LadderGame(LadderCreator ladderCreator) {
        ladderCreator.setRowsString();                  //모든 행에 해당하는 문자열 생성
        this.ladderCreator = ladderCreator;
    }

    public int run(Position position) {
        LadderRunner ladderRunner = new LadderRunner(ladderCreator.getRows());
        ladderRunner.run(position);
        return position.getValue();
    }
}
