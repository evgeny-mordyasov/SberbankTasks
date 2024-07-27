package mordyasov_3_years_later.operation;

public class UnknownOperation implements Operation {

    @Override
    public double compute(double leftOperand, double rightOperand) {
        throw new UnsupportedOperationException("Operation not defined.");
    }

    @Override
    public String operation() {
        throw new UnsupportedOperationException("Operation not defined.");
    }
}
