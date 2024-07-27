package mordyasov_3_years_later;

import mordyasov_3_years_later.operation.Operation;
import mordyasov_3_years_later.operation.OperationProvider;

public class Calculator {

    private final OperationProvider provider;

    public Calculator(OperationProvider provider) {
        this.provider = provider;
    }

    public double compute(double leftOperand, double rightOperand, String operation) {
        Operation requestedOperation = provider.getOperation(operation);
        return requestedOperation.compute(leftOperand, rightOperand);
    }
}
