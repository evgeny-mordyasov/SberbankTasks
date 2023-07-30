package mordyasov;

/**
 * Класс RazerFactory реализует абстрактную фабрику KeyboardsFactory, предоставляя возможность создать
 * клавиатуру от компании Razer двух видов: проводную или беспроводную.
 */
public class RazerFactory implements KeyboardsFactory {

    /**
     * Функция создания проводной клавиатуры.
     * @returns объект класса RazerWired (проводную клавитуру от Razer).
     */
    @Override
    public Wired createWired() {
        return new RazerWired();
    }

    /**
     * Функция создания беспроводной клавиатуры.
     * @returns объект класса RazerWireless (беспроводную клавитуру от Razer).
     */
    @Override
    public Wireless createWireless() {
        return new RazerWireless();
    }
}
