package mordyasov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    @Test
    void test1() {
        Queue queue = new Queue();

        assertThrows(IllegalStateException.class, queue::top);
    }

    @Test
    void test2() {
        Queue queue = new Queue(2);

        queue.enqueue(1);
        queue.enqueue(2);

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> queue.enqueue(3));
    }

    @Test
    void test3() {
        Queue queue = new Queue(5);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals("[1, 2, 3]", queue.toString());
    }

    @Test
    void test4() {
        Queue queue = new Queue(5);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        queue.dequeue();
        queue.dequeue();

        assertEquals("[3]", queue.toString());
    }

    @Test
    void test5() {
        Queue queue = new Queue(5);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(1, queue.top());
    }

    @Test
    void test6() {
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
    void test7() {
        Queue queue = new Queue(3);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertTrue(queue.isFull());
    }

    @Test
    void test8() {
        Queue queue = new Queue(3);

        queue.enqueue(1);
        queue.dequeue();

        assertEquals("[]", queue.toString());
    }

    @Test
    void test9() {
        Queue queue = new Queue(1);

        queue.enqueue(1);

        assertFalse(queue.isEmpty());
    }

    @Test
    void test10() {
        Queue queue = new Queue(3);

        queue.enqueue(1);
        queue.dequeue();
        queue.enqueue(2);
        queue.dequeue();

        assertEquals("[]", queue.toString());
    }
}