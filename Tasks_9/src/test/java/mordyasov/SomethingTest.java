package mordyasov;

import mordyasov.annotations.AnnotationProcessor;
import mordyasov.exceptions.AnnotationException;
import mordyasov.test_subjects.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SomethingTest {

    /**
     * Ситуация, при которой в классе все аннотации выставлены. Необходимо проверить порядок вызова.
     */
    @Test
    void test() {
        assertArrayEquals(new Object[]{0, 1, 3, 2, 4}, AnnotationProcessor.runningTestMethods(Something.class).toArray());
    }

    /**
     * Ситуация, при которой в классе не выставлены аннотации @DoBeforeAll, @DoAfterAll. Необходимо проверить порядок вызова.
     */
    @Test
    void test1() {
        assertArrayEquals(new Object[]{2, 1, 0}, AnnotationProcessor.runningTestMethods(Something1.class).toArray());
    }

    /**
     * Ситуация, при которой в классе нет ни одной аннотации. Ожидается ошибка.
     */
    @Test
    void test2() {
        assertThrows(AnnotationException.class, () -> AnnotationProcessor.runningTestMethods(Something2.class));
    }

    /**
     * Ситуация, при которой аннотация @DoBeforeAll встречается два раза, что является ошибкой.
     */
    @Test
    void test3() {
        assertThrows(AnnotationException.class, () -> AnnotationProcessor.runningTestMethods(Something3.class));
    }

    /**
     * Ситуация, при которой аннотация @DoAfterAll встречается два раза, что является ошибкой.
     */
    @Test
    void test4() {
        assertThrows(AnnotationException.class, () -> AnnotationProcessor.runningTestMethods(Something4.class));
    }

    /**
     * Ситуация, при которой указано некорректное значение order. Ожидается ошибка.
     */
    @Test
    void test5() {
        assertThrows(AnnotationException.class, () -> AnnotationProcessor.runningTestMethods(Something5.class));
    }
}
