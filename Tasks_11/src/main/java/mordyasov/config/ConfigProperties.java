package mordyasov.config;

import java.io.InputStream;
import java.util.Properties;

/**
 * Класс ConfigProperties, хранящий конфигурационные настройки и
 * позволяющий свободно работать с .properties файлом, вытаскивая необходимую информацию.
 */
public class ConfigProperties {
    /**
     * Имя .properties файла.
     */
    private String configName;

    /**
     * Объект класса Properties, с помощью которого извлекается информация из файла.
     */
    private Properties properties;

    public ConfigProperties(String configName) {
        properties = findPropertiesFile(configName);
        this.configName = configName;
    }

    /**
     * Функция, отыскивающая .properties файл в директории resources.
     * Если .properties файл существует, то объект класса ConfigProperties считается валидным.
     * @param configName - имя .properties файла.
     * @return объект класса ConfigProperties.
     * @throws RuntimeException - неверно указано имя файла или такого файла не существует.
     */
    private Properties findPropertiesFile(String configName) {
        Properties properties = new Properties();

        try(InputStream io = ConfigProperties.class
                .getResourceAsStream(configName)) {

            properties.load(io);

            return properties;
        } catch (Exception e) {
            throw new RuntimeException(
                    String.format("Файл %s не был найден в resources/", configName));
        }
    }

    /**
     * Функция, позволяющая извлечь значение переданного ключа из .properties файла.
     * Если указанного ключа нет в файле, то будет возвращено null.
     * @param key - ключ, хранящийся в .properties файле.
     * @return значение ключа.
     */
    public String getPropertyByKey(String key) {
        return properties.getProperty(key);
    }
}
