package mordyasov_3_years_later;

import java.util.Arrays;

public class Queue<T> {

    private final T[] arrayOfObjects;
    private int numberOfElements;

    public Queue() {
        this(10);
    }

    @SuppressWarnings("unchecked")
    public Queue(int arrayCapacity) {
        arrayOfObjects = (T[]) new Object[arrayCapacity];
    }

    public T enqueue(T element) {
        if (isFull()) {
            throw new ArrayIndexOutOfBoundsException("Очередь заполнена.");
        }

        arrayOfObjects[numberOfElements++] = element;
        return element;
    }

    public T top() {
        if (isEmpty()) {
            throw new IllegalStateException("Очередь пуста.");
        }

        return arrayOfObjects[0];
    }

    public T dequeue() {
        T element = top();
        System.arraycopy(arrayOfObjects, 1, arrayOfObjects, 0, --numberOfElements);
        arrayOfObjects[numberOfElements] = null;
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
