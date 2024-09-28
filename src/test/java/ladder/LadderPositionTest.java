package ladder;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class LadderPositionTest {

    @Test
    void 좌표_생성_확인(){
        //when
        LadderPosition ladderPosition = LadderPosition.autoLadderPosition(3, 3);

        //then
        assertThat(ladderPosition).isNotNull();
    }

    @Test
    void 자동생성좌표_범위_확인1(){
        //when
        LadderPosition ladderPosition = LadderPosition.autoLadderPosition(3, 3);

        //then
        assertThat(ladderPosition.getRow()).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(2);
        assertThat(ladderPosition.getCol().getValue()).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(1);
    }

    @Test
    void 자동생성좌표_범위_확인2(){
        //when
        LadderPosition ladderPosition1 = LadderPosition.autoLadderPosition(3, 5);
        LadderPosition ladderPosition2 = LadderPosition.autoLadderPosition(3, 5);
        LadderPosition ladderPosition3 = LadderPosition.autoLadderPosition(3, 5);

        //then
        assertThat(ladderPosition1.getRow()).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(4);
        assertThat(ladderPosition1.getCol().getValue()).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(2);
        assertThat(ladderPosition2.getRow()).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(4);
        assertThat(ladderPosition2.getCol().getValue()).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(2);
        assertThat(ladderPosition3.getRow()).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(4);
        assertThat(ladderPosition3.getCol().getValue()).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(2);
    }

}