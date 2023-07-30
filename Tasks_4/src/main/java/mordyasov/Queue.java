package mordyasov;

import java.util.Arrays;

/**
 * Класс Queue представляет собой реализацию принципа FIFO, содержащий основные операции работы с очередью.
 */
public class Queue {

    /**
     * Поле arrayOfObjects представляет собой массив Object, необходимый для хранения элементов очереди.
     * Имеет модификатор final потому, что массив является неизменным по количеству элементов. То есть, реализовано так,
     * что очередь будет ограничена определенным числом.
     */
    private final Object[] arrayOfObjects;

    /**
     * Поле numberOfElements содержит в себе количество не null элементов в поле arrayOfObjects.
     */
    private int numberOfElements;

    public Queue() {
        this(10);
    }

    public Queue(int arrayCapacity) {
        arrayOfObjects = new Object[arrayCapacity];
    }

    /**
     * Функция, вставляющая элемент в конец очереди.
     * @param element вставляемый элемент.
     * @returns element.
     * @Throws ArrayIndexOutOfBoundsException, если очередь заполнена.
     * @see #isFull() функция проверки очереди на заполненность.
     */
    public Object enqueue(Object element) {
        if (isFull()) {
            throw new ArrayIndexOutOfBoundsException("Очередь заполнена.");
        }

        arrayOfObjects[numberOfElements++] = element;
        return element;
    }

    /**
     * Функция, возвращающая первый элемент в очереди.
     * @returns первый элемент в очереди.
     * @Throws IllegalStateException, если очередь пуста.
     * @see #isEmpty() функция проверки очереди на пустоту.
     */
    public Object top() {
        if (isEmpty()) {
            throw new IllegalStateException("Очередь пуста.");
        }

        return arrayOfObjects[0];
    }

    /**
     * Функция, возвращающая и удаляющая первый элемент в очереди. Удаление элемента из очереди достигается смещением массива на 1 элемент влево.
     * Последний элемент в очереди затирается, тк он дублируется при смещении.
     * @returns первый элемент в очереди.
     * @Throws IllegalStateException, если очередь пуста.
     * @see #top() функция взятия первого элемента в очереди.
     */
    public Object dequeue() {
        Object element = top();
        System.arraycopy(arrayOfObjects, 1, arrayOfObjects, 0, --numberOfElements);
        arrayOfObjects[numberOfElements] = null;

        return element;
    }

    /**
     * Функция, проверяющая содержимое очереди на пустоту.
     * @returns
     * 1. true, если очередь пустая.
     * 2. false, если очередь содержит хотя бы один элемент.
     */
    public boolean isEmpty() {
        return numberOfElements == 0;
    }

    /**
     * Функция, проверяющая содержимое очереди на заполненность. Необходима для того, чтобы избежать исключения переполнения.
     * @returns
     * 1. true, если очередь заполнена.
     * 2. false, если в очередь можно поместить еще элементы.
     */
    public boolean isFull() {
        return numberOfElements == arrayOfObjects.length;
    }

    /**
     * Функция, урезающая массив до того состояния, когда в нем содержатся только не null-элементы (убирает null).
     * @returns строковое представление массива.
     */
    @Override
    public String toString() {
        Object[] array = new Object[numberOfElements];
        System.arraycopy(arrayOfObjects, 0, array, 0, numberOfElements);

        return Arrays.toString(array);
    }
}
