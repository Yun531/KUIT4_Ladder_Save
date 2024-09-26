package ladder;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class CoordinateTest {

    @Test
    void 좌표_생성_확인(){
        //when
        Coordinate coordinate = Coordinate.autoCoordinate(2, 2);

        //then
        assertThat(coordinate).isNotNull();
    }

    @Test
    void 자동생성좌표_범위_확인1(){

    }

}