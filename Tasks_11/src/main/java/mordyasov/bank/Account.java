package mordyasov.bank;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс Account, являющийся счетом в банке и выступающий в роли хранилища денежных средств.
 */
public class Account {

    /**
     * Баланс - текущее значение денежных средств на счете.
     */
    private BigDecimal balance;

    /**
     * Замок (блокировка), необходимый для ограничения общего доступа к счету.
     */
    private final Lock lock;

    public Account(int initBalance) {
        balance = BigDecimal.valueOf(initBalance);
        lock = new ReentrantLock();
    }

    /**
     * Процедура, позволяющая пополнить значение баланса на счете.
     * @param amount - сумма пополнения.
     */
    public void topUpBalance(BigDecimal amount) {
        balance = balance.add(amount);
    }

    /**
     * Функция, позволяющая списать указанное значение со счета.
     * @param amount - сумма списания.
     * @return true, если на счету присутствует текущая сумма; false,
     * если списание невозможно из-за нехватки денежных средств.
     */
    public boolean writeOffFromBalance(BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            return false;
        }

        balance = balance.subtract(amount);
        return true;
    }

    /**
     * Функция, позволяющая продемонстрировать текущее значение баланса на счете.
     * @return сумму на счете.
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Процедура, ограничивающая доступ к счету от общего доступа.
     */
    public void lock() {
        lock.lock();
    }

    /**
     * Процедура, позволяющая сделать счет доступным для работы.
     */
    public void unlock() {
        lock.unlock();
    }
}
