package mordyasov.config;

/**
 * Класс CurrentConfig, представляющий собой текущую (действующую) конфигурацию настроек,
 * задаваемых с помощью .properties файла.
 */
public class CurrentConfig {
    /**
     * Конфигурационные настройки, находящиеся в .properties файле.
     */
    public static String NUMBER_OF_CLIENTS = "number_of_clients";
    public static String NUMBER_OF_ACCOUNTS = "number_of_accounts";
    public static String AMOUNT_IN_THE_BANK = "amount_in_the_bank";
    public static String MAX_AMOUNT_FOR_TRANSFER = "max_amount_for_transfer";

    /**
     * Актуальная конфигурация настроек.
     */
    private static ConfigProperties config;

    private CurrentConfig() {}

    /**
     * Процедура, позволяющая назначить текущую конфигурацию настроек.
     * Конфигурация настроек будет успешно установлена, если имя файла .properties существует
     * в директории resources.
     * @param configName - имя .properties файла.
     * @throws RuntimeException - неверно указано имя файла или такого файла не существует.
     */
    public static void appoint(String configName) {
        config = new ConfigProperties(configName);
    }

    /**
     * Функция, позволяющая извлекать из текущей конфигурации настроек значение ключа.
     * Если указанного ключа нет в файле, то будет возвращено null.
     * @param key - ключ, хранящийся в .properties файле.
     * @return значение ключа.
     * @see ConfigProperties#getPropertyByKey(String).
     */
    public static String property(String key) {
        return config.getPropertyByKey(key);
    }
}
