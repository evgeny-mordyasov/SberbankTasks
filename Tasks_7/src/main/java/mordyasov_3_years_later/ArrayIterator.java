package mordyasov_3_years_later;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T> {

    private final T[] array;
    private int currentIndex;

    public ArrayIterator(T[][] array) {
        this.array = toOneDimensional(array);
    }

    @Override
    public boolean hasNext() {
        return currentIndex != array.length;
    }

    @Override
    public T next() {
        if (array.length == currentIndex) {
            throw new NoSuchElementException();
        }

        return array[currentIndex++];
    }

    @SuppressWarnings("unchecked")
    private T[] toOneDimensional(T[][] array) {
        return (T[]) Arrays.stream(array)
                .flatMap(Arrays::stream)
                .toArray();
    }
}