package mordyasov_3_years_later;

import mordyasov_3_years_later.operation.AdditionOperation;
import mordyasov_3_years_later.operation.DefaultOperationProvider;
import mordyasov_3_years_later.operation.DivisionOperation;
import mordyasov_3_years_later.operation.ModuloOperation;
import mordyasov_3_years_later.operation.MultiplicationOperation;
import mordyasov_3_years_later.operation.Operation;
import mordyasov_3_years_later.operation.OperationProvider;
import mordyasov_3_years_later.operation.SubtractionOperation;

import java.util.List;

public class ConsoleApplication {

    private static final List<Operation> OPERATIONS = List.of(
            new AdditionOperation(),
            new SubtractionOperation(),
            new MultiplicationOperation(),
            new DivisionOperation(),
            new ModuloOperation()
    );

    public static void main(String[] args) {
        OperationProvider provider = new DefaultOperationProvider(OPERATIONS);
        Calculator calculator = new Calculator(provider);
        ConsoleReader reader = new ConsoleReader();
        CalculatorRunnable runnable = new CalculatorRunnable(calculator, reader);
        new Thread(runnable).start();
    }
}