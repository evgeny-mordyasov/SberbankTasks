package mordyasov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task5Test {
    
    @Test
    void test1() {
        KeyboardsFactory factory = new LogitechFactory();

        assertEquals(LogitechWired.class, factory.createWired().getClass());
    }

    @Test
    void test2() {
        KeyboardsFactory factory = new LogitechFactory();

        assertEquals(LogitechWireless.class, factory.createWireless().getClass());
    }

    @Test
    void test3() {
        KeyboardsFactory factory = new RazerFactory();

        assertEquals(RazerWired.class, factory.createWired().getClass());
    }

    @Test
    void test4() {
        KeyboardsFactory factory = new RazerFactory();

        assertEquals(RazerWireless.class, factory.createWireless().getClass());
    }
}
