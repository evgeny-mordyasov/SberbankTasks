package mordyasov;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Класс Stack представляет собой реализацию принципа LIFO, содержащий основные операции работы со стеком.
 */
public class Stack {
    /**
     * Поле arrayOfObjects представляет собой массив Object, необходимый для хранения элементов стека.
     * Имеет модификатор final потому, что массив является неизменным по количеству элементов. То есть, реализовано так,
     * что стек будет ограничен определенным числом.
     */
    private final Object [] arrayOfObjects;

    /**
     * Поле numberOfElements содержит в себе количество не null элементов в поле arrayOfObjects.
     */
    private int numberOfElements;

    public Stack() {
        this(10);
    }

    public Stack(int arrayCapacity) {
        arrayOfObjects = new Object[arrayCapacity];
    }

    /**
     * Функция, которая вставляет элемент в стек.
     * @param element вставляемый объект в стек.
     * @returns element.
     * @Throws ArrayIndexOutOfBoundsException, если стек заполнен.
     * @see #isFull() функция проверки стека на заполненность.
     */
    public Object push(Object element) {
        if (isFull())
            throw new ArrayIndexOutOfBoundsException("Стек заполнен.");

        arrayOfObjects[numberOfElements++] = element;

        return element;
    }

    /**
     * Фукнция, возвращающая верхний элемент в стеке.
     * @returns верхний элемент.
     * @Throws EmptyStackException, если стек пустой.
     * @see #isEmpty() функция проверки стека на пустоту.
     */
    public Object top() {
        if (isEmpty())
            throw new EmptyStackException();

        return arrayOfObjects[numberOfElements - 1];
    }

    /**
     * Функция, возвращающая верхний элемент из стека, предварительно удалив его.
     * @returns удаленный верхний элемент.
     * @Throws EmptyStackException, если стек пустой.
     * @see #top() функция взятия верхнего элемента.
     */
    public Object pop() {
        Object element = top();
        arrayOfObjects[--numberOfElements] = null;

        return element;
    }

    /**
     * Функция, проверяющая содержимое стека на пустоту.
     * @returns
     * 1. true, если стек пустой.
     * 2. false, если стек содержит хотя бы один элемент.
     */
    public boolean isEmpty() {
        return numberOfElements == 0;
    }

    /**
     * Функция, проверяющая содержимое стека на заполненность. Необходима для того, чтобы избежать исключения переполнения.
     * @returns
     * 1. true, если стек заполнен.
     * 2. false, если в стек можно поместить еще элементы.
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
        Object [] array = new Object[numberOfElements];
        System.arraycopy(arrayOfObjects, 0, array, 0, numberOfElements);

        return Arrays.toString(array);
    }
}