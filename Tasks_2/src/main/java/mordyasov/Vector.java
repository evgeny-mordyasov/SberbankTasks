package mordyasov;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Класс Vector описывает вектор в трёхмерном пространстве. Класс содержит методы для выполнения основных операций, таких как
 * вычисление длины вектора, угла между векторами, нахождение скалярного и векторного произведения векторов.
 */

public final class Vector {

    private final double x;
    private final double y;
    private final double z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Функция, вычисляющая длину вектора;
     * @return возвращает длину вектора.
     */
    public double length() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    /**
     * Функция, вычисляющая скалярное произведение векторов;
     * @param x - значение абсциссы;
     * @param y - значение ординаты;
     * @param z - значение аппликаты;
     * @return возвращает скалярное произведение векторов.
     */
    public double scalarProduct(double x, double y, double z) {
        return (this.x * x + this.y * y + this.z * z);
    }

    /**
     * Функция, вычисляющая скалярное произведение векторов;
     * @param vector - объект класса Vector, необходимый для вычисления скалярного произведения;
     * @see #scalarProduct(double, double, double);
     * @returns возвращает:
     * 1. Скалярное произведение векторов, если объект vector не равен null;
     * 2. 0, если объект vector равен null.
     */
    public double scalarProduct(Vector vector) {
        return vector == null ? 0 : scalarProduct(vector.x, vector.y, vector.z);
    }

    /**
     * Функция, вычисляющая векторное произведение;
     * @param x - значение абсциссы;
     * @param y - значение ординаты;
     * @param z - значение аппликаты;
     * @return возвращает объект класса Vector, полученный векторным произведением.
     */
    public Vector crossProduct(double x, double y, double z) {
        return new Vector(
                this.y * z - this.z * y,
                this.z * x - this.x * z,
                this.x * y - this.y * x);
    }

    /**
     * Функция, вычисляющая векторное произведение;
     * @param vector - объект класса Vector, необходимый для вычисления векторного произведения;
     * @see #crossProduct(double, double, double);
     * @returns возвращает:
     * 1. Объект класса Vector, полученный векторным произведением, если объект vector не равен null;
     * 2. null, если объект vector равен null.
     */
    public Vector crossProduct(Vector vector) {
        return vector == null ? null : crossProduct(vector.x, vector.y, vector.z);
    }

    /**
     * Функция, вычисляющая угол между векторами;
     * @param x - значение абсциссы;
     * @param y - значение ординаты;
     * @param z - значение аппликаты;
     * @return возвращает косинус угла между векторами.
     */
    public double angle(double x, double y, double z) {
        return scalarProduct(x, y, z) / (length() * new Vector(x, y, z).length());
    }

    /**
     * Функция, вычисляющая угол между векторами;
     * @param vector - объект класса Vector, необходимый для вычисления угла между векторами;
     * @see #angle(double, double, double);
     * @returns возвращает:
     * 1. Косинус угла между векторами, если объект vector не равен null;
     * 2. 0, если объект vector равен null.
     */
    public double angle(Vector vector) {
        return vector == null ? 0 : angle(vector.x, vector.y, vector.z);
    }

    /**
     * Функция, вычисляющая сумму векторов;
     * @param x - значение абсциссы;
     * @param y - значение ординаты;
     * @param z - значение аппликаты;
     * @return возвращает объект класса Vector с суммой координат векторов.
     */
    public Vector sum(double x, double y, double z) {
        return new Vector(this.x + x, this.y + y, this.z + z);
    }

    /**
     * Функция, вычисляющая сумму векторов;
     * @param vector - объект класса Vector, необходимый для вычисления суммы векторов;
     * @see #sum(double, double, double);
     * @returns возвращает:
     * 1. Объект класса Vector с суммой координат векторов, если объект vector не равен null;
     * 2. null, если объект vector равен null.
     */
    public Vector sum(Vector vector) {
        return vector == null ? null : sum(vector.x, vector.y, vector.z);
    }

    /**
     * Функция, вычисляющая разность векторов.
     * @param x - значение абсциссы;
     * @param y - значение ординаты;
     * @param z - значение аппликаты;
     * @return возвращает объект класса Vector с разностью координат векторов: (this - x; this - y; this - z).
     */
    public Vector difference(double x, double y, double z) {
        return new Vector(this.x - x, this.y - y, this.z - z);
    }

    /**
     * Функция, вычисляющая разность векторов.
     * @param vector - объект класса Vector, необходимый для вычисления разности векторов;
     * @see #difference(double, double, double);
     * @returns возвращает:
     * 1. Объект класса Vector с разностью координат векторов: (this - x; this - y; this - z), если объект vector не равен null;
     * 2. null, если объект vector равен null.
     */
    public Vector difference(Vector vector) {
        return vector == null ? null : difference(vector.x, vector.y, vector.z);
    }

    /**
     * Функция, генерирующая и возвращающая массив объектов класса Vector;
     * @param N - количество генерируемых векторов;
     * @returns возвращает:
     * 1. null, если N < 1;
     * 2. Массив объектов класса Vector, если N > 0.
     */
    public static Vector[] array(int N) {
        if (N <= 0) return null;

        Vector[] array = new Vector[N];
        for(int iterator = 0; iterator < N; iterator++) {
            array[iterator] = getRandomVector();
        }

        return array;
    }

    /**
     * Вспомогательная функция, возвращающая объект класса Vector, координаты которого сгенерируемы случайным образом;
     * @see #randomCoordinate();
     * @return возвращает объект класса Vector.
     */
    private static Vector getRandomVector() {
        return new Vector(randomCoordinate(),
                randomCoordinate(),
                randomCoordinate());
    }

    /**
     * Вспомогательная функция, возвращающая случайным образом вещественное число в диапазоне [-10; 10];
     * @return возвращает вещественное число.
     */
    private static double randomCoordinate() {
        return ThreadLocalRandom.current().nextDouble(-10, 11);
    }
}