package mordyasov.thread_states;

import java.util.concurrent.TimeUnit;

/**
 * Класс, позволяющий смоделировать ситуацию, когда мы можем поймать поток на состояние BLOCKED.
 */
public class StateBlocked implements Runnable {

    @Override
    public void run() {
        doSomething();
    }

    /**
     * Первый зашедший в метод поток блокирует монитор объекта класса StateBlocked. Все остальные потоки в данный моммент
     * будут приобретать состояние BLOCKED, тк монитор будет занят.
     * Первый поток уходит в режим ожидания, удерживая до сих пор монитор в своих руках.
     * Второй поток, который был вызван на 200ms позже, увидит, что монитор объекта был занят. Поэтому он
     * перейдет в состояние BLOCKED, ожидая освобождения монитора.
     * Как раз в этот момент это состояние может бы проверено в тестовом методе.
     */
    private synchronized void doSomething() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
