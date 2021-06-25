package mordyasov.thread_states;

import java.util.concurrent.TimeUnit;

/**
 * Класс, позволяющий смоделировать ситуацию, когда мы можем поймать поток на состояние TIME_WAITING.
 */
public class StateTimeWaiting implements Runnable {
    /**
     * Отправляем поток в режим ожидания, предоставляя возможность проверить его состояние TIME_WAITING.
     */
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
