package mordyasov_3_years_later.operation;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DefaultOperationProvider implements OperationProvider {

    private final Map<String, Operation> operations;

    public DefaultOperationProvider(List<Operation> operations) {
        this.operations = operations.stream()
                .collect(Collectors.toMap(Operation::operation, Function.identity()));
    }

    @Override
    public Operation getOperation(String operation) {
        return operations.getOrDefault(operation, Operation.unknownOperation());
    }
}
