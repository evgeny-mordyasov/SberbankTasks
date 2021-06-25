package mordyasov.test_subjects;

import mordyasov.annotations.DoAfterAll;
import mordyasov.annotations.DoBeforeAll;
import mordyasov.annotations.DoTest;

public class Something {
    @DoBeforeAll
    public int doSomething() {
        return 0;
    }

    @DoTest(order = 1)
    public int doSomething1() {
        return 1;
    }

    @DoTest(order = 3)
    public int doSomething2() {
        return 2;
    }

    @DoTest(order = 2)
    public int doSomething3() {
        return 3;
    }

    @DoAfterAll
    public int doSomething4() {
        return 4;
    }
}
