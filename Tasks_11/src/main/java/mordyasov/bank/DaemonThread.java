package mordyasov.bank;

import mordyasov.config.CurrentConfig;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Класс DaemonThread, выступающий в роли потока-наблюдателя за операциями
 * перевода денежных средств между счетами.
 */
public class DaemonThread extends Thread {
    /**
     * Банк, в пределах которого поток работает.
     */
    private Bank bank;

    public DaemonThread(Bank bank) {
        this.bank = bank;
    }

    /**
     * Процедура, выполняющая до тех пор, пока не завершен главный поток.
     * Поток-наблюдатель сравнивает сумму, взятую из текущей конфигурации настроек, и
     * сумму всех счетов в банке. Если эти значения не равны, то в банке произошел сбой при
     * переводе денежных средств между счетами.
     * @throws RuntimeException - если суммы не равны.
     * @see #calculateAmount() - подсчет суммы всех счетов в банке.
     * @see #takeOffTheLockOnTheList() - разблокирование доступа к счетами.
     * @see #sleep() - режим ожидания потока-наблюдателя.
     */
    @Override
    public void run() {
        BigDecimal amount =
                new BigDecimal(
                        CurrentConfig.property(CurrentConfig.AMOUNT_IN_THE_BANK));

        while (true) {
            if (amount.compareTo(calculateAmount()) != 0) {
                throw new RuntimeException(
                        "Сумма из файла *.properties не совпадает с суммой всех счетов");
            }

            takeOffTheLockOnTheList();
            sleep();
        }
    }

    /**
     * Функция, позволяющая подсчитать сумму всех счетов в банке.
     * Сначала она пытается получить доступ ко всем счетами с помощью метода putTheLockOnTheList(List).
     * После она совершает клонирование списка счетов через метод clone(List).
     * Только потом производится подсчет суммы.
     * @return сумма всех счетов в банке.
     * @see #putTheLockOnTheList(List) - получение доступа к счетам.
     * @see #clone(List) - клонирование элементов списка.
     */
    private BigDecimal calculateAmount() {
        return clone(putTheLockOnTheList(bank.getListOfAccounts()))
                .stream()
                .map(Account::getBalance)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    /**
     * Функция, позволяющая получить доступ ко всем счетам в банке.
     * Она проходит по всему списку счетов и перехватывает блокировку счетов.
     * Работает до тех пор, пока не будут взяты все блокировки счетов.
     * @param list - список счетов.
     * @return список счетов.
     */
    private List<Account> putTheLockOnTheList(List<Account> list) {
        return list.stream()
                .peek(Account::lock)
                .collect(Collectors.toList());
    }

    /**
     * Функция, позволяющая склонировать все счета в списке.
     * Необходима для того, чтобы во время подсчета суммы счетов не произошли
     * сбои при переводе денежных средств (нехватка или переполнение суммы на счету).
     * @param list - список счетов.
     * @return склонированный список счетов.
     */
    private List<Account> clone(List<Account> list) {
        return list.stream()
                .map(a -> new Account(a.getBalance().intValue()))
                .collect(Collectors.toList());
    }

    /**
     * Процедура, позволяющая разблокировать доступ ко всем счетам в банке.
     */
    private void takeOffTheLockOnTheList() {
        bank.getListOfAccounts()
                .forEach(Account::unlock);
    }

    /**
     * Процедура, отправляющая поток-наблюдателя в режим ожидания.
     */
    private void sleep() {
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
