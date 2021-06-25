package mordyasov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyStringUtilsTest {
    private StringBuilder text;

    /**
     * В тексте одно повторяющееся слово "rp", записанное в различных формах.
     */
    @Test
    public void test() {
        text = new StringBuilder();
        text.append("RP rp Rp rP")
                .append(" a b c d e f");

        assertArrayEquals(new String[] {"rp"}, MyStringUtils.countingWords(text.toString(), 1));
    }

    /**
     * В тексте два повторяющихся слова: "hi" и "we", которые будут найдены функцией.
     */
    @Test
    public void test1() {
        text = new StringBuilder();
        text.append("hi hI Hi HI")
                .append(" we we we")
                .append(" a b c d e f");

        assertArrayEquals(new String[] {"hi", "we"}, MyStringUtils.countingWords(text.toString(), 2));
    }

    /**
     * В тексте два повторяющихся слова: "word" и "tord". Т.к их повторность одинакова, то
     * первым будет идти "tord", а после "word" (в лексикографическом порядке).
     * Так же будет определено и третье слово "b".
     */
    @Test
    public void test2() {
        text = new StringBuilder();
        text.append("word word word word")
                .append(" tord tord tord tord")
                .append(" abc cba qax e g b");

        assertArrayEquals(new String[] {"tord", "word", "b"}, MyStringUtils.countingWords(text.toString(), 3));
    }

    /**
     * В тексте четыре повторяющихся слова: "bb", "aa", "cc", "dd".
     * "bb" - 4 раза.
     * "aa" - 3 раза.
     * "cc" - 2 раза.
     * "dd" - 2 раза.
     * Т.к "cc" < "dd" в лексикографическом порядке, то сначала будет идти "cc", а после "dd".
     */
    @Test
    public void test3() {
        text = new StringBuilder();
        text.append("bb bb bb bb")
                .append(" aa aa aa ")
                .append(" cc cc")
                .append(" dd dd")
                .append(" lock close hat");

        assertArrayEquals(new String[] {"bb", "aa", "cc", "dd"}, MyStringUtils.countingWords(text.toString(), 4));
    }
}
