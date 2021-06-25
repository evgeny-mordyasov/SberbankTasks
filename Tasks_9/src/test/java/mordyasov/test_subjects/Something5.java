package mordyasov.test_subjects;

import mordyasov.annotations.DoTest;

public class Something5 {
    @DoTest(order = -1)
    public int doSomething1() {
        return 0;
    }

    @DoTest(order = 0)
    public int doSomething2() {
        return 1;
    }

    @DoTest(order = 1)
    public int doSomething3() {
        return 2;
    }
}
