package mordyasov.bank;

import java.math.BigDecimal;
import java.util.List;

/**
 * Класс Client, выступающий в роли потока, совершающий денежный трансфер между счетами.
 */
public class Client implements Runnable {

    /**
     * Банк, в котором будет произведена операция перевода денежны средств.
     */
    private final Bank bank;

    public Client(Bank bank) {
        this.bank = bank;
    }

    /**
     * Процедура, позволяющая совершить денежный трансфер между счетами.
     * Счета и сумма для перевода выбираются случайным образом с помощью класса RandomParameters.
     */
    @Override
    public void run() {
        List<Account> accounts = RandomParameters.accounts(bank.getListOfAccounts());
        BigDecimal amount = RandomParameters.amount();

        bank.makeMoneyTransfer(accounts.get(0), accounts.get(1), amount);
        accounts.forEach(Account::unlock);
    }
}
