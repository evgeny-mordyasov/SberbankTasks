package mordyasov_3_years_later;

import java.util.Scanner;

public class ConsoleReader {

    private final Scanner scanner;

    public ConsoleReader() {
        this.scanner = new Scanner(System.in);
    }

    public double readDouble() {
        return Double.parseDouble(scanner.nextLine());
    }

    public String readOperation() {
        return scanner.nextLine();
    }
}
