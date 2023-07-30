package mordyasov;

/**
 * Класс Calculator содержит в себе один публичный метод, выполняющий вычисление определенного выражения вида a Q b,
 * где a, b - вещественные числа, а Q - это одна из операций: +-*%/
 */
public final class Calculator {

    private Calculator() {
    }

    /**
     * Функция, выполняющая определенное вычисление переданного выражения.
     * @param firstNumber первое вещественное число.
     * @param secondNumber второе вещественное число.
     * @param operation математическая операция, представленная в строковом виде: +-*%/
     * @see #sum(double, double) сумма чисел.
     * @see #difference(double, double) разность чисел.
     * @see #division(double, double) деление чисел.
     * @see #multiply(double, double) умножение чисел.
     * @see #moduleDivision(double, double) модульное деление чисел.
     * @returns возвращает:
     * 1. Сумму чисел, если operation - это символ +
     * 2. Разность чисел, если operation - это символ -
     * 3. Деление чисел, если operation - это символ /
     * 4. Умножение чисел, если operation - это *
     * 5. Модульное деление чисел, если operation - %
     */
    public static double compute(double firstNumber, double secondNumber, String operation) {
        return switch (operation) {
            case "+" -> sum(firstNumber, secondNumber);
            case "-" -> difference(firstNumber, secondNumber);
            case "/" -> division(firstNumber, secondNumber);
            case "*" -> multiply(firstNumber, secondNumber);
            default -> moduleDivision(firstNumber, secondNumber);
        };
    }

    /**
     * Функция, вычисляющая сумму чисел a и b.
     * @param a первое число.
     * @param b второе число.
     * @returns возвращает сумму двух чисел (a + b).
     */
    private static double sum(double a, double b) {
        return a + b;
    }

    /**
     * Функция, вычисляющая разность чисел a и b.
     * @param a первое число (уменьшаемое).
     * @param b второе число (вычитаемое).
     * @returns возвращает разность двух чисел (a - b).
     */
    private static double difference(double a, double b) {
        return a - b;
    }

    /**
     * Функция, вычисляющая частое чисел a и b.
     * @param a первое число (делимое).
     * @param b второе число (делитель).
     * @returns возвращает:
     * 1. Частное двух чисел (a / b), если b ≠ 0.
     * 2. Infinity, если (a > 0 и b == 0) или (a < 0 и b ≠ -0).
     * 3. -Infinity, если (a < 0 и b == 0) или (a > 0 и b ≠ -0).
     * 4. NaN, если a и b равны 0.
     */
    private static double division(double a, double b) {
        return a / b;
    }

    /**
     * Функция, вычисляющая произведение чисел a и b.
     * @param a первое число.
     * @param b второе число.
     * @returns возвращает произведение двух чисел (a * b).
     */
    private static double multiply(double a, double b) {
        return a * b;
    }

    /**
     * Функция, выполняющее модульное деление чисел a и b.
     * @param a первое число.
     * @param b второе число.
     * @returns возвращает:
     * 1. Остаток от деления двух чисел (a % b), если b ≠ 0.
     * 2. NaN, если b == 0.
     */
    private static double moduleDivision(double a, double b) {
        return a % b;
    }
}