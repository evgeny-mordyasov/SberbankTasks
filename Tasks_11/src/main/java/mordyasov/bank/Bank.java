package mordyasov.bank;

import java.math.BigDecimal;
import java.util.List;

/**
 * Класс Bank, выступающий в роли хранилища счетов.
 */
public class Bank {
    /**
     * Список, содержащий счета.
     */
    private final List<Account> listOfAccounts;

    public Bank(List<Account> accounts) {
        listOfAccounts = accounts;
    }

    /**
     * Функция, позволяющая совершить денежный трансфер между счетами.
     * Если на счете списания недостаточно денежных средств, то операция отменяется.
     * @param from - с какого счета отправляются денежные средства.
     * @param to - на какой счет приходят денежные средства.
     * @param amount - сумма для совершения денежнего трансфера.
     * @see Account#writeOffFromBalance(BigDecimal).
     * @see Account#topUpBalance(BigDecimal).
     */
    public void makeMoneyTransfer(Account from, Account to, BigDecimal amount) {
        if (from.writeOffFromBalance(amount)) {
            to.topUpBalance(amount);
        }
    }

    /**
     * Функция, позволяющая предоставить список всех счетов в банке.
     * @return список счетов.
     */
    public List<Account> getListOfAccounts() {
        return listOfAccounts;
    }
}
