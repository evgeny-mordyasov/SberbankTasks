package mordyasov;

/**
 * Класс LogitechFactory реализует абстрактную фабрику KeyboardsFactory, предоставляя возможность создать
 * клавиатуру от компании Logitech двух видов: проводную или беспроводную.
 */
public class LogitechFactory implements KeyboardsFactory {
    /**
     * Функция создания проводной клавиатуры.
     * @returns объект класса LogitechWired (проводную клавитуру от Logitech).
     */
    @Override
    public Wired createWired() {
        return new LogitechWired();
    }

    /**
     * Функция создания беспроводной клавиатуры.
     * @returns объект класса LogitechWireless (беспроводную клавитуру от Logitech).
     */
    @Override
    public Wireless createWireless() {
        return new LogitechWireless();
    }
}
