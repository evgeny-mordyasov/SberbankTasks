package mordyasov_3_years_later.operation;

public interface Operation {

    Operation UNKNOWN_OPERATION = new UnknownOperation();

    static Operation unknownOperation() {
        return UNKNOWN_OPERATION;
    }

    double compute(double leftOperand, double rightOperand);

    String operation();
}