package mordyasov.annotations;

import mordyasov.exceptions.AnnotationException;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс AnnotationProcessor, фиксирующий аннотации над методами какого-либо класса, желающего протестировать.
 */
public class AnnotationProcessor {
    /**
     * Функция, анализирующая аннотации над методами и запускающая методы по определенным правилам.
     * @param clazz класс, который содержит аннотации @DoTest, @DoBeforeAll или @DoAfterAll.
     * @return список объектов, хранящий порядок выполнения методов переданного в качестве параметра класса.
     * @see #methodList(Class) доп. функция, вытаскивающая методы с нужными аннотациями.
     * @see #sortingByMethodInvokeOrder(List) доп. функция, сортирующая методы в порядке приоритетного вызова.
     */
    public static List<Object> runningTestMethods(Class<?> clazz) {
        return sortingByMethodInvokeOrder(methodList(clazz)).stream().map(m -> {
            try {
                return m.invoke(clazz.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                return null;
            }
        }).collect(Collectors.toList());
    }

    /**
     * Функция, читающая все методы переданного в качестве параметра класса и находящая необходимые аннотации.
     * @param clazz класс, который содержит аннотации @DoTest, @DoBeforeAll или @DoAfterAll.
     * @return список объектов Method, содержащих одну из вышеуказанных аннотаций.
     */
    private static List<Method> methodList(Class<?> clazz) {
        List<Method> list = new ArrayList<>(Arrays.asList(null, null));

        Arrays.stream(clazz.getMethods()).forEach(m -> {
            if (m.isAnnotationPresent(DoTest.class)) {
                list.add(m);
            }
            else if (m.isAnnotationPresent(DoBeforeAll.class)) {
                if (list.get(0) == null) {
                    list.set(0, m);
                }
                else throw new AnnotationException("Число аннотаций DoBeforeAll больше 1.");
            }
            else if (m.isAnnotationPresent(DoAfterAll.class)) {
                if (list.get(1) == null) {
                    list.set(1, m);
                }
                else throw new AnnotationException("Число аннотаций DoAfterAll больше 1.");
            }
        });

        if (list.size() == 2) {
            throw new AnnotationException("В классе нет аннотаций DoTest.");
        }

        return list;
    }

    /**
     * Функция, позволяющая выстроить цепочку вызовов методов в приоритетном порядке.
     * @param list список, содержащий объекты Method, которые имеют аннотации @DoTest, @DoBeforeAll или @DoAfterAll.
     * @return список объектов Method, расположенных в порядке необходимого вызова.
     */
    private static List<Method> sortingByMethodInvokeOrder(List<Method> list) {
        List<Method> resultList = list.subList(2, list.size()).stream()
                .sorted(Comparator.comparingInt(m -> {
                    int order = m.getAnnotation(DoTest.class).order();

                    if (order < 1) {
                        throw new AnnotationException("Значение order в аннотации DoTest должно быть натуральным числом.");
                    }

                    return order;
                }))
                .collect(Collectors.toList());

        if (list.get(0) != null) {
            resultList.add(0, list.get(0));
        }

        if (list.get(1) != null) {
            resultList.add(list.get(1));
        }

        return resultList;
    }
}
