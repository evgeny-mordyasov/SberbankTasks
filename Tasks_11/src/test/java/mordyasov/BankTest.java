package mordyasov;

import mordyasov.bank.Account;
import mordyasov.bank.Bank;
import mordyasov.bank.Client;
import mordyasov.bank.DaemonThread;
import mordyasov.config.CurrentConfig;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Класс BankTest, необходимый для тестирования совершения денежных трансферов.
 */
public class BankTest {
    /**
     * Тест, работающий по первому сценарию.
     * Сценарий: Запускаем N клиентов M счетов. Все ок.
     */
    @Test
    public void test1() {
        CurrentConfig.appoint("../configs/config1.properties");
    }

    /**
     * Тест, работающий по второму сценарию.
     * Сценарий: Пополняем какому-нибудь счету сумму, отличающуюся от суммы в конфигурационных настройках.
     * Запускаем N клиентов и М счетов. Ожидаем, что будет исключение RuntimeException от потока-наблюдателя.
     */
    @Test
    public void test2() {
        CurrentConfig.appoint("../configs/config2.properties");
    }

    /**
     * Тест, работающий по первому сценарию.
     * Сценарий: Запускаем N клиентов, которые работают только с двумя счетами. Все ок.
     */
    @Test
    public void test3() {
        CurrentConfig.appoint("../configs/config3.properties");
    }

    /**
     * Процедура, инициализирующая начальное состояние: создание банка и его счетов,
     * создание потока-наблюдателя, создание и запуск клиентов-потоков.
     */
    @AfterAll
    public static void init() {
        int numberOfClients = property(CurrentConfig.NUMBER_OF_CLIENTS);
        Bank bank = new Bank(getList());

        DaemonThread daemonThread = new DaemonThread(bank);
        daemonThread.setDaemon(true);
        daemonThread.start();

        IntStream.range(0, numberOfClients)
                .forEach(i ->
                        new Thread(new Client(bank)).start());
    }

    /**
     * Функция, позволяющая из текущей конфигурации настроек файла .properties извлечь
     * необходимое значение переданного ключа.
     * Если в качестве ключа передано fail, то сначала проверяется, есть ли данный ключ в
     * файле .properties. Если он есть, то будет возвращено значение 1, что сигнализирует о
     * аварийной ситуации в потоке-наблюдателе.
     * @param key - ключ из файла .properties.
     * @return значение ключа.
     * @see CurrentConfig#property(String).
     */
    private static int property(String key) {
        if (key.equals("fail")) {
            String fail = CurrentConfig.property(key);

            if (fail == null) {
                return 0;
            } else return 1;
        }

        return Integer.parseInt(CurrentConfig.property(key));
    }

    /**
     * Функция, позволяющая создать список счетов и
     * при необходимости организовать аварийную ситуацию в потоке-наблюдателе, добавив
     * первому счету любую (в данном случае 100) сумму. По итогу сумма из
     * конфигурационных настроек не будет равняться с суммой всех счетов банка.
     * @return список счетов.
     * @see #createList().
     * @see Account#topUpBalance(BigDecimal).
     */
    private static List<Account> getList() {
        List<Account> list = createList();

        if (property("fail") == 1) {
            list.get(0).topUpBalance(BigDecimal.valueOf(100));
        }

        return list;
    }

    /**
     * Функция, создающая список счетов по трем правилам, описанным в любом .properties файле.
     * @return список счетов.
     */
    private static List<Account> createList() {
        int numberOfAccounts = property(CurrentConfig.NUMBER_OF_ACCOUNTS);
        int amount = property(CurrentConfig.AMOUNT_IN_THE_BANK);

        if (numberOfAccounts < amount) {
            int averageSum = amount / numberOfAccounts;

            return IntStream.range(0, numberOfAccounts)
                    .mapToObj(i -> {
                        if (i == numberOfAccounts - 1) {
                            return new Account(amount - averageSum * (numberOfAccounts - 1));
                        }

                        return new Account(averageSum);
                    }).collect(Collectors.toList());
        }
        else if (numberOfAccounts > amount) {
            return IntStream.range(0, numberOfAccounts)
                    .mapToObj(i -> {
                        if (i < amount) {
                            return new Account(1);
                        }
                        else {
                            return new Account(0);
                        }
                    }).collect(Collectors.toList());
        }
        else {
            return IntStream.range(0, numberOfAccounts)
                    .mapToObj(i -> new Account(1))
                    .collect(Collectors.toList());
        }
    }
}
