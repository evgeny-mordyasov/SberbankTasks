package mordyasov_3_years_later;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack<T> {


    private final T[] arrayOfObjects;
    private int numberOfElements;

    public Stack() {
        this(10);
    }

    @SuppressWarnings("unchecked")
    public Stack(int arrayCapacity) {
        arrayOfObjects = (T[]) new Object[arrayCapacity];
    }

    public T push(T element) {
        if (isFull()) {
            throw new ArrayIndexOutOfBoundsException("Стек заполнен.");
        }

        arrayOfObjects[numberOfElements++] = element;
        return element;
    }

    public T top() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return arrayOfObjects[numberOfElements - 1];
    }

    public T pop() {
        T element = top();
        arrayOfObjects[--numberOfElements] = null;
        return element;
    }

    public boolean isEmpty() {
        return numberOfElements == 0;
    }

    public boolean isFull() {
        return numberOfElements == arrayOfObjects.length;
    }

    @Override
    @SuppressWarnings("unchecked")
    public String toString() {
        T[] array = (T[]) new Object[numberOfElements];
        System.arraycopy(arrayOfObjects, 0, array, 0, numberOfElements);
        return Arrays.toString(array);
    }
}