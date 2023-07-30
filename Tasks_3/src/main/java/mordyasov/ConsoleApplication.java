package mordyasov;

import java.util.Scanner;

public class ConsoleApplication {
    private static final Scanner read = new Scanner(System.in);

    public static void main(String[] args) {
        run();
    }

    /**
     * Процедура, запускающая работу простого консольного калькулятора.
     * На экран выводит результат вычисленного выражения.
     */
    private static void run() {
        while (true) {
            double[] numbers = readNumbersFromConsole();
            String operation = readOperationFromConsole();

            System.out.format("%.2f %s %.2f = %.2f\n",
                    numbers[0], operation, numbers[1], Calculator.compute(numbers[0], numbers[1], operation));
        }
    }

    /**
     * Функция ввода чисел с консоли, выполняющая до тех пор, пока не будут введены корректные числа.
     * @see #isInfinityOrNaN(double[]) вспомогательная функция.
     * @returns возвращает массив вещественных чисел длины 2.
     */
    private static double[] readNumbersFromConsole() {
        while (true) {
            try {
                System.out.println("Введите два числа: ");
                double[] numbers = new double[]{ Double.parseDouble(read.nextLine()), Double.parseDouble(read.nextLine()) };

                if (isInfinityOrNaN(numbers))
                    throw new NumberFormatException();

                return numbers;
            } catch (NumberFormatException e) {
                System.out.println("Введены некорректные данные. Повторите попытку.");
            }
        }
    }

    /**
     * Функция, проверяющая массив вещественных чисел на принадлежность к Infinity, -Infinity, NaN.
     * @param numbers массив вещественных чисел.
     * @returns возвращает:
     * 1. true, если первое или второе число массива является Infinity, -Infinity или NaN.
     * 2. false, если первое и второе число - это не Infinity, -Infinity или NaN.
     */
    private static boolean isInfinityOrNaN(double[] numbers) {
        return "-InfinityNaN".contains(String.valueOf(numbers[0])) ||
                "-InfinityNaN".contains(String.valueOf(numbers[1]));
    }

    /**
     * Функция ввода математической операции с консоли, выполняющая до тех пор, пока не будет введен один из символов: +-*%/
     * @see #isOperation(String) вспомогательная функция.
     * @returns возвращает один символ из указанных: +-*%/
     */
    private static String readOperationFromConsole() {
        while (true) {
            System.out.println("Введите операцию (+-*/%)");
            String operation = read.nextLine();

            if (!isOperation(operation)) continue;

            return operation;
        }
    }

    /**
     * Функция, проверяющая на корректность введенной операции.
     * @param operation строка, содержащая символ операции.
     * @returns возвращает:
     * 1. true, если operation содержит один символ и operation является одним из символов: +-*%/
     * 2. false, если оба из указанных условий в 1) не выполняются.
     */
    private static boolean isOperation(String operation) {
        return operation.length() == 1 && "+-*/%".contains(operation);
    }
}