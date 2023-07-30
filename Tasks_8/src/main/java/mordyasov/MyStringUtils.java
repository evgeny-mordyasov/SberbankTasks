package mordyasov;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class MyStringUtils {

    private MyStringUtils() {
    }

    /**
     * Функция, позволяющая из текста извлечь наиболее повторяющиеся слова.
     * @param text - текст, в котором необходимо найти слова.
     * @param n - количество наиболее повторяющихся слов.
     * @return массив типа String, содержащий слова в порядке убывания по количеству повторений.
     */
    public static String[] countingWords(String text, int n) {
        Map<String, Integer> map = new HashMap<>();

        Arrays.stream(text.split(" ")).map(String::toLowerCase).forEach(s -> {
            if (map.containsKey(s)) {
                map.replace(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        });

        return map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(n)
                .map(Map.Entry::getKey)
                .toArray(String[]::new);
    }
}
