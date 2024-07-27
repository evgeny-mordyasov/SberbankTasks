package mordyasov_3_years_later.operation;

public class DivisionOperation implements Operation {

    @Override
    public double compute(double leftOperand, double rightOperand) {
        return leftOperand / rightOperand;
    }

    @Override
    public String operation() {
        return "/";
    }
}
