package mordyasov_3_years_later;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MyStringUtilsTest {

    private StringBuilder text;

    @Test
    void test() {
        text = new StringBuilder();
        text.append("RP rp Rp rP")
                .append(" a b c d e f");

        assertArrayEquals(new String[] {"rp"}, MyStringUtils.countingWords(text.toString(), 1));
    }

    @Test
    void test1() {
        text = new StringBuilder();
        text.append("hi hI Hi HI")
                .append(" we we we")
                .append(" a b c d e f");

        assertArrayEquals(new String[] {"hi", "we"}, MyStringUtils.countingWords(text.toString(), 2));
    }

    @Test
    void test2() {
        text = new StringBuilder();
        text.append("word word word word")
                .append(" tord tord tord tord")
                .append(" abc cba qax e g b");

        assertArrayEquals(new String[] {"tord", "word", "abc"}, MyStringUtils.countingWords(text.toString(), 3));
    }

    @Test
    void test3() {
        text = new StringBuilder();
        text.append("bb bb bb bb")
                .append(" aa aa aa ")
                .append(" cc cc")
                .append(" dd dd")
                .append(" lock close hat");

        assertArrayEquals(new String[] {"bb", "aa", "cc", "dd"}, MyStringUtils.countingWords(text.toString(), 4));
    }
}
