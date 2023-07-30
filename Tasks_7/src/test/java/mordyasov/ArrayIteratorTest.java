package mordyasov;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ArrayIteratorTest {

    private static Integer[][] ARRAY;

    /**
     * Поле, которое будет хранить в себе все элементы массива в строковом представлении.
     */
    private static final StringBuilder ELEMENTS = new StringBuilder();

    /**
     * Генератор случайных чисел.
     */
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    /**
     * Поле, отвечающее за то, какой двумерный массив создавать и тестировать: матрицу или переменной длины.
     */
    private static final boolean IS_MATRIX = true;

    /**
     * Тест, который выполняется один раз и инициализирует массив, заполняющийся случайным образом.
     */
    @BeforeAll
    static void init() {
        ARRAY = getArray();

        for(int i = 0; i < ARRAY.length; i++) {
            for(int j = 0; j < ARRAY[i].length; j++) {
                ARRAY[i][j] = RANDOM.nextInt(0, 100);
                ELEMENTS.append(ARRAY[i][j]);
            }
        }
    }

    /**
     * Функция, которая определяет, какой массив необходимо вернуть для тестирования.
     * @returns:
     * 1. массив-матрицу, если isMatrix равен true.
     * 2. массив переменной длины, если isMatrix равен false.
     */
    private static Integer[][] getArray() {
        return IS_MATRIX ? getMatrix() : getNotMatrix();
    }

    /**
     * Функция, инициализирующая массив переменной длины.
     * @returns двумерный массив пременной длины.
     */
    private static Integer[][] getNotMatrix() {
        Integer[][] arr = new Integer[RANDOM.nextInt(1, 10)][];
        IntStream.range(0, arr.length).forEach(i ->
                arr[i] = new Integer[RANDOM.nextInt(1, 10)]);

        return arr;
    }

    /**
     * Функция, инициализирующая массив-матрицу.
     * @returns двумерный массив-матрицу.
     */
    private static Integer[][] getMatrix() {
        return new Integer[RANDOM.nextInt(1, 10)][RANDOM.nextInt(1, 10)];
    }

    /**
     * Тест, в котором сравниваются две строки: elements и result.
     * element - это строка, получаемая в методе init() при инициализации массива.
     * result - строка, получаемая при извлекании элементов из массива с помощью итератора.
     */
    @Test
    void test() {
        ArrayIterator<Integer> iterator = new ArrayIterator<>(ARRAY);
        StringBuilder result = new StringBuilder();

        while (iterator.hasNext()) {
            result.append(iterator.next());
        }

        assertEquals(ELEMENTS.toString(), result.toString());
    }

    /**
     * Тест, в котором проверяется, выпадает ли ошибка NoSuchElementException, если перебрать все элементы и выйти за границу.
     */
    @Test
    void test1() {
        ArrayIterator<Integer> iterator = new ArrayIterator<>(ARRAY);

        assertThrows(NoSuchElementException.class, () -> {
            while (true) {
                iterator.next();
            }
        });
    }
}