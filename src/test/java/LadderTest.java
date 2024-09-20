import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.*;

class LadderTest {

    static Ladder ladder;
    static int[][] rows;

//    @BeforeAll
//    static void beforeAll() throws NoSuchFieldException, IllegalAccessException {
//        ladder = new Ladder(6, 3);
//
//        Field field = Ladder.class.getDeclaredField("rows");
//        field.setAccessible(true);
//        rows = (int[][]) field.get(ladder);
//    }
    @BeforeEach
    void beforeEach() throws NoSuchFieldException, IllegalAccessException {
        ladder = new Ladder(6, 3);

        Field field = Ladder.class.getDeclaredField("rows");                             //private 멤버 접근 방법
        field.setAccessible(true);
        rows = (int[][]) field.get(ladder);
    }

    @Test
    @DisplayName("사다리 생성 테스트")
    void testLadderCreation() {
        assertThat(ladder).isNotNull();


        for(int i = 0; i < 6; i++){
            for(int k = 0; k < 3; k++){
                assertThat(rows[i][k]).isEqualTo(0);
            }
        }
    }

    @Test
    @DisplayName("사다리 라인 입력 범위 테스트")
    public void testInputLine() throws NoSuchMethodException {                                  //private 메서드 접근 방법
        Method method = Ladder.class.getDeclaredMethod("inputLine", int.class, int.class, int.class);
        method.setAccessible(true);

        assertThatThrownBy(() -> {
            try{
                method.invoke(ladder, 1, 1, 3);
            }
            catch (InvocationTargetException e){                    //리플렉션으로 예외를 발생시키면, 해당 예외가 InvocationTargetException으로 감싸져서 전달
                throw e.getCause();
            }
        })
                .isInstanceOf(IllegalAccessException.class);

        assertThatThrownBy(() -> {
            try{
                method.invoke(ladder, 3, 1, 3);
            }
            catch (InvocationTargetException e){
                throw e.getCause();
            }
        })
                .isInstanceOf(IllegalAccessException.class);
    }

    @Test
    @DisplayName("사다리 중복 생성 테스트")
    public void testCheckLine() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = Ladder.class.getDeclaredMethod("checkLine", Position.class);
        method.setAccessible(true);

        assertThat(method.invoke(ladder, new Position(0, 0, 1))).isEqualTo(true);
        assertThat(method.invoke(ladder, new Position(1, 0, 1))).isEqualTo(true);
        assertThat(method.invoke(ladder, new Position(0, 1, 2))).isEqualTo(false);
    }

    @Test
    @DisplayName("사다리 게임 테스트")
    void testRun() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = Ladder.class.getDeclaredMethod("checkLine", Position.class);
        method.setAccessible(true);
        Method methodStart = Ladder.class.getDeclaredMethod("start", int.class);
        methodStart.setAccessible(true);

        method.invoke(ladder, new Position(0, 0, 1));
        method.invoke(ladder, new Position(2, 0, 1));
        method.invoke(ladder, new Position(4, 0, 1));
        method.invoke(ladder, new Position(1, 1, 2));
        method.invoke(ladder, new Position(3, 1, 2));
        method.invoke(ladder, new Position(5, 1, 2));

        assertThat(methodStart.invoke(ladder, 1)).isEqualTo(0);
        assertThat(methodStart.invoke(ladder, 2)).isEqualTo(1);
        assertThat(methodStart.invoke(ladder, 3)).isEqualTo(2);
    }

}