package mordyasov_3_years_later;

public class CalculatorRunnable implements Runnable {

    private final Calculator calculator;
    private final ConsoleReader reader;

    public CalculatorRunnable(Calculator calculator, ConsoleReader reader) {
        this.calculator = calculator;
        this.reader = reader;
    }

    @Override
    public void run() {
        while (true) {
            try {
                run0();
            } catch (Exception e) {
                System.out.println("Incorrect data entered. Try again. Error: " + e.getMessage());
            }
        }
    }

    private void run0() {
        double[] numbers = readNumbersFromConsole();
        String operation = readOperationFromConsole();

        System.out.format("%.2f %s %.2f = %.2f\n",
                numbers[0], operation, numbers[1], calculator.compute(numbers[0], numbers[1], operation));
    }

    private double[] readNumbersFromConsole() {
        System.out.println("Enter two numbers: ");
        double number1 = reader.readDouble();
        double number2 = reader.readDouble();

        if (Double.isFinite(number1) && Double.isFinite(number2)) {
            return new double[]{ number1, number2 };
        }

        throw new RuntimeException("Entered NaN or Infinity.");
    }

    private String readOperationFromConsole() {
        System.out.println("Enter operation:");
        return reader.readOperation();
    }
}
