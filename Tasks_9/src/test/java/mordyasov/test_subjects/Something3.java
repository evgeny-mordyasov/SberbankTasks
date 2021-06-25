package mordyasov.test_subjects;

import mordyasov.annotations.DoBeforeAll;
import mordyasov.annotations.DoTest;

public class Something3 {
    @DoBeforeAll
    public int doSomething1() {
        return 0;
    }

    @DoBeforeAll
    public int doSomething2() {
        return 1;
    }

    @DoTest(order = 1)
    public int doSomething3() {
        return 2;
    }
}
