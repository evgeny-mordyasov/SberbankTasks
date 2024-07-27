package mordyasov_3_years_later;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public final class Vector {

    private final double x;
    private final double y;
    private final double z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double length() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    public double scalarProduct(double x, double y, double z) {
        return (this.x * x + this.y * y + this.z * z);
    }

    public double scalarProduct(Vector vector) {
        return vector == null
                ? 0
                : scalarProduct(vector.x, vector.y, vector.z);
    }

    public Vector crossProduct(double x, double y, double z) {
        return new Vector(
                this.y * z - this.z * y,
                this.z * x - this.x * z,
                this.x * y - this.y * x);
    }

    public Vector crossProduct(Vector vector) {
        return vector == null
                ? null
                : crossProduct(vector.x, vector.y, vector.z);
    }

    public double angle(double x, double y, double z) {
        return scalarProduct(x, y, z) / (length() * new Vector(x, y, z).length());
    }

    public double angle(Vector vector) {
        return vector == null
                ? 0
                : angle(vector.x, vector.y, vector.z);
    }

    public Vector sum(double x, double y, double z) {
        return new Vector(this.x + x, this.y + y, this.z + z);
    }

    public Vector sum(Vector vector) {
        return vector == null
                ? null
                : sum(vector.x, vector.y, vector.z);
    }

    public Vector difference(double x, double y, double z) {
        return new Vector(this.x - x, this.y - y, this.z - z);
    }

    public Vector difference(Vector vector) {
        return vector == null
                ? null
                : difference(vector.x, vector.y, vector.z);
    }

    public static Vector[] randomArray(int n) {
        if (n <= 0) return new Vector[0];

        return IntStream.range(0, n)
                .mapToObj(i -> getRandomVector())
                .toArray(Vector[]::new);
    }

    private static Vector getRandomVector() {
        return new Vector(randomCoordinate(),
                randomCoordinate(),
                randomCoordinate());
    }

    private static double randomCoordinate() {
        return ThreadLocalRandom.current().nextDouble(Double.MIN_VALUE, Double.MAX_VALUE);
    }
}