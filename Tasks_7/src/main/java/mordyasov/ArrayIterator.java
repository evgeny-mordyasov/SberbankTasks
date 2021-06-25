package mordyasov;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Класс, позволяющий перебрать все элементы двумерного массива.
 * @param <T> тип элементов массива.
 */
public class ArrayIterator<T> implements Iterator<T>{
    private T[] array;
    private int currentIndex;

    public ArrayIterator(T[][] array) {
        this.array = convertToOneDimensional(array);
    }

    /**
     * Функция, сообщающая о том, есть ли еще элементы в массиве.
     * @returns:
     * 1. true, если элементы еще есть.
     * 2. false, если все элементы были пройдены.
     */
    @Override
    public boolean hasNext() {
        return currentIndex != array.length;
    }

    /**
     * Функция, возвращающая следующий элемент из массива.
     * @returns элемент из массива.
     * @Throws NoSuchElementException, если вышли за границу массива.
     */
    @Override
    public T next() {
        if (array.length == currentIndex)
            throw new NoSuchElementException();

        return array[currentIndex++];
    }

    /**
     * Дополнительная функция, которая двумерный массив преобразует в одномерный.
     * @param array двумерный массив.
     * @returns одномерный массив.
     */
    private T[] convertToOneDimensional(T[][] array) {
        List<T> list = new ArrayList<>();

        IntStream.range(0, array.length).forEach(i -> list.addAll(Arrays.asList(array[i])));

        return (T[]) list.toArray();
    }
}