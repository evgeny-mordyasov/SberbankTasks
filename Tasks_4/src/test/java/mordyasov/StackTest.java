package mordyasov;

import java.util.EmptyStackException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    void test1() {
        Stack stack = new Stack(4);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        assertEquals("[1, 2, 3, 4]", stack.toString());
    }

    @Test
    void test2() {
        Stack stack = new Stack(4);

        assertEquals("[]", stack.toString());
    }

    @Test
    void test3() {
        Stack stack = new Stack(1);

        stack.push(1);

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> stack.push(2));
    }

    @Test
    void test4() {
        Stack stack = new Stack(4);

        assertThrows(EmptyStackException.class, stack::top);
    }

    @Test
    void test5() {
        Stack stack = new Stack(4);

        stack.push(1);
        stack.push(2);
        stack.pop();

        assertEquals("[1]", stack.toString());
    }

    @Test
    void test6() {
        Stack stack = new Stack(10);

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.pop();
        stack.push(5);

        assertEquals("[1, 2, 3, 5]", stack.toString());
    }

    @Test
    void test7() {
        Stack stack = new Stack(10);

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();

        assertEquals("[]", stack.toString());
    }

    @Test
    void test8() {
        Stack stack = new Stack(10);

        stack.push(1);
        stack.pop();

        assertTrue(stack.isEmpty());
    }

    @Test
    void test9() {
        Stack stack = new Stack(3);

        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertTrue(stack.isFull());
    }

    @Test
    void test10() {
        Stack stack = new Stack(3);

        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.pop());
    }
}