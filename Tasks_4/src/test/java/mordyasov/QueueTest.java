package mordyasov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {
    @Test
    public void test1() {
        Queue queue = new Queue();

        assertThrows(IllegalStateException.class, () -> queue.top());
    }

    @Test
    public void test2() {
        Queue queue = new Queue(2);

        queue.enqueue(1);
        queue.enqueue(2);

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> queue.enqueue(3));
    }

    @Test
    public void test3() {
        Queue queue = new Queue(5);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals("[1, 2, 3]", queue.toString());
    }

    @Test
    public void test4() {
        Queue queue = new Queue(5);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        queue.dequeue();
        queue.dequeue();

        assertEquals("[3]", queue.toString());
    }

    @Test
    public void test5() {
        Queue queue = new Queue(5);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(1, queue.top());
    }

    @Test
    public void test6() {
        Queue queue = new Queue(5);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        queue.dequeue();
        queue.dequeue();
        queue.dequeue();

        assertTrue(queue.isEmpty());
    }

    @Test
    public void test7() {
        Queue queue = new Queue(3);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertTrue(queue.isFull());
    }

    @Test
    public void test8() {
        Queue queue = new Queue(3);

        queue.enqueue(1);

        queue.dequeue();

        assertEquals("[]", queue.toString());
    }

    @Test
    public void test9() {
        Queue queue = new Queue(1);

        queue.enqueue(1);

        assertFalse(queue.isEmpty());
    }

    @Test
    public void test10() {
        Queue queue = new Queue(3);

        queue.enqueue(1);
        queue.dequeue();
        queue.enqueue(2);
        queue.dequeue();

        assertEquals("[]", queue.toString());
    }
}