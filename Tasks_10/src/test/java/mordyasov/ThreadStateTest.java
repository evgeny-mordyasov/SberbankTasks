package mordyasov;

import mordyasov.thread_states.StateBlocked;
import mordyasov.thread_states.StateTimeWaiting;
import mordyasov.thread_states.StateWaiting;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class ThreadStateTest {

    /**
     * Тестовый метод, позволяющий зафиксировать поток в состоянии NEW (создан, но не запущен).
     */
    @Test
    void test() {
        Thread thread = new Thread(() -> {});

        assertEquals(thread.getState(), Thread.State.NEW);
    }

    /**
     * Тестовый метод, позволяющий зафиксировать поток в состоянии RUNNABLE (запущенный).
     * Несмотря на то, что метод выполнения потока пустой, assertEquals успевает зафиксировать состояние RUNNABLE.
     * Это связано с тем, что для создания нового потока требуется кое-какое время. Этого времени хватает главному потоку
     * совершить проверку состояний.
     */
    @Test
    void test1() {
        Thread thread = new Thread(() -> {});
        thread.start();

        assertEquals(thread.getState(), Thread.State.RUNNABLE);
    }

    /**
     * Тестовый метод, позволяющий зафиксировать поток в состоянии TERMINATED (завершенный).
     */
    @Test
    void test2() throws InterruptedException {
        Thread thread = new Thread(() -> {});
        thread.start();
        thread.join();

        assertEquals(thread.getState(), Thread.State.TERMINATED);
    }

    /**
     * Тестовый метод, позволяющий зафиксировать поток в состоянии TIMED_WAITING (пребывание в режиме ожидания).
     * Создание потока осуществляется с помощью класса StateTimeWaiting.
     * После запуска потока отправляем главный поток в режим ожидания. Это сделано для того, чтобы только что запущенный поток
     * смог успеть войти в режим ожидания.
     * @see StateTimeWaiting класс, который демонстрирует ситуацию данного состояния.
     */
    @Test
    void test3() throws InterruptedException {
        Thread thread = new Thread(new StateTimeWaiting());

        thread.start();
        TimeUnit.MILLISECONDS.sleep(200);

        assertEquals(thread.getState(), Thread.State.TIMED_WAITING);
    }

    /**
     * Тестовый метод, позволяющий зафиксировать поток в состоянии WAITING (ожидающий поток).
     * Создаются два потока на основе объекта класса StateWaiting.
     * Запускается первый поток. Главный поток уходит в режим ожидания, чтобы только что запущенный поток успел вызвать wait().
     * Запускается второй поток.
     * Сразу же идет проверка на то, что первый поток имеет состояние WAITING. Только что запущенный второй поток не успевает вызвать
     * метод notify() в классе StateWaiting, тк необходимо кое-какое время для запуска потока.
     * @see StateWaiting класс, который демонстрирует ситуацию данного состояния.
     */
    @Test
    void test4() throws InterruptedException {
        StateWaiting object = new StateWaiting();

        Thread thread1 = new Thread(object);
        Thread thread2 = new Thread(object);

        thread1.start();
        TimeUnit.MILLISECONDS.sleep(200);

        thread2.start();

        assertEquals(thread1.getState(), Thread.State.WAITING);
    }

    /**
     * Тестовый метод, позволяющий зафиксировать поток в состоянии BLOCKED (в ожидании блокировки).
     * Создаются два потока на основе объекта класса StateBlocked.
     * Запускаем первый поток. Главный поток уходит в режим ожидания, чтобы только что запущенный поток успел заблокировать монитор объекта класса StateBlocked.
     * Запускается второй поток. Главный поток уходит в режим ожидания, чтобы только что запущенный поток успел войти в режим BLOCKED.
     * Идет проверка на то, что состояние второго потока совпадает с состоянием BLOCKED.
     * @see StateBlocked класс, который демонстрирует ситуацию данного состояния.
     */
    @Test
    void test5() throws InterruptedException {
        StateBlocked object = new StateBlocked();

        Thread thread1 = new Thread(object);
        Thread thread2 = new Thread(object);

        thread1.start();
        TimeUnit.MILLISECONDS.sleep(200);

        thread2.start();

        TimeUnit.MILLISECONDS.sleep(200);

        assertEquals(thread2.getState(), Thread.State.BLOCKED);
    }
}
