package mordyasov;

/**
 * Если args.length > 2, то args[i], где i >= 2, не будут участвовать в сравнении.
 * Если args[0 или 1] являются не ЧИСЛОМ - возникнет исключение.
 * Если args[0 или 1] > Integer.maxValue или args[0 или 1] < Integer.minValue - возникнет исключение.
 * Если args[0 или 1] - вещественное число, то возникнет исключение.
 * Если args.length < 2, то возникнет исключение.
 */
public class Task1 {
    public static void main(String[] args) {
        System.out.format("Минимальное число: %d", Math.min(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
    }
}