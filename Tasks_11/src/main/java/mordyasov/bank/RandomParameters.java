package mordyasov.bank;

import mordyasov.config.CurrentConfig;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Класс RandomParameters, позволяющий генерировать определенные объекты.
 */
public class RandomParameters {
    /**
     * Функция, позволяющая получить случайным образом два счета в банке.
     * Доступ к этим счетам блокируется от общего доступа, т.к они будут использованы клиентом
     * для совершения денежного трансфера.
     * @param listOfAccounts - список всех счетов в банке.
     * @return список двух счетов, полученных случайным образом.
     */
    public static List<Account> accounts(List<Account> listOfAccounts) {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        return IntStream.range(0, 2)
                .map(i -> random.nextInt(listOfAccounts.size()))
                .sorted()
                .mapToObj(listOfAccounts::get)
                .peek(Account::lock)
                .collect(Collectors.toList());
    }

    /**
     * Функция, позволяющая получить случайным образом сумму для перевода денежных средств.
     * Генерация суммы происходит от 0 (включая) до значения max_amount_for_transfer (не включая).
     * Это значение задается в конфигурационных настройках в файле .properties.
     * @return сгенерируемую некоторую сумму.
     */
    public static BigDecimal amount() {
        return BigDecimal.valueOf(
                ThreadLocalRandom.current().nextInt(
                        Integer.parseInt(
                                CurrentConfig.property(CurrentConfig.MAX_AMOUNT_FOR_TRANSFER))));
    }
}
