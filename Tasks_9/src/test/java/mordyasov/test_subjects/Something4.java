package mordyasov.test_subjects;

import mordyasov.annotations.DoAfterAll;
import mordyasov.annotations.DoTest;

public class Something4 {
    @DoAfterAll
    public int doSomething1() {
        return 0;
    }

    @DoAfterAll
    public int doSomething2() {
        return 1;
    }

    @DoTest(order = 1)
    public int doSomething3() {
        return 2;
    }
}