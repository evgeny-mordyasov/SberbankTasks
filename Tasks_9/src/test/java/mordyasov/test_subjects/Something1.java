package mordyasov.test_subjects;

import mordyasov.annotations.DoTest;

public class Something1 {
    @DoTest(order = 3)
    public int doSomething1() {
        return 0;
    }

    @DoTest(order = 2)
    public int doSomething2() {
        return 1;
    }

    @DoTest(order = 1)
    public int doSomething3() {
        return 2;
    }
}